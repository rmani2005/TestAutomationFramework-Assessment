package com.clover.solution.ui.objectFindStrategy;

import org.openqa.selenium.By;

public class TagFindStrategy extends FindStrategy {
    public TagFindStrategy(String value) {
        super(value);
    }

    @Override
    public By convert() {
        return By.tagName(getValue());
    }

    @Override
    public String toString() {
        return String.format("tag = %s", getValue());
    }
}
