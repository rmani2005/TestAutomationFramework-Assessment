
package com.clover.solution.ui.waitstrategies;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.clover.solution.ui.browser.DriverManager;

import java.time.Duration;
import java.util.function.Function;

public abstract class WaitStrategy {
    @Getter protected long timeoutInterval;
    @Getter protected long sleepInterval;
    @Getter protected WebDriverWait webDriverWait;

    public WaitStrategy() {
    }

    public WaitStrategy(long timeoutInterval, long sleepInterval) {
        this.timeoutInterval = timeoutInterval;
        this.sleepInterval = sleepInterval;
    }

    public abstract void waitUntil(SearchContext searchContext, By by);

    protected boolean waitUntil(Function<SearchContext, Boolean> waitCondition) {
        webDriverWait = new WebDriverWait(DriverManager.getMasterDriver(), Duration.ofSeconds(timeoutInterval), Duration.ofSeconds(sleepInterval));
        return webDriverWait.until(waitCondition);
    }

    protected WebElement findElement(SearchContext searchContext, By by) {
        WebElement element = searchContext.findElement(by);
        return element;
    }
}
