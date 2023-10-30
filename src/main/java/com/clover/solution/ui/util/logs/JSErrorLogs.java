package com.clover.solution.ui.util.logs;

import java.util.function.Predicate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class JSErrorLogs {
    public static LogEntries getLogs(WebDriver driver) 
    {
        return driver.manage().logs().get(LogType.BROWSER);
    }
    public static Boolean isLoginErrorLog(WebDriver driver) {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        return logEntries.getAll().stream()
            .anyMatch(new Predicate<LogEntry>() {
				@Override
				public boolean test(LogEntry logEntry) {
					return true;
				}
			});
    }
}