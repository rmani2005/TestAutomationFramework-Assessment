package com.clover.solution.ui.browser;

import org.openqa.selenium.WebDriver;

import lombok.Getter;
import lombok.Setter;

public abstract class browserDriverStrategy {

	@Setter @Getter private String browser;
    public abstract WebDriver getDriverInstance();
	
}
