package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCoursePOM {
private WebDriver driver; 
	
	public CreateCoursePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='title']")
	private WebElement courseNm; 
	
	@FindBy(id="advanced_params")
	private WebElement advancedsettgsBtn; 
	
	@FindBy(xpath="//label[contains(.,'Category')]//following::div[@class='filter-option']")
	private WebElement category;
	
	@FindBy(id="add_course_wanted_code")
	private WebElement courseCd; 
		
	@FindBy(xpath="//label[contains(.,'Language')]//following::div[@class='filter-option']")
	private WebElement languageList;
	
	@FindBy(id="add_course_submit")
	private WebElement createCoureseBtn; 
	
	@FindBy(xpath="//a[@title='Add an introduction text']")
	private WebElement addIntroBtn; 
	
	@FindBy(xpath="//iframe[@title='Rich Text Editor, intro_content']")
	private WebElement Introfrm;
	
	@FindBy(id="introduction_text_intro_cmdUpdate")
	private WebElement saveIntroBtn; 
	
	public void entercourseNm(String courseNm) {
		this.courseNm.sendKeys(courseNm); 
	}
	
	public void clickAdvancedSettgs() {
		this.advancedsettgsBtn.click(); 
	}
	
	public void enterCategory(String category) {
		this.category.click(); 
		Actions act=new Actions(driver);
		WebElement e=this.driver.findElement(By.xpath("//label[contains(.,'Category')]//following::span[contains(text(),'"+category+"')]"));
		act.moveToElement(e).build().perform();
		act.click().build().perform();
	}
	
	public void enterCourseCd(String courseCd) {
		this.courseCd.sendKeys(courseCd);
	}
	
	public void enterLanguage(String language){
		Actions act1=new Actions(driver);
		this.languageList.click(); 
		WebElement el=this.driver.findElement(By.xpath("//label[contains(.,'Language')]//following::span[contains(text(),'"+language+"')]"));
		act1.moveToElement(el).build().perform();
		act1.click().build().perform();
	}
	
	public void clickCreatecourse() {
		this.createCoureseBtn.click(); 
	}
	
	public void addIntro() {
		this.addIntroBtn.click(); 
	}
	
	//Enter Introduction in textbox...
		public void enterCourseIntro(String introText) throws InterruptedException {
			Thread.sleep(10000);
			System.out.println("No of frames in page: "+this.driver.findElements(By.tagName("iframe")).size());
			this.driver.switchTo().frame(this.Introfrm);
			WebElement ele=this.driver.findElement(By.xpath("//html/body/p"));
			Actions act=new Actions(this.driver);
			act.moveToElement(ele).build().perform();
			act.click().build().perform();
			act.sendKeys(introText).build().perform();;
			this.driver.switchTo().defaultContent();
		}
	
	public void saveIntro() {
		this.saveIntroBtn.click(); 
	}
	

}
