package com.wipro.AssignmentDay7;

import java.util.ArrayList;

public class RecentSearches {
	public static void main(String args[])
	{
		int N=3;
		ArrayList<String>searches=new ArrayList<>();
		
		searches.add("Java");
		searches.add("C++");
		searches.add("Python");
		searches.add("React");
		
		
		while(searches.size()>N)
		{
			searches.remove(0);
		}
		
		System.out.println("Recent Searches");
		for(String s:searches)
		{
			System.out.println(s);
		}
	}
}
