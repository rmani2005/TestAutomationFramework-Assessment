package com.clover.solution.ui.util.logs;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
public class Log 
{
    //Initialize Log4j instance
    private static final Logger Log =  LogManager.getLogger(Log.class);
    //BasicConfigurator.configure();
    //Info Level Logs
    public static void info (String message) {
        Log.info(message);
    }
    
    //Warn Level Logs
    public static void warn (String message) {
        Log.warn(message);
    }
    
    //Error Level Logs
    public static void error (String message) {
        Log.error(message);
    }
    
    //Fatal Level Logs
    public static void fatal (String message) {
        Log.fatal(message);
    }
    
    //Debug Level Logs
    public static void debug (String message) {
        Log.debug(message);
    }

	public static void printStackTrace(IOException ioException) {
		// TODO Auto-generated method stub
		Log.error(ioException.toString());
	}
}