package com.clover.solution.ui.webservices;
import org.openqa.selenium.WebDriver;

import com.clover.solution.ui.browser.DriverManager;

public abstract class WebService {
    public WebDriver getMasterDriver() {
        return DriverManager.getMasterDriver();
    }
}
