package com.clover.solution.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.clover.solution.ui.configuration.ConfigProvider;
import com.clover.solution.ui.objectFindStrategy.FindStrategy;
import com.clover.solution.ui.objectFindStrategy.FindStrategyPool;
import com.clover.solution.ui.objectFindStrategy.IdFindStrategy;
import com.clover.solution.ui.objectFindStrategy.XPathFindStrategy;
import com.clover.solution.ui.util.logs.Log;

public class SearchPage extends BasePage 
{

	private String pageName;
	public SearchPage(WebDriver driver) {
	        super(driver);
	        pageName=this.getClass().getName();
	        Log.info(pageName + "Creating page constractor and its super");
	       	    	
	}

	//Page Objects
	//FindStrategyPool find;
	//Google
	By searchTextBoxElement = new XPathFindStrategy("//textarea[@aria-label='Search']").convert();
	By searchButtonElement = new XPathFindStrategy("(//input[@value='Google Search'])[2]").convert();
	By firstValueSearchValueh3 = new XPathFindStrategy("(//div[contains(@data-async-context,'query:')]//span/a/h3)[1]").convert();
   
	//Bing
	By searchTextBoxElement_bing = new IdFindStrategy("sb_form_q").convert(); //textarea[@id='sb_form_q']
	By cnbcOptionElement = new XPathFindStrategy("//ul//li//div//span[text()='cnbc']").convert();
	By firstValueSearchValueh2_bing= new XPathFindStrategy("//h2//a[@href='https://www.cnbc.com/']").convert();
	//h2//a[@href='https://www.cnbc.com/']
	//
	
    //Test Steps starts here
    public SearchPage searchAndAssert(String URLName,String SearchValue,String AssertValue )
    {
    	if(driver!=null) {
    		String URL=ConfigProvider.getAsString("urlSettings."+ URLName);
			navigateToURL(URL);
			//Page title Assertion - cnbc - Google Search
			//Assert the page text some thing like header
			enterTheTextAndTab(element(driver, searchTextBoxElement), SearchValue);
			clickTheElement(element(driver, searchButtonElement));
			//clickTheElement(this.element(driver, idFindStrategyLogginButton.convert()));
			//Assert the Value
			String actualString = getTheElementText(element(driver,firstValueSearchValueh3));
			assertEqual(actualString,AssertValue);
			//"CNBC: Stock Markets, Business News, Financials, Earnings"
    	}else{
    		Log.error("Error in Webdriver while searchAndAssert");
    	}
		return this;	
    }
    
    //Test Steps starts here
    public SearchPage searchAndAssert_bing(String URLName,String SearchValue,String AssertValue )
    {
    	if(driver!=null) {
    		String URL=ConfigProvider.getAsString("urlSettings."+ URLName);
			navigateToURL(URL);
			//Page title Assertion - cnbc - Google Search
			//Assert the page text some thing like header
			enterTheText(element(driver, searchTextBoxElement_bing), SearchValue);
			clickTheElement(element(driver, cnbcOptionElement));
			//clickTheElement(this.element(driver, idFindStrategyLogginButton.convert()));
			//Assert the Value
			String actualString = getTheElementText(element(driver,firstValueSearchValueh2_bing));
			assertEqual(actualString,AssertValue);
			//"CNBC: Stock Markets, Business News, Financials, Earnings"
    	}else{
    		Log.error("Error in Webdriver while searchAndAssert");
    	}
		return this;	
    }
    
    public SearchPage gotoURL(String URLVariable)
    {
    	if(driver!=null) {
    		String URL=ConfigProvider.getAsString("urlSettings."+ URLVariable);
			navigateToURL(URL);
    	}else{
    		Log.error("Error in Webdriver while searchAndAssert");
    	}
		return this;
    }
    
    public SearchPage enterValueInSearchTextBox(String SearchValue)
    {
    	if(driver!=null) {
    		enterTheTextAndTab(element(driver, searchTextBoxElement), SearchValue);
    	}else{
    		Log.error("Error in Webdriver while searchAndAssert");
    	}
		return this;
    }
    
    
    public SearchPage clickGoogleSearchButton()
    {
    	if(driver!=null) {
    		clickTheElement(element(driver, searchButtonElement));
    	}else{
    		Log.error("Error in Webdriver while searchAndAssert");
    	}
		return this;
    }
    
    public SearchPage assertTheFirstValue(String AssertValue)
    {
    	if(driver!=null) {
    		String actualString = getTheElementText(element(driver,firstValueSearchValueh3));
			assertEqual(actualString,AssertValue);
    	}else{
    		Log.error("Error in Webdriver while searchAndAssert");
    	}
		return this;
    }
    
    
  /* 
    public HomePage goToN11() {
        Log.info("Opening N11 Website.");
        driver.get(baseURL);
        return this;
    }
    
    //Go to LoginPage
    public LoginPage goToLoginPage() {
        Log.info("Going to Login Page..");
        click(signInButtonClass);
        return new LoginPage(driver);
    }
    */
}