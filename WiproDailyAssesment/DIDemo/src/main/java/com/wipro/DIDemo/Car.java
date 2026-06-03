package com.wipro.DIDemo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("car")

public class Car implements Vehicle
{
	public void start()
	{
		System.out.println("Car is starting");
	}
}