package com.clover.solution.ui.webservices;

import org.openqa.selenium.Cookie;

import java.util.Set;

public class CookiesService extends WebService {
    public void addCookie(String cookieName, String cookieValue, String path) {
        getMasterDriver().manage().addCookie(new Cookie(cookieName, cookieValue, path));
    }

    public void addCookie(String cookieName, String cookieValue) {
        addCookie(cookieName, cookieValue, "/");
    }

    public void addCookie(Cookie cookieToAdd) {
        getMasterDriver().manage().addCookie(cookieToAdd);
    }

    public void deleteAllCookies() {
        getMasterDriver().manage().deleteAllCookies();
    }

    public void deleteCookie(String cookieName) {
        getMasterDriver().manage().deleteCookieNamed(cookieName);
    }

    public Set<Cookie> getAllCookies() {
        return getMasterDriver().manage().getCookies();
    }

    public Cookie getCookie(String cookieName) {
        return getMasterDriver().manage().getCookieNamed(cookieName);
    }
}
