package com.wipro.Assignment;
import java.util.Scanner;
public class CountEvenNumber {
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		int n;
		System.out.println("Enter the value of n-->\n");
		n=sc.nextInt();
		int a[]=new int[n];
		System.out.println("Enter the values inside the array");
		for(int i=0;i<n;i++)
		{
			a[i]=sc.nextInt();
		}
		int cnt=0;
		for(int i=0;i<n;i++)
		{
			if(a[i]%2==0)
			{
				cnt++;
			}
		}
		System.out.println("Count Even Number is these"+ cnt);
		
		sc.close();
	}
}
