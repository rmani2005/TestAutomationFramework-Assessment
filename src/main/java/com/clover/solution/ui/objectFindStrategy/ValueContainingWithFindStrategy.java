package com.clover.solution.ui.objectFindStrategy;

import org.openqa.selenium.By;

public class ValueContainingWithFindStrategy extends FindStrategy {
    public ValueContainingWithFindStrategy(String value) {
        super(value);
    }

    @Override
    public By convert() {
        return By.cssSelector(String.format("[value*='%s']", getValue()));
    }

    @Override
    public String toString() {
        return String.format("value containing %s", getValue());
    }
}