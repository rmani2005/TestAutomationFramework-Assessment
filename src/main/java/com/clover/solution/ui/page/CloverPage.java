package com.clover.solution.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.clover.solution.ui.configuration.ConfigProvider;
import com.clover.solution.ui.objectFindStrategy.FindStrategy;
import com.clover.solution.ui.objectFindStrategy.FindStrategyPool;
import com.clover.solution.ui.objectFindStrategy.IdFindStrategy;
import com.clover.solution.ui.objectFindStrategy.XPathFindStrategy;
import com.clover.solution.ui.util.logs.Log;

public class CloverPage extends BasePage 
{

	private String pageName;
	public CloverPage(WebDriver driver) {
	        super(driver);
	        pageName=this.getClass().getName();
	        Log.info(pageName + "Creating page constractor and its super");
	       	    	
	}

	//Page Objects
	//FindStrategyPool find;
	//Clover.com objects	
	By cookiesIunderstandCloseButton = new IdFindStrategy("_evidon-decline-button").convert();
	By homePageHeader = new XPathFindStrategy("//h2[contains(text(),'The all‑in‑one system to help your business thrive')]").convert();
	By PricingButton = new XPathFindStrategy("//nav/a[@title='Pricing']").convert();
	By MenuButton = new XPathFindStrategy("//button[contains(@data-testid,'menu-button')]").convert();	
	By PricingButtonTab = new XPathFindStrategy("//div[@data-testid='nav-drawer']//span[text()='Pricing']").convert();
	By PricingSiteHeader= new XPathFindStrategy("//p[contains(text(),'Find the right solution to power your business')]").convert();
	//header[@data-testid='nav-bar']//span[text()='Pricing']
	//Test Steps starts here
    public CloverPage cloverSiteCheck(String URLName,String AssertValue, String title, String urlContians, String ExpectedPricingString)
    {
    	if(driver!=null) {
    		String URL=ConfigProvider.getAsString("urlSettings."+ URLName);
			navigateToURL(URL);
			clickTheElement(element(driver, cookiesIunderstandCloseButton));
			assertPageTitleContains(title);
			PageURLContains(urlContians);
			
			//Page Assert
			String actualString = getTheElementText(element(driver,homePageHeader));
			assertEqual(actualString,AssertValue);
			
			if(ConfigProvider.getAsString("webSettings.defaultBrowser").equalsIgnoreCase("CHROMEHEADLESS")) {
				clickTheElement(element(driver, MenuButton));
				clickTheElement(element(driver, PricingButtonTab));
			}else
			{
				clickTheElement(element(driver, PricingButton));
			}
			
			//Page Assert - Pricing //Find the right solution to power your business
			String actualPricingString = getTheElementText(element(driver,PricingSiteHeader));
			assertEqual(actualPricingString,ExpectedPricingString);
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