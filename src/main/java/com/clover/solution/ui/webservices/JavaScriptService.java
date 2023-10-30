
package com.clover.solution.ui.webservices;

import org.openqa.selenium.*;

import jdk.internal.org.jline.utils.Log;

public class JavaScriptService extends WebService {
    private final JavascriptExecutor javascriptExecutor;

    public JavaScriptService() {
        super();
        javascriptExecutor = (JavascriptExecutor)getMasterDriver();
    }

    public Object execute(String script) {
        try {
            Object result = javascriptExecutor.executeScript(script);
            return result;
        } catch (Exception ex) {
            Log.error(ex.toString());
            return "";
        }
    }

    public String execute(String frameName, String script) {
        getMasterDriver().switchTo().frame(frameName);
        String result = (String)execute(script);
        getMasterDriver().switchTo().defaultContent();
        return result;
    }

    public String execute(String script, Object... args) {
        try {
        	String result = (String)javascriptExecutor.executeScript(script, args);
            return result;
        } catch (Exception ex) {
        	 Log.error(ex.toString());
            return "";
        }
    }

  
    public String execute(String script, WebElement nativeElement) {
        try {
        	String result = (String)javascriptExecutor.executeScript(script, nativeElement);
            return result;
        } catch (NoSuchSessionException | NoSuchWindowException ex) {
            throw ex;
        } catch (StaleElementReferenceException | NoSuchElementException ex) {
            return "";
        } catch (Exception ex) {
        	 Log.error(ex.toString());
            return "";
        }
    }
}
