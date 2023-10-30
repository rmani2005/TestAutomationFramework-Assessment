package com.clover.solution.ui.util.extentreports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.clover.solution.ui.configuration.ConfigProvider;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports createExtentReports() 
    {
    	String userDir=System.getProperty("user.dir");
    	String project=ConfigProvider.getProject().toLowerCase();
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date myDate = new Date();
        String parsedDate = formatter.format(myDate);
        
        String path= userDir + "//Report//" + project+ "//" + parsedDate;
        File theDir = new File(path);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        
        formatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
        myDate = new Date();
        parsedDate = formatter.format(myDate);
    	
        String finalPathandfile=path + "//" + project + "-TestReport-"+parsedDate +".html";
        
        ExtentSparkReporter reporter = new ExtentSparkReporter(finalPathandfile);
        reporter.config().setReportName("Clover Automation Team");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Team", "Clover Automation");
        extentReports.setSystemInfo("Author", "Manikandan Ravi");
        return extentReports;
    }
}