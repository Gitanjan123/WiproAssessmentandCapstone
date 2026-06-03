package com.wipro.Assignment;
import java.util.Scanner;
public class ReverseArray {
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int n;
		System.out.println("Enter the value of n-->:");
		n=sc.nextInt();
		int a[]=new int[n];
		for(int i=0;i<n;i++)
		{
			a[i]=sc.nextInt();
		}
		int reverse[]=new int[n];
		int j=0;
		for(int i=n-1;i>=0;i--)
		{
			reverse[j]=a[i];
			j++;
			
		}
		System.out.println("Reverse Array is:");
		for(int i=0;i<n;i++)
		{
			System.out.print(reverse[i]+" ");
		}
		sc.close();
	}
}
