
package com.clover.solution.ui.objectFindStrategy;

import org.openqa.selenium.By;

public class XPathFindStrategy extends FindStrategy {
    public XPathFindStrategy(String value) {
        super(value);
    }

    @Override
    public By convert() {
        return By.xpath(getValue());
    }

    @Override
    public String toString() {
        return String.format("xpath = %s", getValue());
    }
}
