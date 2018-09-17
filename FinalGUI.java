import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;


public class FinalGUI
{
	 int sizei = 100; 
	 int sizej = 100; 
	int iteration = 1; 
	 int iterations = 300; 
	 int percentVaccinated = 60;
	JFrame window;  
	Container content;
	JTextField expression;

	public void GUI()
	{
	window = new JFrame( "Live Update");
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	content = window.getContentPane();
	content.setLayout(new GridLayout(sizei,sizej));
	
	expression = new JTextField();
	expression.setFont(new Font("verdana", Font.BOLD, 16));
	expression.setText("");
	
	content.add(expression); 
	
	
	
	
	
	}



	public static void main( String args[] )
	{
	int[][] Array = new int[sizei][sizej]; 
	int[][] StatArray = new int[sizei][sizej]; 
	
	
	/////Generating Lattice/////
	for (int i = 0; i < sizei; i ++)
		{
		for (int j = 0; j <sizej; j ++)
			{
		
			Random randGen =  new Random();
			int randomNum = 1+randGen.nextInt(10); 
			if( randomNum <5 )
				Array[i][j] = 1; 
	
			}
		}
	
	
	/////PLACING INFECTED WALKER/////
	int cont = 1; 
	while (cont == 1)
	{
		Random randGen3 = new  Random();
		int randomNum3 = 1+randGen3.nextInt(sizei-1); 
		Random randGen4 = new Random(); 
		int randomNum4 = 1+randGen3.nextInt(sizej-1); 
		if (Array[randomNum3][randomNum4] == 1)
		{
			Array[randomNum3][randomNum4] = 2; 
			cont = 0; 
		}
		else
			cont = 1; 
	}
	////////////////////////////////////
	
	/////PLACING VACCINATED WALKERS/////
	int[][] VaccineArr = new int[sizei][sizej]; 
	for (int i = 0; i < sizei; i++)
		for (int j = 0; j < sizej; j++)
		{
			if (Array[i][j] == 1)
			{
				Random randGen5 = new Random(); 
				int randomNum5 = 1+randGen5.nextInt(100);
				if(randomNum5 < percentVaccinated)
					VaccineArr[i][j] = 1; 
			}
		}
	////////////////////////////////////
	
	
	
	
	Random randGen2 = new Random(); 
	/////RANDOM WALKING/////
	while(iteration <iterations)
	{
		RandomWalk(Array, StatArray, VaccineArr, sizei, sizej, randGen2); 
		for(int i =0; i <sizei; i++)
			for(int j = 0; j <sizej; j++)
			{
			if(Array[i][j] ==1 &&((i !=0 &&Array[i-1][j] ==2) || (i !=0 && j != sizej-1 && Array[i][j] ==2)||(j !=sizej-1 && Array[i][j+1] ==2) || (i != sizei-1 && j != sizej-1 && Array[i][j] ==2) || (i != sizei-1 && Array[i+1][j] ==2) || (i!= sizei -1 && j != 0 && Array[i][j] == 2 ) ||(j !=0 && Array[i][j-1] == 2)|| (i !=0 && j != 0 && Array[i][j] == 2)))
				{
					if (VaccineArr[i][j]==1)
					{
						Random randGen6 = new Random(); 
						int randomNum6 = 1+randGen6.nextInt(100); 
						if (randomNum6 <5)
							Array[i][j] = 2; 
					}
					else
						Array[i][j] = 2; 
				}
			}
			
		
		//printing(Array, sizei, sizej); 
		percentInfected(Array, sizei, sizej); 	
		//System.out.println(); 
		iteration ++; 
	}
	//printing(Array, sizei, sizej);
	
	
}
	
	
	
	private static void RandomWalk(int[][] a, int[][] b, int[][] c, int sizei, int sizej, Random randGen2)
	{
		
		for (int i = 0; i < sizei; i ++)
		{
			for (int j = 0; j <sizej; j ++)
			{
		
			if (a[i][j] == 1 || a[i][j] == 2)
			{
				int randomNum2 = 1+randGen2.nextInt(4); 
				if (randomNum2 == 1)
					if (i != 0&& a[i-1][j] == 0 && b[i-1][j] != 1)
					{
						a[i-1][j] = a[i][j]; 
						a[i][j] = 0;
						b[i-1][j] = 1; 
						b[i][j] = 0;
						if(c[i][j] ==1)
						{
							c[i-1][j] = 1; 
							c[i][j] = 0; 
						}
					}
				if (randomNum2 == 2)
					if (j != sizej-1 && a[i][j+1] == 0 && b[i][j+1] !=1)
					{
						a[i][j+1] = a[i][j]; 
						a[i][j] = 0;
						b[i][j+1] = 1; 
						b[i][j] = 0; 
						if(c[i][j] ==1)
						{
							c[i][j+1] = 1; 
							c[i][j] = 0; 
						}
					}
				if (randomNum2 == 3)
					if (i != sizei-1 && a[i+1][j] == 0 && b[i+1][j] != 1)
					{
						a[i+1][j] = a[i][j]; 
						a[i][j] = 0;
						b[i+1][j] = 1; 
						b[i][j] = 0; 
						if(c[i][j] ==1)
						{
							c[i+1][j] = 1; 
							c[i][j] = 0; 
						}
					}
				if(randomNum2 == 4)
					if (j != 0 && a[i][j-1] == 0 && b[i][j-1] != 1)
					{	
						a[i][j-1] = a[i][j]; 
						a[i][j] = 0;
						b[i][j-1] = 1; 
						b[i][j] = 0; 
						if(c[i][j] ==1)
						{
							c[i][j-1] = 1; 
							c[i][j] = 0; 
						}
					}
			}
	
			}
		}	
	}
	private static void printing(int[][] a, int sizei, int sizej)
	{
	for(int l = 0; l<sizei; ++l)
		{
			System.out.println();
			for(int j = 0; j<sizej; ++j)
			{
				System.out.print(a[l][j]); 
			}
		}
	}
	private static void percentInfected(int[][] a, int sizei, int sizej)
	{
	double count = 0; 
	double infectedCount = 0; 
	for (int i = 0; i < sizei; i ++)
		for (int j = 0; j<sizej; j++)
		{
		if (a[i][j] == 1 || a[i][j] == 2)
			count ++; 
		if (a[i][j] == 2)
			infectedCount ++; 	
		}
		double percentInfected = infectedCount/count;
		System.out.println(percentInfected); 
	}
}
















