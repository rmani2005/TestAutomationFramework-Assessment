package com.clover.solution.ui.objectFindStrategy;

import org.openqa.selenium.By;

public class NameFindStrategy extends FindStrategy {
    public NameFindStrategy(String value) {
        super(value);
    }

    @Override
    public By convert() {
        return By.name(getValue());
    }

    @Override
    public String toString() {
        return String.format("name = %s", getValue());
    }
}
