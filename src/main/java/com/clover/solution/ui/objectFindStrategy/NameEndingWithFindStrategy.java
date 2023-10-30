package com.clover.solution.ui.objectFindStrategy;

import org.openqa.selenium.By;

public class NameEndingWithFindStrategy extends FindStrategy {
    public NameEndingWithFindStrategy(String value) {
        super(value);
    }

    @Override
    public By convert() {
        return By.cssSelector(String.format("[name$='%s']", getValue()));
    }

    @Override
    public String toString() {
        return String.format("name ending with %s", getValue());
    }
}