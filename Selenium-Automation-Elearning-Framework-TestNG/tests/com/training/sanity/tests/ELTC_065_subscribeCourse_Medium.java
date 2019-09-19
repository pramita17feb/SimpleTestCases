package com.training.sanity.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AssignmentPOM;
import com.training.pom.CoursePagePOM;
import com.training.pom.CoursesPOM;
import com.training.pom.ResultafterTestPOM;
import com.training.pom.TestsPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class ELTC_065_subscribeCourse_Medium {
	
	public static WebDriver driver;
	private String baseUrl;
	private LoginStudentTests loginStudent;
	private CoursesPOM coursesPOM;
	private CoursePagePOM coursepagePOM;
	private TestsPOM testsPOM;
	private ResultafterTestPOM resultPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	
	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		baseUrl = properties.getProperty("baseURL");
		coursesPOM=new CoursesPOM(driver);
		coursepagePOM=new CoursePagePOM(driver);
		loginStudent=new LoginStudentTests();
		testsPOM=new TestsPOM(driver);
		resultPOM=new ResultafterTestPOM(driver);
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		//login as student.........
		loginStudent.validLoginTest(driver);
		System.out.println("logintest executed");
	}
	
	@AfterMethod 
	public void tearDown() throws Exception { 
		  Thread.sleep(1000);
		  driver.quit();
	  }
	
	@Test
	public void subscribeCourse() throws InterruptedException {
		coursesPOM.clickCourseCatalog();
		coursesPOM.enterCourseSearch("testCourse");
		coursesPOM.clickCourseSearch();
		coursesPOM.clickSubscribeCourse("testCourse");
		
		//coursepagePOM.clickAssessments();
		coursepagePOM.clickTests();
		
		//click on test name link......
				testsPOM.clickTestLink();
				
				//click on Start test......
				testsPOM.clickStartTestBtn();
				
				//Answer the questions......
				System.out.println("Q1. >> "+testsPOM.getQuestn());
				String actualQ=testsPOM.getQuestn();
				String expectedQ="which course your learning";
				
				Assert.assertEquals(actualQ, expectedQ);
				
				if(actualQ.contains(expectedQ)) 
					testsPOM.clickAnsRadioBtn("selenium");	
				else System.out.println("question:: "+actualQ+"--- is not found---");
				
				Thread.sleep(3000);
				//next question button clicked
				testsPOM.clickNextQtn();
				
				System.out.println("Q2. >> "+testsPOM.getQuestn());
				expectedQ="which language are you using in selenium";
				actualQ=testsPOM.getQuestn();
				
				Assert.assertEquals(actualQ, expectedQ);
				if(testsPOM.getQuestn().contains(expectedQ))
					testsPOM.clickAnsRadioBtn("java");
				else System.out.println("question::"+actualQ+"--- is not found---");
				
				//Click on End Test button.......
				testsPOM.clickEndTestBtn();
				
				//Verify Result screen, results should get displayed based on the authoring & answer......
				Assert.assertTrue(resultPOM.getResultHeader().contains("onlineTest"));
				Assert.assertTrue(resultPOM.getUserInfo().contains("student"));
		
		
	}
		

}
