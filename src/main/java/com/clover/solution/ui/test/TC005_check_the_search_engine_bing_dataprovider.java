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
import com.clover.solution.ui.reader.excelReader;

public class TC005_check_the_search_engine_bing_dataprovider extends BaseTest 
{
	String ExcelDataFileName;
	
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
       	 description="TC005 check the search engine bing - using DataProvider",
       	 enabled = true,
         alwaysRun=true,
         dataProvider="testDataProvider")
       public void TC005_check_the_search_engine_bing_dataprovider1(Method method) {
           //ExtentReports Description
       	ExtentTestManager.startTest(method.getName(), "TC005 check the search engine Bing - using DataProvider");
       	ExcelDataFileName=method.getName();
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
    	//DriverManager.close();
        
    }
    
    @DataProvider
    public Object[][] testDataProvider()
    {	
    	excelReader excel=new excelReader(ExcelDataFileName);
    	
    	Object[][] testObjArray=null;
		try {
			testObjArray = excel.ExcelReaderDataProvider(ExcelDataFileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return (testObjArray);
    	
    }
    
	
}