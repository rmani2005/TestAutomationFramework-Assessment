package com.clover.solution.ui.objectFindStrategy;

import org.openqa.selenium.By;

public class ClassContainingFindStrategy extends FindStrategy {
    public ClassContainingFindStrategy(String value) {
        super(value);
    }

    @Override
    public By convert() {
        return By.cssSelector(String.format("[class*='%s']", getValue()));
    }

    @Override
    public String toString() {
        return String.format("class containing %s", getValue());
    }
}
