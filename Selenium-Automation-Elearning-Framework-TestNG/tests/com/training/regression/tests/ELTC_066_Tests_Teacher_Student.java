package com.training.regression.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.dataproviders.ELTC066TestsComplexDataProvider;
import com.training.generics.GenericMethods;
import com.training.generics.ScreenShot;
import com.training.pom.CoursePagePOM;
import com.training.pom.CoursesPOM;
import com.training.pom.CreateCoursePOM;
import com.training.pom.ReportingPOM;
import com.training.pom.ResultafterTestPOM;
import com.training.pom.TestsPOM;
import com.training.sanity.tests.LoginStudentTests;
import com.training.sanity.tests.LoginTeacherTests;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class ELTC_066_Tests_Teacher_Student {
	private WebDriver driver;
	private String baseUrl;
	private LoginTeacherTests loginTeacher;
	private LoginStudentTests loginStudent;
	private CoursesPOM coursesPOM;
	private CreateCoursePOM createcoursesPOM;
	private CoursePagePOM coursepagePOM;
	private TestsPOM testsPOM;
	private ResultafterTestPOM resultPOM;
	private ReportingPOM reportingPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private GenericMethods genericMethods; 
	private JavascriptExecutor js;
	private String testNm="online quiz";
	private String courseNm="dummycourse";
	
	
	@BeforeTest
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		createcoursesPOM=new CreateCoursePOM(driver);
		coursesPOM=new CoursesPOM(driver);
		coursepagePOM=new CoursePagePOM(driver);
		loginTeacher=new LoginTeacherTests();
		testsPOM=new TestsPOM(driver);
		resultPOM=new ResultafterTestPOM(driver);
		loginStudent=new LoginStudentTests();
		reportingPOM=new ReportingPOM(driver);		
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		js=(JavascriptExecutor) driver;
		genericMethods = new GenericMethods(driver); 
		// open the browser
		driver.get(baseUrl);
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
		
		coursesPOM.clickCourseLink(courseNm);
		coursepagePOM.clickTests();
		
		//
		testsPOM.clickCreateTest();
		
		//
		
		testsPOM.enterTestNm(testNm);
		testsPOM.clickAdvancedSettgs();
		testsPOM.enterContext("quiz");
		testsPOM.clickFeedbackRadioBtn();
		testsPOM.clickEnableStartTimeChckbox();
		testsPOM.selectStartTimeMonthYear("Sep","2019");
		testsPOM.passpercent("50");
		
		//
		testsPOM.clickproceedtoquestnBtn();
	}
	
	@Test(priority=2, dependsOnMethods="createTestByTeacher", dataProvider = "db-inputs", dataProviderClass = ELTC066TestsComplexDataProvider.class)
	public void addQuestions(String question, String option1, String option2, String option3, String option4, String answerOptn, String answer) throws InterruptedException {
		
		testsPOM.clickmultiplechoiceIcon();
		
		//
		testsPOM.enterQuestion(question);
		testsPOM.enterMCQoption1(option1);
		testsPOM.enterMCQoption2(option2);
		testsPOM.enterMCQoption3(option3);
		testsPOM.enterMCQoption4(option4);
		testsPOM.clickRadioAns(answerOptn);
		String score="50";
		testsPOM.enterScore(answerOptn, score);
		screenShot.captureScreenShot("QuestionsDBdata");
		
		//
		testsPOM.clickaddQuestion();
		
		screenShot.captureScreenShot("QuestionsAddedToTest");
		
	}
	
	@Test(priority=3, dependsOnMethods="addQuestions")
	public void verifyAlertandPreview() {
		String expectedMsg="2 questions, for a total score (all questions) of 100.";
		String actualMsg=testsPOM.verifyMsg();
		
		//
		Assert.assertEquals(actualMsg, expectedMsg);

		//
		testsPOM.clickPreview();
		
		//
		Assert.assertTrue(testsPOM.verifyStartTestBtn());
	}
	
	@Test(priority=4)
	public void startTest() throws InterruptedException {
		//logout and login as student.........
		loginStudent.LogOutTest(driver);
		driver.get("http://elearning.upskills.in/");
		loginStudent.validLoginTest(driver);
		
		//Search Course in course catalog and subscribe...........
		coursesPOM.clickCourseCatalog();
		coursesPOM.enterCourseSearch(courseNm);
		coursesPOM.clickCourseSearch();
		coursesPOM.clickSubscribeCourse(courseNm);
		
		//Go to Tests for the subscribed course........
		coursepagePOM.clickTests();
		
		//click on test name link......
		testsPOM.clickTestLink();
		
		//click on Start test......
		testsPOM.clickStartTestBtn();
		
		//Answer the questions......
		System.out.println("Q1. >> "+testsPOM.getQuestn());
		if(testsPOM.getQuestn().contains("which course your learning")) 
			testsPOM.clickAnsRadioBtn("selenium");	
		else System.out.println("question:: which course your learning--- is not found---");
		Thread.sleep(3000);
		testsPOM.clickNextQtn();//next question button clicked
		
		System.out.println("Q2. >> "+testsPOM.getQuestn());
		if(testsPOM.getQuestn().contains("which language are you using in selenium"))
			testsPOM.clickAnsRadioBtn("java");
		else System.out.println("question:: which language are you using in selenium--- is not found---");
		
		//Click on End Test button.......
		testsPOM.clickEndTestBtn();
		
		//Verify Result screen for Test name and User first name or second name......
		Assert.assertTrue(resultPOM.getResultHeader().contains(testNm));
		Assert.assertTrue(resultPOM.getUserInfo().contains("student"));
		
		screenShot.captureScreenShot("TestResultAfterEndTest ScreenDBTest");
		
	}
	
	@Test(priority=5)
	public void verifyAsAdmin() throws InterruptedException {
		
		//logout and login as admin.........
		loginStudent.LogOutTest(driver);
		driver.get("http://elearning.upskills.in/");
		loginStudent.validLoginTest();
		
		coursesPOM.clickCourseListAdmin();
		coursesPOM.enterCorseSearchAdmin(courseNm);
		coursesPOM.clickCourseSearchBtnAdmin();
		coursesPOM.clickCourseLinkAdmin(courseNm);
		
		coursepagePOM.clickTests();
		testsPOM.clickResultandFeedback();
		testsPOM.clickGradeActivity();
		screenShot.captureScreenShot("GradeActivityDBTest");
		
		Assert.assertTrue(resultPOM.getResultHeader().contains(testNm));
		Assert.assertTrue(resultPOM.getUserInfo().contains("student"));
		
		resultPOM.clickSendEmailCheckbox();           //Correct&Rate icon clicked.........
		resultPOM.verifyEmailMsg(courseNm);      //verify the Test result email msg...........
		
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		
		resultPOM.clickCorrectTestBtn();              //click on correct test button..............
		
		
	}
	
			
	


}
