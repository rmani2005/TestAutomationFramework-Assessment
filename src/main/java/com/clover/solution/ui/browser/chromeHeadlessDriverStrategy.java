package com.clover.solution.ui.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class chromeHeadlessDriverStrategy extends browserDriverStrategy
{
	private ChromeOptions options;
	
    
	public WebDriver getDriverInstance_backup() 
    {
			String currentDirectory=System.getProperty("user.dir");
			String fileName=currentDirectory + "//src//main//java//com//clover//solution//ui//browser//chromedriver";
			
			System.setProperty("webdriver.chrome.driver", fileName);
            options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--headless=new");
            options.addArguments("--headless");

            
            //options.addArguments(Arrays.asList("--window-position=0,0"));
           // options.addArguments(Arrays.asList("--window-size=1920,1080"));

            //WebDriverManager.chromedriver().setup();
            
            return new ChromeDriver(options);
    }
	
	
	public WebDriver getDriverInstance() 
    {
    	
            options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--headless=new");
            options.addArguments("--headless");
            options.addArguments("start-maximized"); // open Browser in maximized mode
            options.addArguments("disable-infobars"); // disabling infobars
            options.addArguments("--disable-extensions"); // disabling extensions
            options.addArguments("--disable-gpu"); // applicable to windows os only
            options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
            options.addArguments("--log-level=3","--remote-allow-origins=*");
            //For Running in EC2
            options.addArguments("--remote-debugging-port=9222");
            
             // Bypass OS security model
            //options.addArguments("window-size=1920,1080");
           
            //options.setAcceptInsecureCerts(true);
            //System.setProperty("webdriver.chrome.silentOutput", "true");
            //options.addArguments(Arrays.asList("--window-position=0,0"));
           // options.addArguments(Arrays.asList("--window-size=1920,1080"));

            WebDriverManager.chromedriver().setup();
            
            return new ChromeDriver(options);
    }
    
}