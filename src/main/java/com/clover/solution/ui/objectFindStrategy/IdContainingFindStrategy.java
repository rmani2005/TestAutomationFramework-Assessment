package com.clover.solution.ui.objectFindStrategy;

import org.openqa.selenium.By;

public class IdContainingFindStrategy extends FindStrategy {
    public IdContainingFindStrategy(String value) {
        super(value);
    }

    @Override
    public By convert() {
        return By.cssSelector(String.format("[id*='%s']", getValue()));
    }

    @Override
    public String toString() {
        return String.format("id containing %s", getValue());
    }
}