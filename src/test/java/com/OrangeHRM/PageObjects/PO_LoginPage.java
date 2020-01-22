package com.OrangeHRM.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PO_LoginPage {

	WebDriver ldriver;
	
	public PO_LoginPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		
		PageFactory.initElements(rdriver,this);
		
	}
	
	// Captured all WebElements by @FindBy annotation
	
	@FindBy(name="txtUsername")
	@CacheLookup
	WebElement UserName;
	
	@FindBy(name="txtPassword")
	@CacheLookup
	WebElement Password;
	
	@FindBy(name="Submit")
	@CacheLookup
	WebElement submit;
	
	@FindBy(xpath="//*[@id=\"content\"]/div/div[1]/h1")
	@CacheLookup
	WebElement dashBoardtxt;
	
	public void Login(String uname,String pwd) {
		
		UserName.sendKeys(uname);
		Password.sendKeys(pwd);
		submit.click();
				
	}
	
public Boolean IsSuccesfullLogin() 
	{
		
		if(dashBoardtxt.getText().equalsIgnoreCase("dashboard")) 
		{
		return true;
		}
	else
		return false;
	}
}
