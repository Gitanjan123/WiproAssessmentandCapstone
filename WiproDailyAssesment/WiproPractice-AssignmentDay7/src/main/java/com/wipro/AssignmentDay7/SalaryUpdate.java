package com.wipro.AssignmentDay7;

import java.util.ArrayList;

public class SalaryUpdate {
	public static void main(String args[])
	{
		ArrayList<Double>arr=new ArrayList<>();
		arr.add((double) 20000);
		arr.add((double) 30000);
		arr.add((double) 40000);
		
		for(int i=0;i<arr.size();i++)
		{
			double updated=arr.get(i)*0.10;
			
			arr.set(i, updated);
		}
		
		System.out.println("Updated Salaried"+ arr);
		
	}
}
