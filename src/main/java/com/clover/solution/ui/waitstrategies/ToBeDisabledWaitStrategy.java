package com.clover.solution.ui.waitstrategies;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.clover.solution.ui.configuration.ConfigProvider;

public class ToBeDisabledWaitStrategy extends WaitStrategy {
    public ToBeDisabledWaitStrategy() {
    	timeoutInterval=ConfigProvider.getAsInt("webSettings.timeoutSettings.elementWaitTimeoutelementNotToBeVisibleTimeout");
    	sleepInterval=ConfigProvider.getAsInt("webSettings.timeoutSettings.elementWaitTimeoutsleepInterval");
 
    }

    public static ToBeDisabledWaitStrategy of() {
        return new ToBeDisabledWaitStrategy();
    }

    public ToBeDisabledWaitStrategy(long timeoutIntervalSeconds, long sleepIntervalSeconds) {
        super(timeoutIntervalSeconds, sleepIntervalSeconds);
    }

    @Override
    public void waitUntil(final SearchContext searchContext, final By by) {
        waitUntil(new Function<SearchContext, Boolean>() {
			@Override
			public Boolean apply(SearchContext x) {
				return elementIsDisabled(searchContext, by);
			}
		});
    }

    private boolean elementIsDisabled(SearchContext searchContext, By by) {
    	WebElement element = findElement(searchContext, by);
        try {
            return element != null && !element.isEnabled();
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            return false;
        }
    }
}
