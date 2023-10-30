package com.clover.solution.ui.waitstrategies;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import com.clover.solution.ui.configuration.ConfigProvider;

public class ToExistWaitStrategy extends WaitStrategy {
    public ToExistWaitStrategy() {
        timeoutInterval=ConfigProvider.getAsInt("webSettings.timeoutSettings.elementWaitTimeoutelementToExistTimeout");
    	sleepInterval=ConfigProvider.getAsInt("webSettings.timeoutSettings.elementWaitTimeoutsleepInterval");
    
    }

    public ToExistWaitStrategy(long timeoutIntervalSeconds, long sleepIntervalSeconds) {
        super(timeoutIntervalSeconds, sleepIntervalSeconds);
    }

    public static ToExistWaitStrategy of() {
        return new ToExistWaitStrategy();
    }

    @Override
    public void waitUntil(final SearchContext searchContext, final By by) {
        waitUntil(new Function<SearchContext, Boolean>() {
			@Override
			public Boolean apply(SearchContext x) {
				return elementExists(searchContext, by);
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
