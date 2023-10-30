package com.clover.solution.ui.waitstrategies;

public class WaitStrategyPool {
   // private final TimeoutSettings timeoutSettings;
    //All timeoutsettings should be loaded here

    public WaitStrategyPool() {
        //timeoutSettings = ConfigurationService.get(WebSettings.class).getTimeoutSettings();
    	//load all property values here
    }

    public static ToBeClickableWaitStrategy beClickable(long timeoutInterval, long sleepInterval) {
        return new ToBeClickableWaitStrategy(timeoutInterval, sleepInterval);
    }

    public static ToBeClickableWaitStrategy beClickable() {
        //return new ToBeClickableWaitStrategy(timeoutSettings.getElementToBeClickableTimeout(), timeoutSettings.getSleepInterval());
    	return new ToBeClickableWaitStrategy(30,30);
    }

}
