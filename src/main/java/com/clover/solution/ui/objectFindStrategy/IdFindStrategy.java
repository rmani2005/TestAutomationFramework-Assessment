package com.clover.solution.ui.objectFindStrategy;

import org.openqa.selenium.By;

public class IdFindStrategy extends FindStrategy {
    public IdFindStrategy(String value) {
        super(value);
    }

    @Override
    public By convert() {
        return By.id(getValue());
    }

    @Override
    public String toString() {
        return String.format("id = %s", getValue());
    }
}
