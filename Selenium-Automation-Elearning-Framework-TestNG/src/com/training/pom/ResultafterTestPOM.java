package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ResultafterTestPOM {
	
private WebDriver driver;
	
	public ResultafterTestPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//Result header containing test name.....
	@FindBy(xpath="//*[@class='question-result']//h3")
	private WebElement resultHeader; 
	
	//User info for test result.....
	@FindBy(xpath="//*[@class='user-info']/strong")
	private WebElement userInfo;
	
	//send mail check in test result page when logged in as admin.....
	@FindBy(name="send_notification")
	private WebElement sendEmailCheckbox;
	
	@FindBy(xpath="//iframe[@title='Rich Text Editor, notification_content']")
	private WebElement emailMsgfrm;
	
	@FindBy(id="form-email_submit")
	private WebElement correctTestBtn;
	
	//Get header of the result after clicking on End Test button......
		public String getResultHeader() {
			String header=this.resultHeader.getText(); 
			return header;
		}
		
	//Get user first name and last name for test result ......
		public String getUserInfo() {
			String user=this.userInfo.getText(); 
			return user;
		}
		
	//Send email check box
		public void clickSendEmailCheckbox() {
			this.sendEmailCheckbox.click();
		}
		
	//verify email msg frame with course name and messages..................
		public void verifyEmailMsg(String courseNm) throws InterruptedException {
			Actions act=new Actions(driver);
			Thread.sleep(10000);
			this.driver.switchTo().frame(this.emailMsgfrm);
			WebElement ele=this.driver.findElement(By.xpath("html[@dir='ltr' and @lang='en']/body/div[@id='email-message']/div[1]/p"));
			System.out.println(ele.getText());
			Assert.assertTrue(ele.getText().contains(courseNm));
			String expectedTxt="Your following attempt has been viewed/commented/corrected by the trainer";
			ele=driver.findElement(By.xpath("html[@dir='ltr' and @lang='en']/body/div[@id='email-message']/p[contains(text(),'"+expectedTxt+"')]"));
			Assert.assertTrue(ele.isDisplayed());
			
			String expectedTxt1="Click the link below to access your account and view your commented Examsheet.";
			ele=driver.findElement(By.xpath("html[@dir='ltr' and @lang='en']/body/div[@id='email-message']/p[contains(text(),'"+expectedTxt1+"')]"));
			Assert.assertTrue(ele.isDisplayed());
			
			this.driver.switchTo().defaultContent();
		}
		
		public void clickCorrectTestBtn() {
			this.correctTestBtn.click();
		}

}
