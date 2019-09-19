package com.training.regression.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.CoursePagePOM;
import com.training.pom.CoursesPOM;
import com.training.pom.CreateCoursePOM;
import com.training.pom.ReportingPOM;
import com.training.sanity.tests.LoginStudentTests;
import com.training.sanity.tests.LoginTeacherTests;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class ELTC_064_createCouerseComplex {
	public static WebDriver driver;
	private String baseUrl;
	private LoginTeacherTests loginTeacher;
	private LoginStudentTests loginStudent;
	private CoursesPOM coursesPOM;
	private CreateCoursePOM createcoursesPOM;
	private CoursePagePOM coursepagePOM;
	private ReportingPOM reportingPOM;
	private JavascriptExecutor js;
	private static Properties properties;
	private ScreenShot screenShot;
	private String coursenm="seleniumCourse";
	
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
		loginStudent=new LoginStudentTests();
		reportingPOM=new ReportingPOM(driver);
		screenShot = new ScreenShot(driver); 
		js=(JavascriptExecutor) driver;
		// open the browser 
		driver.get(baseUrl);
		//login as teacher.........
		loginTeacher.validLoginTest(driver);
		
	}
	
	@AfterTest
	public void tearDown() throws Exception { 
		  Thread.sleep(1000);
		  driver.quit();
	  }	
	
	@Test(priority=1)
	public void createCourse() throws Exception {
		//click on create a course.....
		coursesPOM.clickCreateCourse();
				
		//enter all fields to create the course.......
		createcoursesPOM.entercourseNm(coursenm);
		createcoursesPOM.clickAdvancedSettgs();
		createcoursesPOM.enterCategory("Projects");
		createcoursesPOM.enterCourseCd("selCrs");
		createcoursesPOM.enterLanguage("English");
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		createcoursesPOM.clickCreatecourse();
		Thread.sleep(5000);
		screenShot.captureScreenShot("createCourse");
		
	}
	
	@Test(priority=2,dependsOnMethods="createCourse")
	public void addDetailsToCourse() throws InterruptedException {
		
		//add course introduction........
		createcoursesPOM.addIntro();
		createcoursesPOM.enterCourseIntro("this is an selenium course");
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		createcoursesPOM.saveIntro();
		
		//add course description
		coursepagePOM.clickcourseDescriptn();
		coursepagePOM.clickDescriptnIcon();
		coursepagePOM.enterTitle("selenium course for beginners");
		coursepagePOM.enterContent("selenium course for beginners");
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		coursepagePOM.clickSave();
		
		//add objectives......
		coursepagePOM.clickObjectives();
		coursepagePOM.enterTitle("selenium course for beginners");
		coursepagePOM.enterContent("selenium course for beginners");
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		coursepagePOM.clickSave();
		
		//add topics.......
		coursepagePOM.clickTopics();
		coursepagePOM.enterTitle("selenium course for beginners");
		coursepagePOM.enterContent("selenium course for beginners");
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		coursepagePOM.clickSave();
		
	}
	
	@Test(priority=3,dependsOnMethods="addDetailsToCourse")
	public void subscribeCourse() throws InterruptedException {
		
		//login as student.........
		loginStudent.LogOutTest(driver);
		driver.get("http://elearning.upskills.in/");
		loginStudent.validLoginTest(driver);
		
		coursesPOM.clickCourseCatalog();
		coursesPOM.enterCourseSearch(coursenm);
		coursesPOM.clickCourseSearch();
		coursesPOM.clickSubscribeCourse(coursenm);
		screenShot.captureScreenShot("courseSubscribed");
		
		//Course Intro should display
		Assert.assertTrue(coursepagePOM.verifyIntroDisplayed());
		
		//click on Course description
		coursepagePOM.clickcourseDescriptn();
		
		//Course description should display
		Assert.assertTrue(coursepagePOM.verifyDescriptionDisplayed());

		//Course objective should display
		Assert.assertTrue(coursepagePOM.verifyObjectiveDisplayed());
		
		//Course Topic should display
		Assert.assertTrue(coursepagePOM.verifyTopicDisplayed());
		
	}
	
	@Test(priority=4, dependsOnMethods="subscribeCourse")
	public void verifyDetailAsAdmin() {
		
		//login as admin.........
		loginStudent.LogOutTest(driver);
		driver.get("http://elearning.upskills.in/");
		loginStudent.validLoginTest();//calling super class admin login
		
		//click on Reporting tab
		reportingPOM.clickReporting();
		
		//click on followed student icon
		reportingPOM.clickFollowedStudnt();
		
		//enter search keyword for student
		reportingPOM.enterKeyword("student");
		reportingPOM.clickSearchStudnt();
		
		//click on student detail with first name and last name
		reportingPOM.clickStudntdtl("student","student");
		//click on course detail
		reportingPOM.clickDetails(coursenm);
		
		//validating overall details
		Assert.assertTrue(reportingPOM.validateOveralldetail(), "overall details of the student in that particular course is displayed");
		
		
	}

}
