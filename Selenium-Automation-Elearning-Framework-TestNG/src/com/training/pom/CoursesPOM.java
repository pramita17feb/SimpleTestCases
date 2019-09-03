package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoursesPOM {
private WebDriver driver; 
	
	public CoursesPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='classic-courses']//a")
	private WebElement course; 
		
	public void clickCourselink() {
		this.course.click(); 
	}

}
