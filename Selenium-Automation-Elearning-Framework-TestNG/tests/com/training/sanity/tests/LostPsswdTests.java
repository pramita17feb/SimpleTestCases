package com.training.sanity.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.GmailPOM;
import com.training.pom.LoginPOM;
import com.training.pom.SignUpPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class LostPsswdTests {
	
	private WebDriver driver;
	private String baseUrl;
	private String dependentURL;
	private SignUpPOM signUpPOM;
	private LoginPOM loginPOM;
	private GmailPOM gmailPOM;
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
		gmailPOM=new GmailPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		dependentURL=properties.getProperty("dependentURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@Test
	public void LostPsswd() throws InterruptedException, AWTException {
		System.out.println("this is lostpassword method");
		loginPOM.clickLostpasswd();
		loginPOM.enterLostPsswdUser("pramita17feb@gmail.com");
		loginPOM.clickSendMsgBtn();
		Thread.sleep(3000);
		System.out.println("Clicked on send Mail button");
		
		//Navigate to new tab....
		
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_T);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(3000);
		//ArrayList<String> s1=new ArrayList<String>( driver.getWindowHandles());
		Set<String> s1=driver.getWindowHandles();
		Iterator<String> i1=s1.iterator();
        String parentWindow=i1.next();
        System.out.println("This parent: "+parentWindow);
        String childWindow=i1.next();
        System.out.println("Thisi child: "+childWindow);
        driver.switchTo().window(childWindow);
        
        //Navigate to gmail.....
        
		driver.navigate().to(dependentURL);
		Thread.sleep(3000);
		gmailPOM.clicksigninBtn();		
		s1=driver.getWindowHandles();
		i1=s1.iterator();
		parentWindow=i1.next();
		System.out.println("new parent1: "+parentWindow);
		childWindow=i1.next();
		System.out.println("Thisi child: "+childWindow);
		String childWindow1=i1.next();
		System.out.println("Thisi child1: "+childWindow1);
		driver.switchTo().window(childWindow1);
		System.out.println("current window: "+driver.getWindowHandle());
		gmailPOM.entergemail("pramita17feb");
		gmailPOM.clicknextBtn();
		System.out.println("current window: "+driver.getWindowHandle());
		Thread.sleep(3000);
		gmailPOM.entergpasswd("abc");
		gmailPOM.clicknextBtn();
		Thread.sleep(3000);
		screenShot.captureScreenShot("gmaillogin");

		
	}

}
