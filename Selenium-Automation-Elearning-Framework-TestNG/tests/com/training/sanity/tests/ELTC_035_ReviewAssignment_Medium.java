package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AssignmentPOM;
import com.training.pom.CoursePagePOM;
import com.training.pom.CoursesPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class ELTC_035_ReviewAssignment_Medium {
	public static WebDriver driver;
	private String baseUrl;
	private LoginTeacherTests loginTeacher;
	private AssignmentPOM assignmentPOM;
	private CoursesPOM coursesPOM;
	private CoursePagePOM coursepagePOM;
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
		baseUrl = properties.getProperty("baseURL");
		assignmentPOM=new AssignmentPOM(driver);
		coursesPOM=new CoursesPOM(driver);
		coursepagePOM=new CoursePagePOM(driver);
		loginTeacher=new LoginTeacherTests();
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		//login as teacher.........
		loginTeacher.validLoginTest(driver);
		System.out.println("logintest executed");
	}
	
	
	  @AfterMethod public void tearDown() throws Exception { 
		  Thread.sleep(1000);
		  driver.quit();
	  }
	 
	  
	@Test
	public void reviewAssignmtByTeacher() throws InterruptedException {
		//click on course link
		coursesPOM.clickCourseLink("testCourse");
		
		//click assignment icon
		coursepagePOM.clickAssignments();
		//click on added assignment link...
		assignmentPOM.clickAnAssignmnt("TestAssignmntTeacher");
		
		//pass the title of student's assignment to be reviewed
		assignmentPOM.clickReviewStudentAssignmnt("teststudentAssignment");
		
		//description for review
		assignmentPOM.enterDescription("Reviewed");
		//click on send message....
		assignmentPOM.clickSendmsgBtn();
		
		
	}

}
