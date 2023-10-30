package com.clover.solution.ui.objectFindStrategy;

import org.openqa.selenium.By;

public class IdEndingWithFindStrategy extends FindStrategy {
    public IdEndingWithFindStrategy(String value) {
        super(value);
    }

    @Override
    public By convert() {
        return By.cssSelector(String.format("[id$='%s']", getValue()));
    }

    @Override
    public String toString() {
        return String.format("id ending with %s", getValue());
    }
}