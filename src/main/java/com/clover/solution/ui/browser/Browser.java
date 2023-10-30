package com.clover.solution.ui.browser;

import lombok.Getter;
import lombok.Setter;

public enum Browser {
	
	CHROME("chrome"),
	CHROME_HEADLESS("chromeHeadless"),
	FIREFOX("firefox"),
	EDGE("edge");
	
	@Getter @Setter
	private final String Browser;
	
	Browser(String Browser)
	{
		this.Browser = Browser;
	}
	
	@Override
	public String toString() {
		return Browser;
	}

}
