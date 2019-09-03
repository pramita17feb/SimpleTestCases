package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoursePagePOM {
private WebDriver driver; 
	
	public CoursePagePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@title='Assignments']")
	private WebElement assignment; 
		
	public void clickAssignment() {
		this.assignment.click(); 
	}

}
