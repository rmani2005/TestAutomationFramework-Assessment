/*
 * Copyright 2022 Automate The Planet Ltd.
 * Author: Anton Angelov
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.clover.solution.ui.waitstrategies;

import java.util.function.Function;
import org.openqa.selenium.*;
import com.clover.solution.ui.configuration.ConfigProvider;

public class ToNotExistWaitStrategy extends WaitStrategy {
    public ToNotExistWaitStrategy() {
    	timeoutInterval=ConfigProvider.getAsInt("webSettings.timeoutSettings.elementWaitTimeoutElementToNotExistTimeout");
    	sleepInterval=ConfigProvider.getAsInt("webSettings.timeoutSettings.elementWaitTimeoutsleepInterval");
    }

    public ToNotExistWaitStrategy(long timeoutIntervalSeconds, long sleepIntervalSeconds) {
        super(timeoutIntervalSeconds, sleepIntervalSeconds);
    }

    public static ToNotExistWaitStrategy of() {
        return new ToNotExistWaitStrategy();
    }

    @Override
    public void waitUntil(final SearchContext searchContext, final By by) {
        waitUntil(new Function<SearchContext, Boolean>() {
			@Override
			public Boolean apply(SearchContext x) {
				return elementNotExists(searchContext, by);
			}
		});
    }

    private boolean elementNotExists(SearchContext searchContext, By by) {
        try {
            WebElement element = findElement(searchContext, by);
            return element == null;
        } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
            return true;
        }
    }
}
