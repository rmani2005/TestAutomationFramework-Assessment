package com.clover.solution.ui.webservices;

import java.net.URL;

public class NavigationService extends WebService {
    // TODO: UrlNotNavigatedEvent

    public void to(String url) {
    	getMasterDriver().navigate().to(url);
    }

    public void toLocalPage(String filePath) {
        URL testAppUrl = Thread.currentThread().getContextClassLoader().getResource(filePath);
        if (testAppUrl != null) {
            to(testAppUrl.toString());
        } else {
            testAppUrl = getClass().getClassLoader().getResource(filePath);
            if (testAppUrl != null) {
                to(testAppUrl.toString());
            }
        }
    }
}