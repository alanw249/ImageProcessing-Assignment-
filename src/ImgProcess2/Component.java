package ImgProcess2;

import java.util.ArrayList;


/**
 * @author Alan Walsh
 * @version 1.0
 */
public class Component {
		private int id;
		private ArrayList<Integer> x;
		
		
		/**
		 *  @param id
		 *  Creates a new component which has an id of its root
		 */
		public Component(int root)
		{
					x = new ArrayList<Integer>();
					this.id = root;
		}
		
		/**
		 * @param root
		 * Setter to give id to component
		 */
		public void setID(int root)
		{
			this.id = id;
		}
		
		/**
		 * getter to @return id
		 */
		public int getID() 
		{
			return id;
		}
		
		/**
		 * @param width
		 * @return
		 * Finds largest X point in component
		 */
		public int biggestXInArray(int width)
		{
			int bigX = x.get(0)%width; //TODO why is it modulo width?
			for (int i=0; i<x.size();i++)
			{
					int z = x.get(i)%width;
					if(z > bigX)
					{
						bigX = z;
					}
			}
			return bigX;
		}
		
		/**
		 * @param width
		 * @return
		 * Finds smallest X point in component
		 */
		public int smallestXInArray(int width)
		{
			int smallX = x.get(0)%width; //TODO why is it modulo width?
			for (int i=0; i<x.size();i++)
			{
				int z = x.get(i)%width;
				if(z < smallX)
				{
					smallX = z;
				}
			}
			return smallX;
		}
		
		/**
		 * @param height
		 * @return
		 * Finds biggest Y point in component
		 */
		public int biggestYInArray(int width)
		{
			int bigY = x.get(0)/width; //TODO why is it divide height?
			for (int i=0; i<x.size();i++)
			{
					int z = x.get(i)/width;
					if(z > bigY)
					{
						bigY = z;
					}
			}
			return bigY;
		}
		
		/**
		 * @param height
		 * @return
		 * Finds smallest Y point in component
		 */
		public int smallestYInArray(int width)
		{
			int smallY = x.get(0)/width; //TODO why is it divide height?
			for (int i=0; i<x.size();i++)
			{
				int z = x.get(i)/width;
				if(z < smallY)
				{
					smallY = z;
				}
			}
			return smallY;
		}
		
		/**
		 * @param piece
		 * adds points to the component
		 */
		public void addPiece(int piece)
		{
			x.add(piece);
		}
		
		
		@Override
		public String toString() {
			return "Component id=" + id;
		}
}
