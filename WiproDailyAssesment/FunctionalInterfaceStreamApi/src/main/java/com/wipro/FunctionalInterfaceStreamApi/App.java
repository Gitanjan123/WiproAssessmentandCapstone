package com.wipro.FunctionalInterfaceStreamApi;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Hello world!
 *
 */


// Predicate (test condition->returns true/false)
public class App 
{
    public static void main( String[] args )
    {
        Predicate<Integer>isEven=x->x%2==0;
        System.out.println(isEven.test(10));
        System.out.println(isEven.test(7));
    }
}

//Function (input->output transformation)

public class Main
{
	public static void main(String args[])
	{
		Function<Integer,Integer>square=x->x*x;
		System.out.println(square.apply(5));
	}
}
// Consumer (which takes input does not return anything)
public class Main
{
	public static void main(String args[])
	{
		Consumer<String>print=s->System.out.println(s);
		print.accept("Hello stream Api");
	}
}
// Supplier
public class Main{
	public static void main(String agrs[])
	{
		Supplier<Double>randomvlaue=()->Math.random();
		System.out.println(randomvalue.get());
		
	}
}
