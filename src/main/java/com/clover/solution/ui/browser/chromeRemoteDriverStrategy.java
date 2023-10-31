package com.clover.solution.ui.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class chromeRemoteDriverStrategy extends browserDriverStrategy
{
	private ChromeOptions options;
	
    public WebDriver getDriverInstanceRemote() 
    {    	
	    	options = new ChromeOptions();
	    	options.addArguments("--ignore-ssl-errors=yes");
	    	options.addArguments("--ignore-certificate-errors");
            options.addArguments("start-maximized"); // open Browser in maximized mode
            options.addArguments("disable-infobars"); // disabling infobars
            options.addArguments("--disable-extensions"); // disabling extensions
            options.addArguments("--disable-gpu"); // applicable to windows os only
            options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
            options.addArguments("--no-sandbox"); // Bypass OS security model
            options.addArguments("--log-level=3","--remote-allow-origins=*");
            
            //WebDriverManager.chromedriver().setup();
            
            //RemoteWebDriver driver = RemoteWebDriver.Remote(command_executor=’http://localhost:4444/wd/hub’,options=options)
            
            return new ChromeDriver();
    }

	@Override
	public WebDriver getDriverInstance() {
		// TODO Auto-generated method stub
		return null;
	}
    
}