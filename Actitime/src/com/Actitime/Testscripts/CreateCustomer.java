package com.Actitime.Testscripts;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Actitime.GenericLibrary.BaseClass;
import com.Actitime.GenericLibrary.FileLibrary;
import com.Actitime.pom.TaskPage;

public class CreateCustomer extends BaseClass {
	
	@Test
	public void createCustomer()
	{
	
		HomePage hp = new HomePage(driver);
		hp.getTasktab().click();
		
		TaskPage tp = new TaskPage(driver);
		tp.getAddnewcust().click();
		tp.getNewcust().click();
		FileLibrary f = new FileLibrary();
		String name = f.readDataFromExcel("Sheet1", 1, 1);
		tp.getCustname().sendKeys(name);
		String desp = f.readDataFromExcel("Sheet1", 1, 2);
		tp.getCustdesp().sendKeys(desp);
		tp.getCreatecust().click();
		
		String expectedresult = name;
		String actualresult = driver.findElement(By.xpath("(//div[.='"+name+"'])[]")).getText()   ;
		SoftAssert s = new SoftAssert();
		s.assertEquals(expectedresult, actualresult);
		s.assertAll();
		
	}

}
     