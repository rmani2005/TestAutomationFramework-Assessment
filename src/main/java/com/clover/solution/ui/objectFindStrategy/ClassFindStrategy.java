package com.clover.solution.ui.objectFindStrategy;

import org.openqa.selenium.By;

public class ClassFindStrategy extends FindStrategy {
    public ClassFindStrategy(String value) {
        super(value);
    }

    @Override
    public By convert() {
        return By.className(getValue());
    }

    @Override
    public String toString() {
        return String.format("class = %s", getValue());
    }
}
