package com.clover.solution.ui.page;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.clover.solution.ui.objectFindStrategy.FindStrategy;
import com.clover.solution.ui.objectFindStrategy.FindStrategyPool;
import com.clover.solution.ui.objectFindStrategy.IdFindStrategy;
import com.clover.solution.ui.util.extentreports.ExtentTestManager;
import com.clover.solution.ui.util.logs.Log;
import com.clover.solution.ui.waitstrategies.*;
import com.clover.solution.ui.webservices.*;

public class BasePage{
	
    protected static WebDriver driver;
    public static  WebDriverWait wait;
    private static WaitStrategy TobeClickable;
    private static String classname;
    private FindStrategyPool find;
	private WaitStrategyPool waitpool;
    //
    private static String url_google = "";
    private static String url_yahoo = "";
    private static String url_bing= "";
    private static String url_clover = "";
    
    //Load all timeouts here //Later load through waitStrategyPool
	private static ToBeClickableWaitStrategy ToBeClickableWaitStrategy=new ToBeClickableWaitStrategy();
	private static ToBeDisabledWaitStrategy ToBeDisabledWaitStrategy=new ToBeDisabledWaitStrategy();
	private static ToBeVisibleWaitStrategy ToBeVisibleWaitStrategy=new ToBeVisibleWaitStrategy();
	private static ToExistWaitStrategy ToExistWaitStrategy=new ToExistWaitStrategy();
	private static ToHaveContentWaitStrategy ToHaveContentWaitStrategy=new ToHaveContentWaitStrategy();
	private static ToNotBeVisibleWaitStrategy ToNotBeVisibleWaitStrategy=new ToNotBeVisibleWaitStrategy();
	private static ToNotExistWaitStrategy ToNotExistWaitStrategy=new ToNotExistWaitStrategy(30,1);
	private static ToShadowRootToBeAttachedWaitStrategy ToShadowRootToBeAttachedWaitStrategy=new ToShadowRootToBeAttachedWaitStrategy();

	//Load all WebServices here //Later load through Webservice master class
	private JavaScriptService javaScript=new JavaScriptService();
	private DialogService dialogService=new DialogService();
	private NavigationService navigateService=new NavigationService();
	private CookiesService cookiesService=new CookiesService();
	
    //Constructor
    public BasePage(WebDriver webDriver) {
        BasePage.driver = webDriver;
        classname=this.getClass().getName();
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        javaScript=new JavaScriptService();
        dialogService=new DialogService();
        navigateService=new NavigationService();
        
        //FindStratergy
        find=new FindStrategyPool();
        
        //WaitStrategy
        waitpool=new WaitStrategyPool();
    	TobeClickable=new ToBeClickableWaitStrategy();
    }
    
    //Navigate Service
    protected void navigateToURL(String URL) {
    	try {
    		driver.get(URL);
    		ExtentTestManager.getTest().log(Status.PASS, "NavigateToURL - Navigated to URL - " + URL);
    	}catch(Exception e)
    	{
    		ExtentTestManager.getTest().log(Status.FAIL, classname + "-Exception in navigateToURL" + e.getMessage());
    		Log.error(classname + "-Exception in navigateToURL" + e.getMessage());
    	}
    }
    
    //clickTheElement
    protected void clickTheElement(WebElement element) {
    	try {
    		element.click();
    		ExtentTestManager.getTest().log(Status.PASS, "ClickTheElement - Clicked the element - " + element);
    	}catch (StaleElementReferenceException | NoSuchElementException e) {
         	Log.error(classname + "-Exception in clickTheElement");
        }
    	catch(Exception e)
    	{
    		ExtentTestManager.getTest().log(Status.FAIL, classname + "-Exception in clickTheElement" + e.getMessage());
    		Log.error(classname + "-Exception in clickTheElement" + e.getMessage());
    	}
    }
    
    //enterTheText
    protected void enterTheText(WebElement element, String value) {
    	try {
    		element.sendKeys(value);
    		ExtentTestManager.getTest().log(Status.PASS, "EnterTheText - Entered the value - " + value +" in " + element);
    	}catch (StaleElementReferenceException | NoSuchElementException e) {
         	Log.error(classname + "-Exception in clickTheElement");
        }
    	catch(Exception e)
    	{
    		ExtentTestManager.getTest().log(Status.FAIL, classname + "-Exception in enterTheText" + e.getMessage());
    		Log.error(classname + "-Exception in clickTheElement" + e.getMessage());
    	}
    }
    
