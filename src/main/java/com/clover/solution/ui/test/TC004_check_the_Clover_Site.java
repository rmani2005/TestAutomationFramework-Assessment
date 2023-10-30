package com.clover.solution.ui.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.clover.solution.ui.browser.DriverManager;
import com.clover.solution.ui.configuration.ConfigProvider;
import com.clover.solution.ui.page.CloverPage;
import com.clover.solution.ui.page.SearchPage;
import com.clover.solution.ui.util.extentreports.ExtentTestManager;
import com.clover.solution.ui.util.logs.Log;

public class TC004_check_the_Clover_Site extends BaseTest 
{

    
    @BeforeMethod(alwaysRun=true)
    public static void preCondition() 
    {
    	Log.info("Tests is starting!");
    	Master_driver=new DriverManager();
    	Log.info("Starting the Test & Opening up the WebDriver");
    	//getDriver().manage().deleteAllCookies();
    	cloverPage = new CloverPage(getDriver());
    	Log.info("Clearing the cookies");    
    }
    
    @Test(priority=0,
    	description="TC004 check the clover Site using JSON",
    	enabled = true,
    	alwaysRun=true)
        public void TC004_check_the_Clover_Site_JSON(Method method) {
            List<LinkedHashMap<String,String>> dataMap=new LinkedList<LinkedHashMap<String,String>>();
    		//Reading the data from JSON
            dataMap=jsonFileReader(method.getName());
    		//ExtentReports Description
          	ExtentTestManager.startTest(method.getName(), "TC004 check the clover Site using JSON");
          	
          	String url=dataMap.get(0).get("engine_url_variable");
          	String validation_String=dataMap.get(0).get("search_assert_value");
          	String title_String=dataMap.get(0).get("pageTitle");
          	String url_String=dataMap.get(0).get("UrlContians");
          	String ExpectedPricingString=dataMap.get(0).get("ExpectedPricingString");
          	//The all‑in‑one system to help your business thrive
          	
          	cloverPage.cloverSiteCheck(url,validation_String, title_String, url_String, ExpectedPricingString);
         }
    

    @AfterMethod(alwaysRun=true)
    public void postCondition() 
    {
    	getDriver().manage().deleteAllCookies();
    	Log.info("Clearing the cookies");
    	Log.info("WebDriver is cooling down");
    	//DriverManager.close();
        
    }
    
	
}

