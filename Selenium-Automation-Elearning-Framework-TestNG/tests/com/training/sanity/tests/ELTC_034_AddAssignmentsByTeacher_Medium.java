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
import com.training.sanity.tests.LoginTeacherTests;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class ELTC_034_AddAssignmentsByTeacher_Medium {
	
	public static WebDriver driver;
	private String baseUrl;
	private LoginTeacherTests loginTeacher;
	private AssignmentPOM assignmentPOM;
	private CoursesPOM coursesPOM;
	private CoursePagePOM coursepagePOM;
	private static Properties properties;
	private ScreenShot screenShot;
	
	
	  @BeforeClass public static void setUpBeforeClass() throws IOException {
	  properties = new Properties(); FileInputStream inStream = new
	  FileInputStream("./resources/others.properties"); properties.load(inStream);
	  }
	 

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		baseUrl = properties.getProperty("baseURL");
		assignmentPOM=new AssignmentPOM(driver);
		loginTeacher=new LoginTeacherTests();
		coursesPOM=new CoursesPOM(driver);
		coursepagePOM=new CoursePagePOM(driver);
		screenShot = new ScreenShot(driver);
		driver.get(baseUrl);
		//login as teacher.........
		loginTeacher.validLoginTest(driver);
		System.out.println("logintest executed");
		
	}
	
	
	
	  @AfterMethod 
	  public void tearDown() throws Exception { 
		  Thread.sleep(1000);
		  driver.quit();
	 }
	 
	 
	 
	  
	@Test
	public void addAssignmentByTeacher() throws InterruptedException {
		//go to the course already created
		coursesPOM.clickCourseLink("testCourse");
		
		//click on Assignment icon
		coursepagePOM.clickAssignments();
		
		//create new assignment
		assignmentPOM.createAssignment();
		
		//enter valid credentials in Assignments name textbox...
		assignmentPOM.enterAssignmtNm("Assignment1");

		//add description...
		assignmentPOM.enterDescription("Description exercise1");
		assignmentPOM.clickAdvancedSettgs();
		
		//add max score
		assignmentPOM.entermaxScore("50");
		System.out.println("Default Document type:"+assignmentPOM.defaultDocType());
		
		//click on validate...
		assignmentPOM.clickSubmit();
		screenShot.captureScreenShot("AddAssignmntTeacher");
	
	}

}
