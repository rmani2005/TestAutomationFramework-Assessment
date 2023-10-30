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
import com.clover.solution.ui.page.SearchPage;
import com.clover.solution.ui.util.extentreports.ExtentTestManager;
import com.clover.solution.ui.util.logs.Log;

public class TC001_check_the_search_engine_google_JSON extends BaseTest 
{
	
    @BeforeMethod(alwaysRun=true)
    public static void preCondition() 
    {
    	Log.info("Tests is starting!");
    	Master_driver=new DriverManager();
    	Log.info("Starting the Test & Opening up the WebDriver");
    	//getDriver().manage().deleteAllCookies();
    	searchPage = new SearchPage(getDriver());
    	Log.info("Clearing the cookies");    
    }
    
    @Test(priority=0,
    	description="TC001 check the search engine Google - using JSON",
    	enabled = true,
    	alwaysRun=true)
        public void TC001_check_the_search_engine_google_JSON(Method method) {
            List<LinkedHashMap<String,String>> dataMap=new LinkedList<LinkedHashMap<String,String>>();
    		//Reading the data from JSON
            dataMap=jsonFileReader(method.getName());
    		//ExtentReports Description
          	ExtentTestManager.startTest(method.getName(), "TC001 check the search engine Google - using JSON input");
          	
          	String url=dataMap.get(0).get("engine_url_variable");
          	String search_key=dataMap.get(0).get("search_string");
          	String validation_String=dataMap.get(0).get("search_assert_value");
          	
          	searchPage.gotoURL(url)
          			.enterValueInSearchTextBox(search_key)
          			.clickGoogleSearchButton()
          			.assertTheFirstValue(validation_String);
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