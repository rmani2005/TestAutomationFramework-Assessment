package com.clover.solution.ui.objectFindStrategy;

import org.openqa.selenium.By;

public class LinkTextFindStrategy extends FindStrategy {
    public LinkTextFindStrategy(String value) {
        super(value);
    }

    @Override
    public By convert() {
        return By.linkText(getValue());
    }

    @Override
    public String toString() {
        return String.format("link text = %s", getValue());
    }
}
