package com.clover.solution.ui.objectFindStrategy;

public class FindStrategyPool {

	public FindStrategyPool(){
		
	}
	
	  public static XPathFindStrategy xpathFind(String value) {
	        return new XPathFindStrategy(value);
	    }
	
}