    //enterTheText
    protected void enterTheTextAndTab(WebElement element, String value) {
    	try {
    		element.sendKeys(value + Keys.TAB);
    	}catch (StaleElementReferenceException | NoSuchElementException e) {
         	Log.error(classname + "-Exception in clickTheElement");
        }
    	catch(Exception e)
    	{
    		ExtentTestManager.getTest().log(Status.FAIL, classname + "-Exception in enterTheTextAndTab" + e.getMessage());
    		Log.error(classname + "-Exception in clickTheElement" + e.getMessage());
    	}
    }
    
    //enterTheText
    protected String getTheElementText(WebElement element) {
    	try {
    		return element.getText();
    	}catch (StaleElementReferenceException | NoSuchElementException e) {
         	Log.error(classname + "-Exception in clickTheElement");
        }
    	catch(Exception e)
    	{
    		ExtentTestManager.getTest().log(Status.FAIL, classname + "-Exception in getTheElementText" + e.getMessage());
    		Log.error(classname + "-Exception in clickTheElement" + e.getMessage());
    	}
    	return "ERROR";
    }
    
    //JSClick
    protected void JSClick(WebElement element) {
    	try {
    		javaScript.execute("arguments[0].click();", element);
    	}catch (StaleElementReferenceException | NoSuchElementException e) {
         	Log.error(classname + "-Exception in JSClick");
        }
    	catch(Exception e)
    	{
    		ExtentTestManager.getTest().log(Status.FAIL, classname + "-Exception in getTheElementText" + e.getMessage());
    		Log.error(classname + "-Exception in JSClick" + e.getMessage());
    	}
    }
    
  //JSEnter
    protected void JSEnter(WebElement element) {
    	try {
    		element.click();
    	}catch (StaleElementReferenceException | NoSuchElementException e) {
         	Log.error(classname + "-Exception in clickTheElement");
        }
    	catch(Exception e)
    	{
    		ExtentTestManager.getTest().log(Status.FAIL, classname + "-Exception in getTheElementText" + e.getMessage());
    		Log.error(classname + "-Exception in clickTheElement" + e.getMessage());
    	}
    }
    
  //clickTheElement
    protected void JSScroll(WebElement element) {
    	try {
    		element.click();
    	}catch (StaleElementReferenceException | NoSuchElementException e) {
         	Log.error(classname + "-Exception in clickTheElement");
        }
    	catch(Exception e)
    	{
    		ExtentTestManager.getTest().log(Status.FAIL, classname + "-Exception in getTheElementText" + e.getMessage());
    		Log.error(classname + "-Exception in clickTheElement" + e.getMessage());
    	}
    }
    
    //Common Methods
    protected WebElement element(WebDriver driver, final By by)
    {
    	try {
    		if(driver!=null){
    			//Enabled
				ToBeClickableWaitStrategy.waitUntil(driver, by);
				//visibilityOfElementLocated
				//ToBeClickableWaitStrategy.waitUntil(driver, by);
				//Move to that object
				//ToBeClickableWaitStrategy.waitUntil(driver, by);
				//
				ExtentTestManager.getTest().log(Status.PASS, " Finding the Element using findStrategy and waitStrategy" + by);
	    	}
			return driver.findElement(by);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExtentTestManager.getTest().log(Status.FAIL, classname + "Error in getElement with Expected Condition" + e.getMessage());
			Log.error("Error in getElement with Expected Condition");
			return null;
		}	
    }
        
    //If possible move to other SuperClass 
	protected WebElement getElement(WebDriver driver, final By by,String ExpectedCondition)
	{
		try {
			switch(ExpectedCondition)
			{
				case "TOBECLICK":
					ToBeClickableWaitStrategy.waitUntil(driver, by);
					break;
				case "TOBEENABLED":
					ToBeClickableWaitStrategy.waitUntil(driver, by);
					break;
					//to be continued
				default:
					break;
			}		
			
			return driver.findElement(by);
			
		} catch (StaleElementReferenceException | NoSuchElementException e) {
			// TODO Auto-generated catch block
			Log.error("Error in getElement with Expected Condition");
			return null;
		}	
	}
	
	protected boolean isElementReady(WebDriver driver, final By by,String ExpectedCondition)
	{
		try {
			switch(ExpectedCondition)
			{
				case "TOBECLICK":
					ToBeClickableWaitStrategy.waitUntil(driver, by);
					break;
				case "TOBEENABLED":
					ToBeClickableWaitStrategy.waitUntil(driver, by);
					break;
					//to be continued
				default:
					break;
			}		
			
			return true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.error("Error in getElement with Expected Condition");
			return false;
		}	
	}
	
