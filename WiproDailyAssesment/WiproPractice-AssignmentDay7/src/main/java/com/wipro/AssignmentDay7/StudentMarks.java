package com.wipro.AssignmentDay7;

import java.util.*;
public class StudentMarks {
	public static void main(String args[])
	{
		ArrayList<Integer>marks=new ArrayList<>();
		
		marks.add(80);
		marks.add(65);
		marks.add(90);
		marks.add(75);
		
		int max=Collections.max(marks);
		int min=Collections.min(marks);
		
		double sum=0;
		for(int m:marks)
		{
			sum+=m;
		}
		double avg=sum/marks.size();
		
		System.out.println("Marks:"+marks);
		System.out.println("Highest:"+max);
		System.out.println("Lowest:"+ min);
		System.out.println("Average:"+avg);
	}
}
