package com.training.sanity.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.training.pom.LoginPOM;

public class LoginTeacherTests extends LoginTests {
	//private WebDriver driver;
	//LoginPOM loginPOM = new LoginPOM(driver);
		
	@Test
	public void validLoginTest(WebDriver driver) {
		/*
		 * try { super.setUpBeforeClass(); super.setUp();
		 * 
		 * } catch(Exception e){ System.out.println(e); }
		 */
		System.out.println("this is login for teacher.");
		loginPOM=new LoginPOM(driver);
		loginPOM.sendUserName("pramita");
		loginPOM.sendPassword("pramita");
		loginPOM.clickLoginBtn();
		String actualTitle=loginPOM.pageTitle();
		Assert.assertEquals(actualTitle, "e Learning - My education - My courses");
		//screenShot.captureScreenShot("login1");

	}
}
