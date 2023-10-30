package com.clover.solution.ui.waitstrategies;

import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.clover.solution.ui.configuration.ConfigProvider;

public class ToHaveContentWaitStrategy extends WaitStrategy {
    public ToHaveContentWaitStrategy() {
    	timeoutInterval=ConfigProvider.getAsInt("webSettings.timeoutSettings.elementWaitTimeoutelementToHaveContentTimeout");
    	sleepInterval=ConfigProvider.getAsInt("webSettings.timeoutSettings.elementWaitTimeoutsleepInterval");
}

    public static ToHaveContentWaitStrategy of() {
        return new ToHaveContentWaitStrategy();
    }

    public ToHaveContentWaitStrategy(long timeoutIntervalSeconds, long sleepIntervalSeconds) {
        super(timeoutIntervalSeconds, sleepIntervalSeconds);
    }

    @Override
    public void waitUntil(final SearchContext searchContext, final By by) {
        waitUntil(new Function<SearchContext, Boolean>() {
			@Override
			public Boolean apply(SearchContext x) {
				return elementHasContent(searchContext, by);
			}
		});
    }

    private boolean elementHasContent(SearchContext searchContext, By by) {
        WebElement element = findElement(searchContext, by);
        try {
            return element != null && !element.getText().isEmpty();
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            return false;
        }
    }
}
