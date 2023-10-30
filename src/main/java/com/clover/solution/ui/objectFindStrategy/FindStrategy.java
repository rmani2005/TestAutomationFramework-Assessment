
package com.clover.solution.ui.objectFindStrategy;

import org.openqa.selenium.By;

public abstract class FindStrategy {
    private final String value;

    protected FindStrategy(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public abstract By convert();
}
