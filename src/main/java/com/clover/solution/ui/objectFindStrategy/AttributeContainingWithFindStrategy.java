package com.clover.solution.ui.objectFindStrategy;

import org.openqa.selenium.By;

public class AttributeContainingWithFindStrategy extends FindStrategy {
    String attributeName;

    public AttributeContainingWithFindStrategy(String attributeName, String value) {
        super(value);
        this.attributeName = attributeName;
    }

    @Override
    public By convert() {
        return By.cssSelector(String.format("[%s*='%s']", attributeName, getValue()));
    }

    @Override
    public String toString() {
        return String.format("%s containing %s", attributeName, getValue());
    }
}