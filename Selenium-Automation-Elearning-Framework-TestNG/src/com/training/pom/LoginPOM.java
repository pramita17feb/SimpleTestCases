package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPOM {
	private WebDriver driver; 
	
	public LoginPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="login")
	private WebElement userName; 
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(id="form-login_submitAuth")
	private WebElement loginBtn; 
	
	@FindBy(xpath="//a[text()=' I lost my password ']")
	private WebElement lostpasswd;
	
	@FindBy(id="lost_password_user")
	private WebElement lostPsswdUser;
	
	@FindBy(id="lost_password_submit")
	private WebElement sendMsgBtn;
	
	@FindBy(xpath="//a[text()=' Sign up! ']")
	private WebElement signUpBtn;
	
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}
	
	public void clickLostpasswd() {
		this.lostpasswd.click(); 
	}
	
	public void enterLostPsswdUser(String mailid) {
		this.lostPsswdUser.sendKeys(mailid); 
	}
	
	public void clickSendMsgBtn() {
		this.sendMsgBtn.click(); 
	}
	
	public void clicksignUpBtn() {
		this.signUpBtn.click(); 
	}
	
	public String pageTitle() {
		String title=driver.getTitle();
		return title;
	}
}
