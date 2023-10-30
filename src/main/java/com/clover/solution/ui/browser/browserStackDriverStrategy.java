package com.clover.solution.ui.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class browserStackDriverStrategy extends browserDriverStrategy
{
	private ChromeOptions options;
	
    public WebDriver getDriverInstance() 
    {
    	
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("start-maximized"); // open Browser in maximized mode
            options.addArguments("disable-infobars"); // disabling infobars
            options.addArguments("--disable-extensions"); // disabling extensions
            options.addArguments("--disable-gpu"); // applicable to windows os only
            options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
            options.addArguments("--no-sandbox"); // Bypass OS security model
            options.addArguments("window-size=1920,1080");
            //options.addArguments(Arrays.asList("--window-position=0,0"));
           // options.addArguments(Arrays.asList("--window-size=1920,1080"));

            WebDriverManager.chromedriver().setup();
            
            return new ChromeDriver(options);
    }
    
}