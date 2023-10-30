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

public class TC004_check_the_search_engine_bing_exceldata extends BaseTest 
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
       	description="TC004 check the search engine Bing - using excel data",
       	enabled = true,
        alwaysRun=true)
       public void TC004_check_the_search_engine_bing_excel(Method method) {
         //ExtentReports Description
       	ExtentTestManager.startTest(method.getName(), "TC004 check the search engine Bing - using excel data");
       	
       	//Data Read from Excel Read
        List<LinkedHashMap<String,String>> dataMap=new LinkedList<LinkedHashMap<String,String>>();
		//Reading the data from JSON
        dataMap=excelFileReader(method.getName());
		//ExtentReports Description
      	//First row Data is for Google
        //Second row Data is for Bing
      	String url=dataMap.get(1).get("engine_url_variable");
      	String search_key=dataMap.get(1).get("search_string");
      	String validation_String=dataMap.get(1).get("search_assert_value");
       	
    	searchPage.searchAndAssert_bing(url,search_key,validation_String);
      }

    @AfterMethod(alwaysRun=true)
    public void postCondition() 
    {
    	getDriver().manage().deleteAllCookies();
    	Log.info("Clearing the cookies");
    	Log.info("WebDriver is cooling down");
    	//DriverManager.close();
        
    }
    
    @DataProvider(name="")
    public Object[][] testDataProvider()
    {
    	
    	return null;
    }
    
	
}