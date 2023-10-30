package com.clover.solution.ui.waitstrategies;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.clover.solution.ui.configuration.ConfigProvider;

public class ToNotBeVisibleWaitStrategy extends WaitStrategy {
    public ToNotBeVisibleWaitStrategy() {
    	timeoutInterval=ConfigProvider.getAsInt("webSettings.timeoutSettings.elementWaitTimeoutelementNotToBeVisibleTimeout");
    	sleepInterval=ConfigProvider.getAsInt("webSettings.timeoutSettings.elementWaitTimeoutsleepInterval");
    }
    public ToNotBeVisibleWaitStrategy(long timeoutIntervalSeconds, long sleepIntervalSeconds) {
        super(timeoutIntervalSeconds, sleepIntervalSeconds);
    }

    public static ToNotBeVisibleWaitStrategy of() {
        return new ToNotBeVisibleWaitStrategy();
    }

    @Override
    public void waitUntil(final SearchContext searchContext, final By by) {
        waitUntil(new Function<SearchContext, Boolean>() {
			@Override
			public Boolean apply(SearchContext x) {
				return elementIsInvisible(searchContext, by);
			}
		});
    }

    private boolean elementIsInvisible(SearchContext searchContext, By by) {
        try {
            WebElement element = findElement(searchContext, by);
            return element != null && !element.isDisplayed();
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            return true;
        }
    }
}
