package com.wipro.DIDemo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("bike")
@Primary
public class Bike implements Vehicle{
	public void start()
	{
		System.out.println("Bike is starting");
	}
}
