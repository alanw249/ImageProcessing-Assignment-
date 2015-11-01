package ImgProcess2;

import edu.princeton.cs.introcs.Picture;

import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

public class ConnectedComponents {

	private static int threshold = 128;
	private String fileLocation;
	private ArrayList<Component> components;
	private ArrayList <Integer> roots;
	private Picture grayScale;
	private Picture binary;
	
	/**
	 * Creates an instance of the connectedComponents image class
	 * @param fileLocation 
	 * takes in the location of the image we want to alter
	 */
	public ConnectedComponents(String fileLocation) 
	{
			this.fileLocation = fileLocation;
			this.grayScale = new Picture(fileLocation);
			this.binary = new Picture(fileLocation);
			components = new ArrayList<Component>();
			roots = new ArrayList<Integer>();
	}
	
	/**
	 * Returns the normal instance of the image
	 * @return
	 */
	public Picture getPicture() 
	{
		Picture p = new Picture(this.fileLocation);
		return p;
	}
	
	/**
	 * Changes the image to grayScale and Binarises the image.
	 */
	public void changeImage()
	{
			Picture pic = new Picture(fileLocation);
			for (int i = 0; i < pic.width(); i++) {
				for (int j = 0; j < pic.height(); j++) {
				
					Color color = pic.get(i, j);
					grayScale.set(i, j, Luminance.toGray(color));
					double lum = Luminance.lum(color);
				
					if (lum >= threshold) {
						binary.set(i, j, Color.BLACK);
					}
					else {
					binary.set(i, j, Color.WHITE);
					}
				}
			}
	}
	/**
	 * returns the grayScale instance of the picture.
	 * @return
	 */
	public Picture getGrayScale()
	{
		return grayScale;
	}
	
	/**
	 * returns the binarised instance of the picture.
	 * @return
	 */
	public Picture getBinarisedImage()
	{
		return binary;
	}
	
	/**
	 * returns a random color by generating a random decimal value for the red , blue and green values.
	 * @return
	 */
	public Color randomColor()
	{
		Random randomNumberGenerator = new Random();
		int r = randomNumberGenerator.nextInt(255);
		int g = randomNumberGenerator.nextInt(255);
		int b = randomNumberGenerator.nextInt(255);
		return new  Color(r,g,b);
	}
	
	/**
	 * Assigns a random color value to each of the components
	 * @param binary
	 * @return
	 */
	public Picture randomColourImage(Picture binary)
	{
		Picture p = binary;
		QuickUnion qu = union(p);
		HashMap<Integer, Color> map = new HashMap<Integer, Color>();
		for (int x = 0; x < p.width(); x++)
		{
			for(int y=0; y < p.height(); y++)
			{
					int root = qu.root(index(x,y)); //root = label for the hashmaps
					if(map.get(root) == null) //Checks if the root already has a color assigned to it by checking through map
					{
						Color randomColor = randomColor();
						map.put(root,  randomColor);
					  p.set(x,y, randomColor);
					}
					else
					{
						p.set(x,y,map.get(root));
					}
			}
		}
		return p;
	}
	
	private QuickUnion union(Picture binary)
	{
		QuickUnion qu = new QuickUnion(binary.width()*binary.height());
		
		for(int x=0; x < binary.width(); x++)
		{
			for(int y=0; y < binary.height(); y++)
			{
					if((x+1 < binary.width() && (y+1 < binary.height())) && binary.get(x, y).equals(binary.get(x+1,y+1)))
					{
						qu.union(index(x,y), index(x+1,y+1));
					}
					if((y+1 < binary.height()) && binary.get(x, y).equals(binary.get(x,y+1)))
					{
						qu.union(index(x,y), index(x,y+1));
					}
					if((x+1 < binary.width()) && binary.get(x, y).equals(binary.get(x+1,y)))
					{
						qu.union(index(x,y), index(x+1,y));
					}
			}
		}
		return qu;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @returns the location of the pixel in the Union Method
	 */
	private int index(int x, int y)
	{
		return ((y*binary.width())+x); //TODO
	}
	
	public int countComponents() 
	{
		QuickUnion qu = union(binary);
		return qu.count()-1; // Minus one to not count background component
	}
	
	public Picture boundingBox()
	{
		Picture ph = binary;
		Picture pic = new Picture(fileLocation);
		QuickUnion qu = union(ph);
		
		//list of components
		for(int x=0; x < ph.width(); x++)
		{
				for(int y=0; y< ph.height(); y++)
				{
					int root = qu.root(index(x,y));
					if(!(roots.contains(root)) && root != 0) //making sure nothing is added twice / or background is added
					{
						roots.add(root);
						Component c = new Component(root);
						components.add(c);
					}
				}
		}
		
		//list of component components
		for (int x = 0; x < ph.width(); x++)
		{
			for(int y=0; y< ph.height(); y++)
			{
				for(int i=0;i< components.size();i++)
				{
					 Component component = components.get(i);
					 int root = qu.root(index(x,y));
					 int componentId = component.getID();
					 if(root == componentId) //Checking if root and component id match
					 {
						 component.addPiece(index(x, y));
					 }
				}
			}
		}
		
		//draws Bounding Box
		for(Component c : components)
		{
			int lx = c.biggestXInArray(ph.width());
			int sx = c.smallestXInArray(ph.width());
			int ly = c.biggestYInArray(ph.width());
			int sy = c.smallestYInArray(ph.width());
			
			if (sx > lx || sy > ly)
			{
				System.out.println("It's All White Pixels!");
			}
			else
			{
					for (int x = sx; x<= lx; x++)
					{
						ph.set(x, sy, Color.RED);
						ph.set(x, ly, Color.RED);
					}
					for (int y = sy; y <= ly; y++)
					{
						ph.set(y, sx, Color.RED);
						ph.set(y, lx, Color.RED);
					}
			}
		}
		return pic;
	}
}
