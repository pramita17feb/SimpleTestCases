package com.training.regression.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.CoursePagePOM;
import com.training.pom.CoursesPOM;
import com.training.pom.CreateCoursePOM;
import com.training.pom.ReportingPOM;
import com.training.pom.TestsPOM;
import com.training.sanity.tests.LoginStudentTests;
import com.training.sanity.tests.LoginTeacherTests;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class ELTC_065_TestsByTeacherComplex {
	public static WebDriver driver;
	private String baseUrl;
	private LoginTeacherTests loginTeacher;
	private LoginStudentTests loginStudent;
	private CoursesPOM coursesPOM;
	private CreateCoursePOM createcoursesPOM;
	private CoursePagePOM coursepagePOM;
	private TestsPOM testsPOM;
	private ReportingPOM reportingPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	
	@BeforeTest 
	public static void setUpBeforeClass() throws IOException {
		  properties = new Properties(); FileInputStream inStream = new
		  FileInputStream("./resources/others.properties"); properties.load(inStream);
		  }
	
	@BeforeClass
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		baseUrl = properties.getProperty("baseURL");
		createcoursesPOM=new CreateCoursePOM(driver);
		coursesPOM=new CoursesPOM(driver);
		coursepagePOM=new CoursePagePOM(driver);
		loginTeacher=new LoginTeacherTests();
		testsPOM=new TestsPOM(driver);
		loginStudent=new LoginStudentTests();
		reportingPOM=new ReportingPOM(driver);
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		//login as teacher.........
		loginTeacher.validLoginTest(driver);
		
	}
	
	
	@AfterClass 
	public void tearDown() throws Exception { 
		  Thread.sleep(1000);
		  driver.quit();
	  }
	
	@Test
	public void createTestByTeacher() throws InterruptedException {

		//
		coursesPOM.clickCourseLink("testCourse");
		Thread.sleep(3000);
		coursepagePOM.clickTests();
		
		//
		testsPOM.clickCreateTest();
		
		//
		testsPOM.enterTestNm("online quiz");
		testsPOM.clickAdvancedSettgs();
		testsPOM.enterContext("quiz");
		testsPOM.clickFeedbackRadioBtn();
		testsPOM.clickEnableStartTimeChckbox();
		testsPOM.selectStartTimeMonthYear("Oct","2019");
		testsPOM.passpercent("50");
		
		//
		testsPOM.clickproceedtoquestnBtn();
	}
	
	@Test(priority=2, dependsOnMethods="createTestByTeacher", dataProvider = "excel-inputs1", dataProviderClass = LoginDataProviders.class)
	public void addQuestions(String question, String option1, String option2, String option3, String option4) throws InterruptedException {
		
		testsPOM.clickmultiplechoiceIcon();
		
		//
		testsPOM.enterQuestion(question);
		testsPOM.enterMCQoption1(option1);
		testsPOM.enterMCQcomment1("Right Answer");
		testsPOM.enterMCQoption2(option2);
		testsPOM.enterMCQcomment2("Wrong Answer");
		testsPOM.enterMCQoption3(option3);
		testsPOM.enterMCQcomment3("Wrong Answer");
		testsPOM.enterMCQoption4(option4);
		testsPOM.enterMCQcomment4("Wrong Answer");
		screenShot.captureScreenShot("Questions");
		
		//
		testsPOM.clickaddQuestion();
		
		screenShot.captureScreenShot("QuestionsAddedToTest");
		
	}
	
	@Test(priority=3, dependsOnMethods="addQuestions")
	public void verifyAlertandPreview() {
		String expectedMsg="4 questions, for a total score (all questions) of 0.";
		String actualMsg=testsPOM.verifyMsg();
		
		//
		Assert.assertEquals(actualMsg, expectedMsg);

		//
		testsPOM.clickPreview();
		
		//
		Assert.assertTrue(testsPOM.verifyStartTestBtn());
	}

}
