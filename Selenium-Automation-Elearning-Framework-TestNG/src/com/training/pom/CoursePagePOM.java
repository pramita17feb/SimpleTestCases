package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CoursePagePOM {
private WebDriver driver; 

	public CoursePagePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	

	
	@FindBy(xpath="//div[@class='page-course']/p")
	private WebElement intro; 
	
	@FindBy(xpath="//*[@title='Assignments']")
	private WebElement assignment; 
	
	//Course Description icon for a course
	@FindBy(xpath="//*[@title='Course description']")
	private WebElement courseDescriptn;
	
	//description icon under Course Description......
	@FindBy(xpath="//div[@id='toolbar']//img[@title='Description']")
	private WebElement adddescriptn;
	
	//description/objective/topic Title......
	@FindBy(id="course_description_title")
	private WebElement descriptnTitle;
	
	//description/objective/topic text box......
	@FindBy(xpath="//iframe[@title='Rich Text Editor, contentDescription']")
	private WebElement descriptnContentfrm;
	
	//description/objective/topic save button......
	@FindBy(id="course_description_submit")
	private WebElement savedescriptn;
	
	//Objectives icon under Course Description.......
	@FindBy(xpath="//div[@id='toolbar']//img[@title='Objectives']")
	private WebElement addObjectives;
	
	//Topics icon under Course Description.......
	@FindBy(xpath="//div[@id='toolbar']//img[@title='Topics']")
	private WebElement addTopics;
	
	//added Description under Course Description
	@FindBy(xpath="//div[@id='description_1']//div[@class='panel-heading']")
	private WebElement description;
		
	//added Description under Course Description
	@FindBy(xpath="//div[@id='description_2']//div[@class='panel-heading']")
	private WebElement objective;
	
	//added Description under Course Description
	@FindBy(xpath="//div[@id='description_3']//div[@class='panel-heading']")
	private WebElement topic;
	
	@FindBy(xpath="//*[@title='Assessments']")
	private WebElement assessments;
	
	@FindBy(xpath="//*[@title='Tests']")
	private WebElement tests; 
	
	
		
	public void clickAssignments() {
		this.assignment.click(); 
	}
	
	public void clickcourseDescriptn() {
		this.courseDescriptn.click(); 
	}
	
	public void clickDescriptnIcon() {
		this.adddescriptn.click(); 
	}
	
	//description/objective/topic Title......
	public void enterTitle(String title) {
		this.descriptnTitle.sendKeys(title); 
	}
	
	//description/objective/topic text box.............Frame handled.................
	public void enterContent(String content) throws InterruptedException {
		Thread.sleep(10000);
		System.out.println("No of frames in page: "+this.driver.findElements(By.tagName("iframe")).size());
		this.driver.switchTo().frame(this.descriptnContentfrm); 
		WebElement ele=this.driver.findElement(By.xpath("html[@dir='ltr' and @lang='en']/body/p"));
		Actions act=new Actions(driver);
		act.moveToElement(ele).build().perform();
		act.click().build().perform();
		act.sendKeys(content).build().perform();;
		this.driver.switchTo().defaultContent();
	}
	
	//description/objective/topic save button......common web Elements.............
	public void clickSave() {
		this.savedescriptn.click(); 
	}
	
	public void clickObjectives() {
		this.addObjectives.click(); 
	}
	
	public void clickTopics() {
		this.addTopics.click(); 
	}
	
	public void clickAssessments() {
		this.assessments.click(); 
	}
	
	public void clickTests() {
		this.tests.click(); 
	}
	
	public boolean verifyIntroDisplayed() throws InterruptedException {
		return(this.intro.isDisplayed());
	}
	
	public boolean verifyDescriptionDisplayed() {
		return (this.description.isDisplayed());
	}
	
	public boolean verifyObjectiveDisplayed() {
		return (this.objective.isDisplayed());
	}
	
	public boolean verifyTopicDisplayed() {
		return (this.topic.isDisplayed());
	}
	

}
