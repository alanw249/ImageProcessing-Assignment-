package ImgProcess2;

import java.util.Scanner;
import java.util.ArrayList;

import edu.princeton.cs.introcs.Picture;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

import java.awt.Color;

public class Menu {

	private Scanner input;
	
	public Menu()
	{
		input = new Scanner(System.in);
	}
	
	public static void main(String[] args)
	 {
		Menu menu = new Menu();
		menu.run();
	 }
	 
	
	 
	 private void run()
	 {
		 	 
			 System.out.println("*************************************");
			 System.out.println("*************************************");
			 System.out.println("");
			 System.out.println("Author: Alan Walsh");
			 System.out.println("Version: 1.0.4");
			 System.out.println("");
			 System.out.println("Project: Image Processing Machine");
			 System.out.println("");
			 System.out.println("*************************************");
			 System.out.println("*************************************");
			 System.out.println("--> Press any button to continue");
			 input.nextLine();
			 bigPadding();
			 
			 secondMenu(mainMenu());
	 }
	 
	 private String mainMenu()
	 {
		 bigPadding();
		 bigPadding();
		 System.out.println("Choose An Image Below;");
		 System.out.println("=====================");
		 System.out.println("1) Shapes");
		 System.out.println("2) ------");
		 System.out.println("3) ------");
		 System.out.println("4) Enter your own url");
		 smallPadding();
		 System.out.print("==>");
		 
		 String choice = "";
		 int option = input.nextInt();
		 while(option < 1 || option > 5)
			{
				StdOut.println("Invalid number, please choose between 1 and 4 only.");
				smallPadding();
				 System.out.print("==>");
				 option = input.nextInt();
			}
		 
		 switch(option)
		 {
		 		case 1: choice = "images/square.bmp";
		 		break;
		 		case 2: choice = "images/";
		 		break;
		 		case 3: choice = "images/";
		 		break;
		 		case 4: 
		 			bigPadding();
		 			bigPadding();
		 			System.out.println("Please input the URL:");
		 			choice = input.nextLine();
		 		break;
		 }
		 return choice;
	 }
	 
	 private void secondMenu(String fileLocation)
	 {
		 int option;
		 ConnectedComponents cc = new ConnectedComponents(fileLocation);
		 cc.changeImage();
		 System.out.println("Now choose what you would like to do with the image!");
		 System.out.println("======");
		 System.out.println("1) Return original Image");
		 System.out.println("2) GrayScale Version of Image");
		 System.out.println("3) Binarised Version of Image");
		 System.out.println("4) Random Colored Version of Binarised Image (Only Kind of Works)");
		 System.out.println("5) Bounding Box Version of Image (Doesn't work)");
		 smallPadding();
		 System.out.print("==>");
		 option = input.nextInt();
		 while(option < 1 || option > 5)
			{
				StdOut.println("Invalid number, please choose between 1 and 4 only.");
				smallPadding();
				System.out.print("==>");
				option = input.nextInt();
			}
		 
		 switch(option)
		 {
		 			case 1:
		 					Picture pic = cc.getPicture();
		 					pic.show();
		 					bigPadding();
		 					System.out.println("GoodBye!");
		 				break;
		 			case 2:
		 					Picture pic2 = cc.getGrayScale();
		 					pic2.show();
		 					bigPadding();
		 					System.out.println("GoodBye!");
		 				break;
		 			case 3:
		 					Picture pic3 = cc.getBinarisedImage();
		 					pic3.show();
		 					bigPadding();
		 					System.out.println("GoodBye!");
		 				break;
		 			case 4:
		 					Picture pic4 = cc.getPicture();
		 					cc.randomColourImage(pic4);
		 					pic4.show();
		 					bigPadding();
		 					System.out.println("GoodBye!");
		 				break;
		 			case 5:
		 					Picture pic5 = cc.boundingBox();
		 					pic5.show();
		 				break;
			 
		 }
	 }
	 
	 
	 /**
		 * Used as a replaces for clearing screen in menu system (Looks neater)
		 */
		private void bigPadding() {
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		}

		/**
		 * Used to invoke small gaps beween certain writing to make the menu more user friendly.
		 */
		private void smallPadding() {
			System.out.println("\n\n\n\n\n");
		}
}
