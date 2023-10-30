
package com.clover.solution.ui.waitstrategies;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.clover.solution.ui.configuration.ConfigProvider;
import com.sun.tools.sjavac.Log;

public class ToBeClickableWaitStrategy extends WaitStrategy {
    public ToBeClickableWaitStrategy() {
    	timeoutInterval=ConfigProvider.getAsInt("webSettings.timeoutSettings.elementWaitTimeoutelementToBeClickableTimeout");
    	sleepInterval=ConfigProvider.getAsInt("webSettings.timeoutSettings.elementWaitTimeoutsleepInterval");
    }

    public static ToBeClickableWaitStrategy of() {
        return new ToBeClickableWaitStrategy();
    }

    public ToBeClickableWaitStrategy(long timeoutIntervalSeconds, long sleepIntervalSeconds) {
        super(timeoutIntervalSeconds, sleepIntervalSeconds);
    }

    @Override
    public void waitUntil(final SearchContext searchContext, final By by) {
        waitUntil(new Function<SearchContext, Boolean>() {
			@Override
			public Boolean apply(SearchContext x) {
				return elementIsClickable(searchContext, by);
			}
		});
    }
  
    private boolean elementIsClickable(SearchContext searchContext, By by) {
        WebElement element = findElement(searchContext, by);
        try {
            return element != null && element.isEnabled() ;
        } catch (StaleElementReferenceException | NoSuchElementException e) {
        	//Log.error(className + "-Exception in elementHandle" );
            return false;
        }
    }
    
    //To reduce the code complexity in BasePage class 
    public WebElement getObject(final SearchContext searchContext, final By by)
    {
    	try {
    	if(waitUntil(new Function<SearchContext, Boolean>() {
			@Override
			public Boolean apply(SearchContext x) {
				return elementIsClickable(searchContext, by);
			}}))
    	{//if starts here
    		return findElement(searchContext, by);
    	}
    	}catch (StaleElementReferenceException | NoSuchElementException e) {
         	//Log.error(className + "-Exception in elementHandle" );
            return null;
        }
    	return null;
    	
    }
}
