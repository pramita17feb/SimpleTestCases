package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AssignmentPOM {
private WebDriver driver; 
	
	public AssignmentPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@title='Create assignment']")
	private WebElement createAssignment; 
			
	@FindBy(xpath="//label[contains(.,'Assignment name')]//following::input")
	private WebElement assignmentNm; 
	
	@FindBy(xpath="//iframe[@title='Rich Text Editor, description']")
	private WebElement descriptionfrm;
	
	
	@FindBy(id="advanced_params")
	private WebElement advancedsettgsBtn; 
	
	@FindBy(xpath="//label[contains(.,'Maximum score')]//following::input")
	private WebElement maxScore; 
	
	@FindBy(xpath="//label[contains(.,'Document type')]//following::button[contains(@class,'dropdown-toggle btn-default')]")
	private WebElement docType; 
	
	
	@FindBy(name="submit")
	private WebElement submit; 
	
	@FindBy(id="work_comment_button")//Correct or rate page send Message button
	private WebElement sendmsg; 
	
	
	//click on Create Assignment icon...
	public void createAssignment() {
		this.createAssignment.click(); 
	}
	
	//enter assignment name......
	public void enterAssignmtNm(String assignmentNm) {
		this.assignmentNm.clear();
		this.assignmentNm.sendKeys(assignmentNm);
	}
	
	//Description for create Assignment or correct and rate icon comment.......Frame handled.................
	public void enterDescription(String description) throws InterruptedException {
		Thread.sleep(10000);
		System.out.println("No of frames in page: "+this.driver.findElements(By.tagName("iframe")).size());
		this.driver.switchTo().frame(this.descriptionfrm);
		WebElement ele=this.driver.findElement(By.xpath("//html[@lang='en']/body/p"));
		Actions act=new Actions(this.driver);
		act.moveToElement(ele).build().perform();
		act.click().build().perform();
		act.sendKeys(description).build().perform();;
		this.driver.switchTo().defaultContent();
	}
	
	public void clickAdvancedSettgs() {
		this.advancedsettgsBtn.click(); 
	}
	
	public void entermaxScore(String maxScore) {
		this.maxScore.clear();
		this.maxScore.sendKeys(maxScore);
	}
	
	//get default document type...
	public String defaultDocType() {
		String s=this.docType.getAttribute("title");
		return s;
	}
	
	//click validate button......
	public void clickSubmit() {
		this.submit.click(); 
	}
	
	//click an Assignment created already...
	public void clickAnAssignmnt(String assignmntNm) {
		WebElement assignmentNm=driver.findElement(By.xpath("//table[@id='workList']//td[contains(@title,'"+assignmntNm+"')]/a"));
		assignmentNm.click();
	}
	
	public void clickReviewStudentAssignmnt(String assignmntNm) {
		WebElement reviewIcon=driver.findElement(By.xpath("//tbody//td[contains(@title,'"+assignmntNm+"')]//following-sibling::td[@aria-describedby='results_actions']//a[@title='View']"));
		reviewIcon.click();
	}
	
	public void clickSendmsgBtn() {
		this.sendmsg.click();
		List<WebElement> successmsg=driver.findElements(By.xpath("//div[@class='alert alert-info']"));
		for(WebElement e:successmsg) {
			System.out.println(e.getText());
		}
	}


}
