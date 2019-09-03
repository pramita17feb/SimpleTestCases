package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GmailPOM {
private WebDriver driver; 
	
	public GmailPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[contains(@class,'hercules-header h-c-header h-c-header--product-marketing-one-tier header--desktop')]//*[contains(text(),'Sign in')]")
	private WebElement gsigninBtn;
	
	@FindBy(xpath="//input[@aria-label='Email or phone']")
	private WebElement gemail;
	
	@FindBy(xpath="//span[contains(text(),'Next')]")
	private WebElement gnextBtn;
	
	@FindBy(xpath="//*[@type='password']")
	private WebElement gpasswd;
	
	public void clicksigninBtn() {
		this.gsigninBtn.click(); 
	}
	
	public void entergemail(String email) {
		this.gemail.clear(); 
		this.gemail.sendKeys(email); 
	}
	
	public void clicknextBtn() {
		this.gnextBtn.click(); 
	}
	
	public void entergpasswd(String passwd) {
		this.gpasswd.clear(); 
		this.gpasswd.sendKeys(passwd); 
	}

}
