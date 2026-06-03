package com.wipro.Assignment;
import java.util.Scanner;
public class SumofArrayElements {
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int n;
		System.out.println("Enter the value of n:");
		n=sc.nextInt();
		int a[]=new int[n];
		for(int i=0;i<n;i++)
		{
			a[i]=sc.nextInt();
		}
		int sum=0;
		for(int i=0;i<n;i++)
		{
			sum+=a[i];
		}
		
		System.out.println("Sum of the Arrays Elements is these:"+sum);
		sc.close();
	}
}
