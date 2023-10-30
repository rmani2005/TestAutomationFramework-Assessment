package com.clover.solution.ui.waitstrategies;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.clover.solution.ui.configuration.ConfigProvider;

import java.util.function.Function;

public class ToShadowRootToBeAttachedWaitStrategy extends WaitStrategy {
    public ToShadowRootToBeAttachedWaitStrategy() {
    	timeoutInterval=ConfigProvider.getAsInt("webSettings.timeoutSettings.elementWaitTimeoutelementToExistTimeout");
    	sleepInterval=ConfigProvider.getAsInt("webSettings.timeoutSettings.elementWaitTimeoutsleepInterval");
    }

    public ToShadowRootToBeAttachedWaitStrategy(long timeoutIntervalSeconds, long sleepIntervalSeconds) {
        super(timeoutIntervalSeconds, sleepIntervalSeconds);
    }

    public static ToShadowRootToBeAttachedWaitStrategy of() {
        return new ToShadowRootToBeAttachedWaitStrategy();
    }


    @Override
    public void waitUntil(final SearchContext searchContext, final By by) {
        waitUntil(new Function<SearchContext, Boolean>() {
			@Override
			public Boolean apply(SearchContext x) {
				return elementExists(searchContext, by);
			}
		});
        final WebElement element = findElement(searchContext, by);
        webDriverWait.until(new Function<WebDriver, Object>() {
			@Override
			public Object apply(WebDriver webDriver) {
				return ((JavascriptExecutor) webDriver).executeScript("return !!arguments[0].shadowRoot", element);
			}
		});
    }

    private boolean elementExists(SearchContext searchContext, By by) {
        try {
        	WebElement element = findElement(searchContext, by);
            return element != null;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
