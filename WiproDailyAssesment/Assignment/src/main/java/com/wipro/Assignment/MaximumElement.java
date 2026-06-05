package com.wipro.Assignment;
import java.util.Scanner;
public class MaximumElement {
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the value of n");
		int n=sc.nextInt();
		int a[]=new int[n];
		System.out.println("Enter the element in the array");
		for(int i=0;i<n;i++)
		{
			a[i]=sc.nextInt();
		}
		int maxi=Integer.MIN_VALUE;
		for(int i=0;i<n;i++)
		{
			if(a[i]>maxi)
			{
				maxi=a[i];
			}
		}
		System.out.println("The maximum value from the array is these:"+maxi);
		sc.close();
	
	}
}
