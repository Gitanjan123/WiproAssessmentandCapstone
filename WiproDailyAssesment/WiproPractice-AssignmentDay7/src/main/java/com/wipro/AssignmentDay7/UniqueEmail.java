package com.wipro.AssignmentDay7;

import java.util.HashSet;

public class UniqueEmail {
	public static void main(String args[])
	{
		HashSet<String> st=new HashSet<>();
		st.add("abc@gmail.com");
		st.add("bcd@gmail.com");
		st.add("abc@gmail.com");
		
		System.out.println("Registered Email is these"+ st);
	}
}
