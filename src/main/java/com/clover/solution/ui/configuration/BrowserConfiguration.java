package com.clover.solution.ui.configuration;

import lombok.Getter;
import lombok.Setter;

import com.clover.solution.ui.browser.Browser;

public class BrowserConfiguration {

	 @Setter @Getter private String browser;
	
	
	 public BrowserConfiguration(String browser) {
	        this.browser = browser;
	        //Browser s=Browser.CHROME;
	    }
	 
	 public void setBrowser(String browser) {
			// TODO Auto-generated method stub
			this.browser=browser;
		}


	public String getBrowser() {
		// TODO Auto-generated method stub
		return browser;
	}
}
