package com.OrangeHRM.Utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass {

public WebDriver driver;
	
public ExtentHtmlReporter htmlReporter;
public ExtentReports extent;
public ExtentTest logger;



@BeforeSuite
public void startReport(){
	
	htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/STMExtentReport.html");
	extent = new ExtentReports();
    extent.attachReporter(htmlReporter);
	   
   extent.setSystemInfo("OS", "Windows ");
   extent.setSystemInfo("Host Name", "Test");
   extent.setSystemInfo("Environment", "QA");
   extent.setSystemInfo("User Name", "Test User");
}


@AfterMethod
public void getResult(ITestResult result){

 if(result.getStatus() == ITestResult.SUCCESS) {

	 	 
  logger.log(Status.PASS, "Test Case Passed " + result.getName());
}

if(result.getStatus() == ITestResult.FAILURE){

   logger.log(Status.FAIL, "Test Case Failed is "+result.getName() + result.getThrowable());

}
else if(result.getStatus() == ITestResult.SKIP){

logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
}
}


@AfterSuite
public void endReport(){

             extent.flush();
             
}

	@BeforeClass
	@Parameters("browser")
	public void setup(String browser)
	{
		if(browser.equalsIgnoreCase("chrome"))
		{		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "//Drivers//chromedriver.exe");
		driver= new ChromeDriver();
		}
		
		else if(browser.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+ "//Drivers//geckodriver.exe");
			driver= new FirefoxDriver();
			
		}
	
	}
	@AfterClass
	public void tearDown(){
		
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException{
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+ "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot Taken");
		
	}
	
}
