package com.clover.solution.ui.waitstrategies;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.clover.solution.ui.configuration.ConfigProvider;

public class ToBeVisibleWaitStrategy extends WaitStrategy {
    public ToBeVisibleWaitStrategy() {
    	timeoutInterval=ConfigProvider.getAsInt("webSettings.timeoutSettings.elementWaitTimeoutelementToBeVisibleTimeout");
    	sleepInterval=ConfigProvider.getAsInt("webSettings.timeoutSettings.elementWaitTimeoutsleepInterval");
    }

    public ToBeVisibleWaitStrategy(long timeoutIntervalSeconds, long sleepIntervalSeconds) {
        super(timeoutIntervalSeconds, sleepIntervalSeconds);
    }

    public static ToBeVisibleWaitStrategy of() {
        return new ToBeVisibleWaitStrategy();
    }

    @Override
    public void waitUntil(final SearchContext searchContext, final By by) {
        waitUntil(new Function<SearchContext, Boolean>() {
			@Override
			public Boolean apply(SearchContext x) {
				return elementIsVisible(searchContext, by);
			}
		});
    }

    private boolean elementIsVisible(SearchContext searchContext, By by) {
        WebElement element = findElement(searchContext, by);
        try {
            return element != null && element.isDisplayed();
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            return false;
        }
    }
}
