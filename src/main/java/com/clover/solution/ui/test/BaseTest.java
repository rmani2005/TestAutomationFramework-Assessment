package com.clover.solution.ui.test;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;

import com.clover.solution.ui.browser.DriverManager;
import com.clover.solution.ui.configuration.ConfigProvider;
import com.clover.solution.ui.page.CloverPage;
import com.clover.solution.ui.page.SearchPage;
import com.clover.solution.ui.reader.jsonReader;
import com.clover.solution.ui.reader.excelReader;
import com.clover.solution.ui.util.extentreports.ExtentManager;
import com.clover.solution.ui.util.logs.Log;

public class BaseTest 
{
    protected static DriverManager Master_driver;
    private static ConfigProvider config;
    private static jsonReader jsonreader;
    private static excelReader excelReader;
    private static String className="BaseTest";
    
    //Pages
	//page object creation section
    public static SearchPage searchPage;
    public static CloverPage cloverPage;
    //public static NBCPage cnbcPage;
    //public static CloverHomePage cloverHomePage;
    //public static CloverPricingPage cloverPricingPage;
    
    protected BaseTest()
    {
    	Log.info(className +"-Creating the --ConfigProvider-- object for whole test execution flow");
    	config=new ConfigProvider();
    	Log.info(className +"-Creating the --DriverManager-- object for whoel test execution flow");	
    }

    
    //This is master gerDriver instances to be used for all over the framework
    public static WebDriver getDriver() {
        return DriverManager.getMasterDriver();
    }
    
    
    
    @AfterSuite
    public static void cooldown() {
        Log.info(className + "Driver is cooling down & Tests are ending!");
        DriverManager.close();
        ExtentManager.extentReports.flush();
    }
    
    protected void beforeAll() throws Exception {
    }
    
    protected void afterAll() throws Exception {
    }

    protected void beforeEach() throws Exception {
    }

    protected void afterEach() {
    }
    
    protected List<LinkedHashMap<String,String>> jsonFileReader(String TestCaseName)
    {
    	jsonreader=new jsonReader(TestCaseName);
    	return jsonreader.getJsonData();
    }
    
    protected List<LinkedHashMap<String,String>> excelFileReader(String TestCaseName)
    {
    	excelReader=new excelReader(TestCaseName);
    	return excelReader.getExcelData();
    }
    
}