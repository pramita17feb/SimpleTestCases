package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
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
		
	@FindBy(xpath="//*[@id='courses']//li[@class='list-group-item add-course']//a")
	private WebElement createCourse; 
	
	@FindBy(xpath="//*[@id='courses']//li[@class='list-group-item list-course']//a")
	private WebElement courseCatalog; 
	
	//course search for Student....
	@FindBy(name="search_term")
	private WebElement courseSearch; 
	
	@FindBy(xpath="//*[@name='search_term']//following::button[@type='submit']")
	private WebElement courseSearchBtn; 
	
	
	@FindBy(xpath="//a[contains(text(),'Subscribe')]")
	private WebElement subscribeBtn; 
	
	@FindBy(xpath="//a[contains(text(),'Course list')]")
	private WebElement courseListAdmin; 
	
	//course search for admin 
	@FindBy(id="course-search-keyword")
	private WebElement courseSearchAadmin; 
	
	@FindBy(id="search_simple_submit")
	private WebElement courseSearchBtnAadmin; 
	
	
	
	//to click on subscribed course by student or by teacher in courses page 
	public void clickCourseLink(String courseNm) {
		List<WebElement> ele=this.driver.findElements(By.xpath("//*[@class='classic-courses']//div[2]/h4/a"));
		for(WebElement e:ele) {
			if(e.getText().contentEquals(courseNm)) {
				e.click();
				break;
			}
		}
	}
		
	
	//to create course by teacher
	public void clickCreateCourse() {
		this.createCourse.click(); 
	}
	
	
	//course catalog
	public void clickCourseCatalog() {
		this.courseCatalog.click(); 
	}
	
	//course search...
	public void enterCourseSearch(String course) {
		this.courseSearch.sendKeys(course);
	}
	
	public void clickCourseSearch() {
		this.courseSearchBtn.click(); 
	}
	
	//to subscribe course
	public void clickSubscribeCourse(String course) {
		List<WebElement> ele=driver.findElements(By.xpath("//div[@class='grid-courses row']//div[@class='image']/a"));
		for(WebElement e:ele) {
			String s=e.getAttribute("title");
			if(course.equals(s)) {
				e.click();
				break;
			}
		}
		this.subscribeBtn.click(); 
	}
	
	//Course list for admin login........
	public void clickCourseListAdmin() {
		this.courseListAdmin.click();
	}
	
	//enter search keyword in search box for admin login.....
	public void enterCorseSearchAdmin(String courseNm) {
		this.courseSearchAadmin.sendKeys(courseNm);
	}
	
	//Click on Search button for course search in Admin login........
	public void clickCourseSearchBtnAdmin() {
		this.courseSearchBtnAadmin.click();
	}
	
	
	public void clickCourseLinkAdmin(String courseNm) {
		List<WebElement> trs=driver.findElements(By.xpath("//*[@id='course-list']/tbody/tr//following-sibling::tr"));
		for(WebElement tr:trs) {
			WebElement e=tr.findElement(By.xpath(".//td[2]/a"));
			if(e.getText().contentEquals(courseNm)) {
				e.click();
				break;
			}
		
			}
			
		}
		
	}