	protected List<WebElement> getElements(WebDriver driver, final By by,String ExpectedCondition)
	{
		try {
			switch(ExpectedCondition)
			{
				case "TOBECLICK":
					ToBeClickableWaitStrategy.waitUntil(driver, by);
					break;
				case "TOBEENABLED":
					ToBeClickableWaitStrategy.waitUntil(driver, by);
					break;
					//to be continued
				default:
					break;
			}		
			
			return driver.findElements(by);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.error("Error in getElement with Expected Condition");
			return null;
		}	
	}
	

	protected boolean isElementsReady(WebDriver driver, final By by,String ExpectedCondition)
	{
		try {
			switch(ExpectedCondition)
			{
				case "TOBECLICK":
					ToBeClickableWaitStrategy.waitUntil(driver, by);
					break;
				case "TOBEENABLED":
					ToBeClickableWaitStrategy.waitUntil(driver, by);
					break;
					//to be continued
				default:
					break;
			}		
			
			return true;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.error("Error in getElement with Expected Condition");
			return false;
		}	
	}
	
	//Assertion Library
	protected void assertEqual(String Actual, String expected)
	{
		Log.info("AssertEquals: Actual - " + Actual );
		Log.info("AssertEquals: Expected - " + expected );
		if(Actual.equals(expected))
			ExtentTestManager.getTest().log(Status.PASS, "AssertEquals: Actual-" + Actual + " with Expected-" + expected );
		else
			ExtentTestManager.getTest().log(Status.FAIL, "AssertEquals: Actual-" + Actual + " with Expected-" + expected );
		Assert.assertEquals(Actual, expected);
	}
	
		
	protected void assertTrue(boolean value)
	{
		Assert.assertTrue(value);
		ExtentTestManager.getTest().log(Status.PASS, "AssertTrue:" + value);
	}
	
	protected void assertPageTitle(String expected)
	{
		String Actualtitle=driver.getTitle();
		Log.info("AssertPageTitle: Actual - " + Actualtitle );
		Log.info("AssertPageTitle: Expected - " + expected );
		if(Actualtitle.equals(expected))
			ExtentTestManager.getTest().log(Status.PASS, "AssertPageTitle: Actual-" + Actualtitle + " with Expected-" + expected );
		else
			ExtentTestManager.getTest().log(Status.FAIL, "AssertPageTitle: Actual-" + Actualtitle + " with Expected-" + expected );
		Assert.assertEquals(Actualtitle, expected);
	}
	

	protected void assertPageTitleContains(String expected)
	{
		String Actualtitle=driver.getTitle();
		Log.info("AssertPageTitle: Actual - " + Actualtitle );
		Log.info("AssertPageTitle: Expected - " + expected );
		if(Actualtitle.contains(expected)) {
			ExtentTestManager.getTest().log(Status.PASS, "AssertPageTitle: Actual-" + Actualtitle + " with Expected-" + expected );
			Assert.assertTrue(true);
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL, "AssertPageTitle: Actual-" + Actualtitle + " with Expected-" + expected );
			Assert.assertTrue(false);
		}
	}
	
	protected void assertPageURL(String expected)
	{
		String ActualPageURL=driver.getCurrentUrl();
		Log.info("AssertPageURL: Actual - " + ActualPageURL );
		Log.info("AssertPageURL: Expected - " + expected );
		if(ActualPageURL.equals(expected))
			ExtentTestManager.getTest().log(Status.PASS, "AssertPageURL: Actual-" + ActualPageURL + " with Expected-" + expected );
		else
			ExtentTestManager.getTest().log(Status.FAIL, "AssertPageURL: Actual-" + ActualPageURL + " with Expected-" + expected );
		Assert.assertEquals(ActualPageURL, expected);
	}
	
	protected void PageURLContains(String containsString)
	{
		String ActualPageURL=driver.getCurrentUrl();
		Log.info("AssertPageURL: Actual - " + ActualPageURL );
		Log.info("AssertPageURL: Expected - " + containsString );
		if(ActualPageURL.contains(containsString)) {
			ExtentTestManager.getTest().log(Status.PASS, "AssertEuqls: Actual-" + ActualPageURL + " with Expected-" + containsString );
			Assert.assertTrue(true);
		}
		else {
			ExtentTestManager.getTest().log(Status.FAIL, "AssertEuqls: Actual-" + ActualPageURL + " with Expected-" + containsString );
			Assert.assertTrue(true);
		}
	}
	
	
    
}