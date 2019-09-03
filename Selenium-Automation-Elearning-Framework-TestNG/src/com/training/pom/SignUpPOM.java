package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignUpPOM {
	
private WebDriver driver; 
	
	public SignUpPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="registration_firstname")
	private WebElement firstName; 
	
	@FindBy(id="registration_lastname")
	private WebElement lastName;
	
	@FindBy(id="registration_email")
	private WebElement email;
	
	@FindBy(id="username")
	private WebElement userName;
	
	@FindBy(id="pass1")
	private WebElement passWd;
	
	@FindBy(id="pass2")
	private WebElement confirmPassWd;
	
	@FindBy(id="registration_phone")
	private WebElement phone;
	
	@FindBy(xpath="//*[text()='Teach courses']")
	private WebElement radioBtnTeacher;
	
	@FindBy(id="registration_submit")
	private WebElement registerBtn;
	
	@FindBy(xpath="//button[@class='btn dropdown-toggle btn-default']")
	private WebElement languageBtn;
	
	@FindBy(xpath="//ul[@class='dropdown-menu inner ']//*[text()='English']")
	private WebElement language;
	
	@FindBy(xpath="//*[text()='Your personal settings have been registered.']")
	private WebElement message;
	
	@FindBy(xpath="//*[text()='An e-mail has been sent to remind you of your login and password.']")
	private WebElement message1;
	
	public void enterfirstName(String firstName) {
		this.firstName.clear(); 
		this.firstName.sendKeys(firstName); 
	}
	
	public void enterlastName(String lastName) {
		this.lastName.clear(); 
		this.lastName.sendKeys(lastName); 
	}
	
	public void enteremail(String email) {
		this.email.clear(); 
		this.email.sendKeys(email);
	}
	
	public void enteruserName(String username) {
		this.userName.clear(); 
		this.userName.sendKeys(username);
	}
	
	public void enterpassWd(String passWd) {
		this.passWd.clear(); 
		this.passWd.sendKeys(passWd);
	}
	
	public void enterconfirmPassWd(String passWd) {
		this.confirmPassWd.clear(); 
		this.confirmPassWd.sendKeys(passWd);
	}
	
	public void enterphone(String phone) {
		this.phone.clear(); 
		this.phone.sendKeys(phone);
	}
	
	public void selectLang(String lang) {
		Actions act=new Actions(driver);
		this.languageBtn.click();
		act.moveToElement(language).build().perform();
		act.click().build().perform();
	}
	
	public void clickradioBtnTeacher() {
		this.radioBtnTeacher.click(); 
	}

	public void clickregisterBtn() {
		this.registerBtn.click(); 
	}
	
	public String registermssg() {
		String msg=this.message.getText();
		String msg1=this.message1.getText();
		String message=msg+"\n"+msg1;
		return message;
		
	}
	
}
