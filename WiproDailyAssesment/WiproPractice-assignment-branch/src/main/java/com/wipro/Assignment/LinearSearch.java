package com.wipro.Assignment;
import java.util.Scanner;
public class LinearSearch {
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the value of n-->\n");
		int n=sc.nextInt();
		int a[]=new int[n];
		System.out.println("Enter the elements in the array");
		for(int i=0;i<n;i++)
		{
			a[i]=sc.nextInt();
		}
		System.out.println("Enter the element to be found");
		int element=sc.nextInt();
		boolean found=false;
		for(int i=0;i<n;i++)
		{
			if(a[i]==element)
			{
				System.out.println("Element have been found in position:"+i);
				found=true;
				break;
			}
		}
		if(!found) {
		System.out.println("Element are not present in the array");
		}
		sc.close();
	}
}
