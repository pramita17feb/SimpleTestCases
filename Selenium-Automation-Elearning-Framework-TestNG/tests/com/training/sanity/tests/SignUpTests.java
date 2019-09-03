package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.SignUpPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class SignUpTests {
	private WebDriver driver;
	private String baseUrl;
	private SignUpPOM signUpPOM;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		signUpPOM = new SignUpPOM(driver); 
		loginPOM=new LoginPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	/*
	 * @AfterMethod public void tearDown() throws Exception { Thread.sleep(1000);
	 * driver.quit(); }
	 */
	
	@Test
	public void SignUpTeacher() throws InterruptedException {
		System.out.println("this is Signup method");
		loginPOM.clicksignUpBtn();
		signUpPOM.enterfirstName("test4");
		signUpPOM.enterlastName("test4");
		signUpPOM.enteremail("test4@gmail.com");
		signUpPOM.enterphone("987654321");
		signUpPOM.enteruserName("test4");
		signUpPOM.enterpassWd("test4");
		signUpPOM.enterconfirmPassWd("test4");
		signUpPOM.clickradioBtnTeacher();
		signUpPOM.selectLang("English");
		Thread.sleep(3000);
		signUpPOM.clickregisterBtn();
		Thread.sleep(3000);
		String signupmsg=signUpPOM.registermssg();
	
		//comparing registration msg 
		boolean flag=signupmsg.contains("Your personal settings have been registered");
		Assert.assertEquals(flag,true);
		flag=signupmsg.contains("An e-mail has been sent to remind you of your login and password.");
		Assert.assertEquals(flag,true);
		Thread.sleep(3000);
		screenShot.captureScreenShot("SignupSuccess");
		
	}
	


}
