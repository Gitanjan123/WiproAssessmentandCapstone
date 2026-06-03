package com.wipro.AssignmentDay7;
import java.util.*;
public class ShoppingCart {
	public static void main(String args[])
	{
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the cart size");
		int n=sc.nextInt();
		sc.nextLine();
		ArrayList<String>cart=new ArrayList<>();
		System.out.println("Enter items:");
		for(int i=0;i<n;i++)
		{
			cart.add(sc.nextLine());
		}
		HashSet<String>st=new HashSet<>();
		String ans="";
		for(int i=0;i<cart.size();i++)
		{
			if(st.contains(cart.get(i)))
			{
				ans=ans+cart.get(i)+" ";
			}
			else
			{
				st.add(cart.get(i));
			}
		}
		System.out.println("Cart Items is these");
		for(String item:cart)
		{
			System.out.println(item);
		}
		
		
	}
}
