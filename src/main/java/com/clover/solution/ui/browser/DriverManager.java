package com.clover.solution.ui.browser;

import java.time.Duration;
import java.util.function.Supplier;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.clover.solution.ui.configuration.BrowserConfiguration;
import com.clover.solution.ui.configuration.ConfigProvider;
import com.clover.solution.ui.util.logs.Log;

public class DriverManager 
{
	//Load all browser related details here after loading it from configuration
	
	private static ThreadLocal<WebDriver> MASTER_DRIVER = null;
	private static ThreadLocal<RemoteWebDriver> MASTER_DRIVER_REMOTE = null;
	private static ThreadLocal<BrowserConfiguration> BROWSER_CONFIGURATION;
	private static browserDriverStrategy DRIVER_STRATEGY;
	private static String MainBrowser;
	private static ThreadLocal<Boolean> DISPOSED = ThreadLocal.withInitial(new Supplier<Boolean>() {
		@Override
		public Boolean get() {
			return true;
		}
	});
	private static String className="DriverManager";
	private static boolean  closeBrowser;
	//We can scale this part to other type of drivers like 
	//EdgeDriver
	//SafariDriver
	//IEDriver 
	//AndroidMobile and so on
	
	static {
        BROWSER_CONFIGURATION = new ThreadLocal<BrowserConfiguration>();
        MASTER_DRIVER = new ThreadLocal<>();
        MASTER_DRIVER_REMOTE = new ThreadLocal<>();
        MainBrowser=ConfigProvider.getAsString("webSettings.defaultBrowser").toUpperCase();
        //BROWSER_CONFIGURATION.set(Browser.CHROME);
        initlizeMasterDriver_normal();
        Log.info(className + "-Inilizing the Browser configuration");
        Log.info(className + "-Inilizing the Master Driver thread");
    }


	public static WebDriver getMasterDriver()	{
		return MASTER_DRIVER.get();
	}
	
	private static void setMasterDriver(WebDriver driver)	{
		MASTER_DRIVER.set(driver);
	}
	
	private static void initlizeMasterDriver_normal() {
		WebDriver driver = null;
		
		try {
			switch (MainBrowser) {
			case "CHROME":
				DRIVER_STRATEGY=new chromeDriverStrategy();
				driver=DRIVER_STRATEGY.getDriverInstance();
				break;
			case "CHROMEHEADLESS":
				DRIVER_STRATEGY=new chromeHeadlessDriverStrategy();
				driver=DRIVER_STRATEGY.getDriverInstance();
				break;
			case "CHROMEREMOTE":
				//MASTER_DRIVER_REMOTE=new chromeRemoteDriverStrategy();
				//driver=DRIVER_STRATEGY.getDriverInstance();
				break;
				
			case "FIREFOX":
				DRIVER_STRATEGY=new firefoxDriverStrategy();
				driver=DRIVER_STRATEGY.getDriverInstance();
				break;
				//To be continued
			default:
				DRIVER_STRATEGY=new chromeDriverStrategy();
				driver=DRIVER_STRATEGY.getDriverInstance();
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.error(e.getMessage());
			e.printStackTrace();
		}
		
		Log.info(className + "-Setting up the Timeouts & window Maximize");
		//Setting up the Master Driver to Thread local instances
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigProvider.getAsInt("webSettings.timeoutSettings.elementWaitTimeoutpageLoadTimeout")));
		driver.manage().timeouts().setScriptTimeout(Duration.ofSeconds(ConfigProvider.getAsInt("webSettings.timeoutSettings.elementWaitTimeoutscriptTimeout")));
        driver.manage().window().maximize();
        changeWindowSize(driver);       
		setMasterDriver(driver);
	}
	
	private static void changeWindowSize(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public static void close() {
		/* if(DISPOSED.get()) {
			return;
		} */
		closeBrowser=ConfigProvider.getAsBoolean("webSettings.closeBrowser");
        if(MASTER_DRIVER.get()!= null && closeBrowser) {
            Log.debug(className + "Shuting down the master driver");
           MASTER_DRIVER.get().close();
          MASTER_DRIVER.get().quit();
            Log.debug(className + "Browser is closed, Driver instance is killed");
            
            BROWSER_CONFIGURATION=null;
            MASTER_DRIVER = null;
           
        }

        DISPOSED.set(true);
    }
	private static WebDriver initlizeMasterDriver_grid() {
		return null;
	}
	private static WebDriver initlizeMasterDriver_cloud() {
		return null;
	}
	
	
	
}
	
