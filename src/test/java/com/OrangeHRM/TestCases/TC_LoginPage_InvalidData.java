package com.OrangeHRM.TestCases;

import java.io.IOException;
import java.util.Properties;

import javax.naming.ldap.ExtendedRequest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;

import com.OrangeHRM.PageObjects.PO_LoginPage;
import com.OrangeHRM.Utilities.BaseClass;
import com.OrangeHRM.Utilities.CommonAction;
import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.Status;

public class TC_LoginPage_InvalidData extends BaseClass{
	
	@Test
	
	public void LoginPage_InvalidData() throws IOException, InterruptedException {
	
		logger = extent.createTest("LoginPage");
		
				
		CommonAction obj= new CommonAction();
		Properties pro= obj.readProperty();
		
		logger.log(Status.INFO, "Entering BaseURL....");
		driver.get(pro.getProperty("baseURL"));
					
		PO_LoginPage lp = new PO_LoginPage(driver);
		logger.log(Status.INFO, "Entering UserID and Password....");
		lp.Login(pro.getProperty("userid"),pro.getProperty("pwd"));
		
		Assert.assertTrue(lp.IsSuccesfullLogin());
		
					
	}

}
