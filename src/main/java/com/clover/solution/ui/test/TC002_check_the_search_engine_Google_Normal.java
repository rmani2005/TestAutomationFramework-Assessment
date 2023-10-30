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

public class TC002_check_the_search_engine_Google_Normal extends BaseTest 
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
    	 description="TC002 check the search engine Google - Normal",
    	 alwaysRun=true)
    public void TC002_check_the_search_engine_Google_Normal1(Method method) {
        
    	//Starting the Reporting
    	ExtentTestManager.startTest(method.getName(), "TC002 check the search engine Google");
    	
    	//Test Starts here
    	searchPage.searchAndAssert("googleUrl", "cnbc", "CNBC: Stock Markets, Business News, Financials, Earnings");
    	
    }
    
    @Test(priority=1,
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

    @AfterMethod(alwaysRun=true)
    public void postCondition() 
    {
    	getDriver().manage().deleteAllCookies();
    	Log.info("Clearing the cookies");
    	Log.info("WebDriver is cooling down");
    	//DriverManager.close();
        
    }
    
    
	
}