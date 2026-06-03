package com.wipro.AssignmentDay8;


interface RouteStrategy
{
	void buildRoute(String from,String to);
}

class FastestRoute implements RouteStrategy
{
	@Override
	public void buildRoute(String from,String to)
	{
		System.out.println("[FASTEST]"+from+"->"+to+"via highway");
	}
}
class ShortestRoute implements RouteStrategy
{
	@Override
	public void buildRoute(String from,String to)
	{
		System.out.println("[SHORTEST]"+from+"->"+to+"via backroads");
	}
}

class AvoidTollRoute implements RouteStrategy
{
	@Override
	public void buildRoute(String from,String to)
	{
		System.out.println("[AVOID TOLLS]"+from+"->"+to+"via NHB");
		
	}
}
class Navigator
{
	private RouteStrategy strategy;
	public void setStrategy(RouteStrategy strategy)
	{
		this.strategy=strategy;
	}
	public void navigate(String from,String to)
	{
		if(strategy==null)
		{
			System.out.println("No strategy set!");
			return;
		}
		strategy.buildRoute(from, to);	
	}
}

public class NavigationSystem
{
	public static void main(String args[])
	{
		Navigator nav=new Navigator();
		System.out.println("======User picks Fastest===");
		nav.setStrategy(new FastestRoute());
		
		nav.navigate("Home", "Office");
		System.out.println("====== User switch to shortest====");
		nav.setStrategy(new ShortestRoute());
		
		nav.navigate("Home", "Office");
		
		System.out.println("=====User switch to avoid tolls======");
		nav.setStrategy(new AvoidTollRoute());
		
		nav.navigate("Home", "Office");
	}
}















public class NavigationSystem {

}
