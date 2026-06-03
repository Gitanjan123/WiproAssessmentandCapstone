package com.wipro.AssignmentDay7;
import java.util.*;
public class MovieWatchList {
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the size");
		int n=sc.nextInt();
		sc.nextLine();
		ArrayList<String> arr=new ArrayList<>();
		System.out.println("Enter the movie names:");
		for(int i=0;i<n;i++)
		{
			arr.add(sc.nextLine());
		}
		System.out.println("Enter the movie to search which movie you are finding");
		String str=sc.nextLine();
		boolean found=false;
		for(String movie:arr)
		{
			if(movie.equalsIgnoreCase(str))
			{
				found=true;
				break;
			}
		}
		if(found)
		{
			System.out.println("Yes the movie is present");
		}
		else
		{
			System.out.println("No the movie is not present");
		}
	}
}
