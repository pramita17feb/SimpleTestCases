package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AssignmentPOM {
private WebDriver driver; 
	
	public AssignmentPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@title='Create assignment']']")
	private WebElement createAssignment; 
			
	@FindBy(xpath="//label[contains(.,'Assignment name')]//following::input")
	private WebElement assignmentNm; 
	
	@FindBy(xpath="//label[contains(.,'Description')]//parent::div//descendant::div[4]")
	private WebElement e;
	private WebElement description=(WebElement) driver.switchTo().frame(1); 
	
	@FindBy(id="advanced_params")
	private WebElement advancedsettgsBtn; 
	
	@FindBy(xpath="//label[contains(.,'Maximum score')]//following::input")
	private WebElement maxScore; 
	
	@FindBy(name="submit")
	private WebElement submit; 
	
	public void clickAssignment() {
		this.createAssignment.click(); 
	}
	
	public void enterAssignmtNm(String assignmentNm) {
		this.assignmentNm.clear();
		this.assignmentNm.sendKeys(assignmentNm);
	}
	
	public void enterDescription(String description) {
		this.description.clear();
		this.description.sendKeys(description);
	}
	
	public void clickAdvancedSettgs() {
		this.advancedsettgsBtn.click(); 
	}
	
	public void entermaxScore(String maxScore) {
		this.maxScore.clear();
		this.maxScore.sendKeys(maxScore);
	}
	
	public void clickSubmit() {
		this.submit.click(); 
	}


}
