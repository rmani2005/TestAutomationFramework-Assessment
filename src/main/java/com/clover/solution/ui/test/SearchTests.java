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

public class SearchTests extends BaseTest 
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
    	enabled = false,
    	alwaysRun=false)
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
    
    @Test(priority=1,
    	 description="TC002 check the search engine Google - Normal",
    	 alwaysRun=true)
    public void TC002_check_the_search_engine_Google_Normal(Method method) {
        
    	//Starting the Reporting
    	ExtentTestManager.startTest(method.getName(), "TC002 check the search engine Google");
    	
    	//Test Starts here
    	searchPage.searchAndAssert("googleUrl", "cnbc", "CNBC: Stock Markets, Business News, Financials, Earnings");
    	
    }
    
    @Test(priority=2,
    	 description="TC003 check the search engine Google - type 2",
       	 alwaysRun=true)
    public void TC003_check_the_search_engine_Google_type2(Method method) {
        //ExtentReports Description
    	ExtentTestManager.startTest(method.getName(), "TC003 check the search engine Google - different type 2");
    	
    	//Test Starts here
    	searchPage.gotoURL("googleUrl")
    			.enterValueInSearchTextBox("cnbc")
    			.clickGoogleSearchButton()
    			.assertTheFirstValue("CNBC: Stock Markets, Business News, Financials, Earnings");
        }
    
    
    @Test(priority=3,
       	description="TC004 check the search engine Bing - using excel data",
       	enabled = false,
        alwaysRun=false)
       public void TC004_check_the_search_engine_bing_excel(Method method) {
           //ExtentReports Description
       	ExtentTestManager.startTest(method.getName(), "TC004 check the search engine Bing - using excel data");
       	
       	//Data Read from Excel Read
       	
       	searchPage.gotoURL("googleUrl")
       			.enterValueInSearchTextBox("cnbc")
       			.clickGoogleSearchButton()
       			.assertTheFirstValue("CNBC: Stock Markets, Business News, Financials, Earnings");
           }
    

    @Test(priority=2,
       	 description="TC005 check the search engine bing - using DataProvider",
       	 enabled = false,
         alwaysRun=false)
       public void TC005_check_the_search_engine_bing_dataprovider(Method method) {
           //ExtentReports Description
       	ExtentTestManager.startTest(method.getName(), "TC005 check the search engine Bing - using DataProvider");
       	
      //Data Read from Excel Read
      //And Dataprovider
       	
       	searchPage.gotoURL("googleUrl")
       			.enterValueInSearchTextBox("cnbc")
       			.clickGoogleSearchButton()
       			.assertTheFirstValue("CNBC: Stock Markets, Business News, Financials, Earnings");
           }
    

    @AfterMethod(alwaysRun=true)
    public void postCondition() 
    {
    	getDriver().manage().deleteAllCookies();
    	Log.info("Clearing the cookies");
    	Log.info("WebDriver is cooling down");
    	DriverManager.close();
        
    }
    
    @DataProvider(name="")
    public Object[][] testDataProvider()
    {
    	
    	return null;
    }
    
	
}