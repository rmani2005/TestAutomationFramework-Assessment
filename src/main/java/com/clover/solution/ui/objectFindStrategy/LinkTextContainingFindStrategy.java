package com.clover.solution.ui.objectFindStrategy;

import org.openqa.selenium.By;

public class LinkTextContainingFindStrategy extends FindStrategy {
    public LinkTextContainingFindStrategy(String value) {
        super(value);
    }

    @Override
    public By convert() {
        return By.xpath(String.format("//a[contains(text(), '%s')]", getValue()));
    }

    @Override
    public String toString() {
        return String.format("link text containing %s", getValue());
    }
}
