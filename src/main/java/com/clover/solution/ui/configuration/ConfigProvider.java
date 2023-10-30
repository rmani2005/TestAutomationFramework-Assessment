package com.clover.solution.ui.configuration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.clover.solution.ui.util.logs.Log;
import com.google.gson.JsonParser;

public class ConfigProvider {
	
	private static String environment; //dev or test
	private static String project; //dev or test
	private static Properties properties;
	private static final String propertyFilePath= "/environment.properties";
	private static boolean PropertyFileloaded;
	
	public static String getEnvironment(){
		return environment;
	}
	
	public static String getProject() {
		return project;
	}
	
	public ConfigProvider(){
		String Current_Directory=System.getProperty("user.dir");
		String MASTER_PROPERTY_FILE_PATH= Current_Directory + "//src//main//java//com//clover//solution//ui//configuration//" ;
	
		if (environment == null) {
            String environmentOverride = System.getProperty("environment");
            if (environmentOverride == null) {
                try {
                	BufferedReader input = new BufferedReader(new FileReader(MASTER_PROPERTY_FILE_PATH + propertyFilePath));
                    Properties p = new Properties();
                    p.load(input);
                    environment = p.getProperty("environment");
                    project = p.getProperty("project");
                    environmentOverride=environment;
                } catch (IOException e) {
                    Log.error("IOException in Property reading");
                }              
            } else {
                environment = environmentOverride;
            }
        }

        String fileName = MASTER_PROPERTY_FILE_PATH + String.format("%s.Settings.%s.properties",project, environment); //dev or test
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
				setPropertyFileloaded(true);
			} catch (IOException e) {
				Log.error("IOException in Property reading");
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			Log.error("IOException in Property reading");
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}
	
	public static String getAsString(String parameter){
		String valueToReturn = getPropertyInstance().getProperty(parameter);
		if(valueToReturn!= null) {
			Log.info("ConfigProvider - getAsString: parameter = "+ valueToReturn);
			return String.valueOf(valueToReturn);
		}
		else {
			Log.error(parameter + " not specified in the Configuration.properties file.");
			throw new RuntimeException(parameter + " not specified in the Configuration.properties file.");		
			}
	}		
	
	public static int getAsInt(String parameter){
		String valueToReturn = getPropertyInstance().getProperty(parameter);
		if(valueToReturn!= null) {
			Log.info("ConfigProvider - getAsString: parameter = "+ valueToReturn);
			return Integer.parseInt(valueToReturn);
		}
		else {
			Log.error(parameter + " not specified in the Configuration.properties file.");
			throw new RuntimeException(parameter + " not specified in the Configuration.properties file.");
		}
	}
	
	
	public static Boolean getAsBoolean(String parameter){
		String valueToReturn = getPropertyInstance().getProperty(parameter);
		if(valueToReturn!= null) {
			Log.info("ConfigProvider - getAsString: parameter = "+ valueToReturn);
			return Boolean.valueOf(valueToReturn);
		}
		else {
			Log.error(parameter + " not specified in the Configuration.properties file.");
			throw new RuntimeException(parameter + " not specified in the Configuration.properties file.");		
			}
	}	

	public boolean isPropertyFileloaded() {
		return PropertyFileloaded;
	}

	public void setPropertyFileloaded(boolean propertyFileloaded) {
		PropertyFileloaded = propertyFileloaded;
	}
	
	public static Properties getPropertyInstance() {
		try {
			if(PropertyFileloaded==true)
				return properties;
			else
				new ConfigProvider();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return properties;
	}
	

}