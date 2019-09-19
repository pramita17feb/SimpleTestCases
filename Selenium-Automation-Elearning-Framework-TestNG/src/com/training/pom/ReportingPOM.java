package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReportingPOM {
private WebDriver driver; 
	
	public ReportingPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@id='navbar']//following::a[@title='Reporting']")
	private WebElement reportingTab; 
	
	@FindBy(xpath="//a[text()='Followed students']")
	private WebElement followedStudent; 
	
	@FindBy(xpath="//*[@name='keyword' and @type='text']")
	private WebElement keyword; 
	
	@FindBy(id="search_user_submit")
	private WebElement keywordSearch; 
		
	@FindBy(xpath="//a[text()='testCourse']//parent::td//following-sibling::td[6]//a")
	private WebElement detail;
	
	@FindBy(xpath="//*[text()='student student']")
	private WebElement studentNm;
	
	@FindBy(xpath="//div[contains(text(),'Average progress in courses')]")
	private WebElement avgProgress;
	
	@FindBy(xpath="//div[contains(text(),'Average of tests in Learning Paths')]")
	private WebElement avgOfTests;
	
	@FindBy(xpath="//*[text()='Achieved skills']")
	private WebElement achievedSkills;
	
	
	
	public void clickReporting() {
		this.reportingTab.click(); 
	}
	
	public void clickFollowedStudnt() {
		this.followedStudent.click(); 
	}
	
	public void enterKeyword(String studentNm) {
		this.keyword.sendKeys(studentNm); 
	}
	
	public void clickSearchStudnt() {
		this.keywordSearch.click(); 
	}
	
	//select student detail....
	 public void clickStudntdtl(String firstnm, String lastnm) {
		List<WebElement> trs=driver.findElements(By.xpath("//table[@class='table table-bordered data_table']/tbody/tr//following-sibling::tr"));
		for(WebElement tr:trs) {
			if(tr.findElement(By.xpath("./td[1]/a")).getText().contentEquals(firstnm))
				if(tr.findElement(By.xpath("./td[2]/a")).getText().contentEquals(lastnm))
					tr.findElement(By.xpath("./td[5]/a[2]")).click();
				
			}
			
		}
		
	// select detail icon....	
	public void clickDetails(String coursenm) {
		
		this.detail.click(); 
	}
	
	
	public boolean validateOveralldetail() {
		boolean flag=false;
		if((this.studentNm.isDisplayed())&&(this.avgProgress.isDisplayed())&&(this.avgOfTests.isDisplayed()))
			flag=true;
			else 
				flag=false;
		return flag;
	}


}
