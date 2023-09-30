package com.Actitime.GenericLibrary;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.Actitime.pom.LoginPage;

public class BaseClass {

	FileLibrary f = new FileLibrary();
	public WebDriver driver;
	@BeforeSuite
	public void databaseConnection()
	{
		Reporter.log("database connected",true);
	}
	
	@BeforeClass
	public void launchBrowser() throws IOException
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String Link = f.readDataFromPropertyFile("url");
		driver.get(Link);
		Reporter.log("browser launched successfully", true);
	}
	
	@BeforeMethod
	public void loginToActitime() throws IOException
	{
		LoginPage lp =new LoginPage(driver);
		String un = f.readDataFromPropertyFile("username");
		lp.getUntbx().sendKeys(un);
		String pw = f.readDataFromPropertyFile("password");
		lp.getPwtbx().sendKeys(pw);
		lp.getLgbtn().click();
		Reporter.log("logged in successfully", true);
	}
	
	@AfterMethod
	public void logoutFromAcitime()
	{
		driver.findElement(By.id("logoutLink")).click();
		Reporter.log("logged out successfully", true);
	}
	
	@AfterClass
	public void closeBrowser()
	{
		driver.close();
		Reporter.log("browser closed successfully", true);
	}
}
