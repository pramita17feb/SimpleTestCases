package com.training.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TestsPOM {
private WebDriver driver;
	
	public TestsPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@title='Create a new test']")
	private WebElement createTest; 
	
	@FindBy(id="exercise_title")
	private WebElement testNm; 
	
	@FindBy(id="advanced_params")
	private WebElement advancedsettgsBtn;
	
	@FindBy(xpath="//iframe[@title='Rich Text Editor, exerciseDescription']")
	private WebElement contextfrm; 
	
	@FindBy(xpath="//*[@class='radio'][1]//input")
	private WebElement feedback;
	
	@FindBy(xpath="//*[@id='activate_start_date_check']//input")
	private WebElement enableStartTime; 
	
	@FindBy(id="start_time_alt_text")
	private WebElement selectStartTime; 
	
	@FindBy(xpath="//*[@class='ui-datepicker-month']")
	private WebElement timeSelectionMonth; 
	
	@FindBy(xpath="//*[@class='ui-datepicker-year']")
	private WebElement timeSelectionYear; 
	
	@FindBy(name="pass_percentage")
	private WebElement passpercent; 
	
	@FindBy(xpath="//button[contains(text(),' Proceed to questions')]")
	private WebElement proceedtoquestnBtn; 
	
	@FindBy(xpath="//*[@title='Multiple choice']")
	private WebElement multiplechoiceIcon;
	
	@FindBy(name="questionName")
	private WebElement question;
	
	@FindBy(xpath="//iframe[@title='Rich Text Editor, answer[1]']")
	private WebElement answer1frm; 
	
	@FindBy(xpath="//iframe[@title='Rich Text Editor, comment[1]']")
	private WebElement comment1frm; 
	
	@FindBy(xpath="//iframe[@title='Rich Text Editor, answer[2]']")
	private WebElement answer2frm; 
	
	@FindBy(xpath="//iframe[@title='Rich Text Editor, comment[2]']")
	private WebElement comment2frm;
	
	@FindBy(xpath="//iframe[@title='Rich Text Editor, answer[3]']")
	private WebElement answer3frm; 
	
	@FindBy(xpath="//iframe[@title='Rich Text Editor, comment[3]']")
	private WebElement comment3frm;
	
	@FindBy(xpath="//iframe[@title='Rich Text Editor, answer[4]']")
	private WebElement answer4frm; 
	
	@FindBy(xpath="//iframe[@title='Rich Text Editor, comment[4]']")
	private WebElement comment4frm;
	
	@FindBy(xpath="//input[@type='radio' and @value='1']")
	private WebElement radioAns1;
	
	@FindBy(xpath="//input[@type='radio' and @value='2']")
	private WebElement radioAns2;
	
	@FindBy(xpath="//input[@type='radio' and @value='3']")
	private WebElement radioAns3;
	
	@FindBy(xpath="//input[@type='radio' and @value='4']")
	private WebElement radioAns4;
	
	@FindBy(id="question_admin_form_weighting[1]")
	private WebElement score1;
	
	@FindBy(id="question_admin_form_weighting[2]")
	private WebElement score2;
	
	@FindBy(id="question_admin_form_weighting[3]")
	private WebElement score3;
	
	@FindBy(id="question_admin_form_weighting[4]")
	private WebElement score4;
	
	@FindBy(id="submit-question")
	private WebElement addQuestionBtn;
	
	@FindBy(xpath="//div[@class='alert alert-info' and contains(text(),'questions, for a total score (all questions) of')]")
	private WebElement displaymsg;
	
	@FindBy(xpath="//*[@title='Preview']")
	private WebElement preview;
	
	@FindBy(xpath="//a[text()='Start test']")
	private WebElement startTestBtn;
	
	@FindBy(xpath="//a[text()='online quiz']")
	private WebElement testlink;
	
	@FindBy(xpath="//div[contains(@id,'question_div')]/div/strong")
	private WebElement questText;
	
	@FindBy(xpath="//label[@class='radio ']//p")
	private WebElement radioOptn;
	
	@FindBy(name="save_now")
	private WebElement nextQtnBtn;
	
	@FindBy(xpath="//button[text()='End test']")
	private WebElement endTestBtn;
	
	//Result and feedback icon for admin user in Test....
	@FindBy(xpath="//*[@title='Results and feedback']")
	private WebElement resultandFeedback;
	
	@FindBy(xpath="//*[@title='Grade activity']")
	private WebElement gradeActivity;
	

	
	public void clickCreateTest() {
		this.createTest.click(); 
	}
	
	public void enterTestNm(String testNm) {
		this.testNm.sendKeys(testNm);
	}
	
	public void clickAdvancedSettgs() {
		this.advancedsettgsBtn.click(); 
	}
	
	//enter text in Test context text box........
	public void enterContext(String context) throws InterruptedException {
		Thread.sleep(10000);
		System.out.println("No of frames in page: "+this.driver.findElements(By.tagName("iframe")).size());
		this.driver.switchTo().frame(this.contextfrm);
		WebElement ele=this.driver.findElement(By.xpath("html[@dir='ltr' and @lang='en']/body/p"));
		Actions act=new Actions(driver);
		act.moveToElement(ele).build().perform();
		act.click().build().perform();
		act.sendKeys(context).build().perform();;
		driver.switchTo().defaultContent();
	}
	
	public void clickFeedbackRadioBtn() {
		this.feedback.click(); 
	}
	
	public void clickEnableStartTimeChckbox() {
		this.enableStartTime.click(); 
	}
	
	//Start date selection...........Select drop down........
	public void selectStartTimeMonthYear(String month,String year) {
		this.selectStartTime.click();
		Select s=new Select(timeSelectionMonth);
		s.selectByVisibleText(month);//"MMM" Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec
		s=new Select(timeSelectionYear);
		s.selectByVisibleText(year);
	}
	
	public void passpercent(String percent) {
		this.passpercent.sendKeys(percent);
	}
	
	public void clickproceedtoquestnBtn() {
		this.proceedtoquestnBtn.click(); 
	}
	
	public void clickmultiplechoiceIcon() {
		this.multiplechoiceIcon.click(); 
	}
	
	// Enter the Question in Question Text Box.........
	public void enterQuestion(String question) {
		this.question.sendKeys(question);
	}
	
	//enter text in MCQ option1 text box...............Frame handled.................
		public void enterMCQoption1(String option1) throws InterruptedException {
			Actions act=new Actions(driver);
			Thread.sleep(10000);
			this.driver.switchTo().frame(this.answer1frm);
			WebElement ele=this.driver.findElement(By.xpath("html[@dir='ltr' and @lang='en']/body/p"));
			act.moveToElement(ele).build().perform();
			act.click().build().perform();
			act.sendKeys(option1).build().perform();;
			this.driver.switchTo().defaultContent();
		}
		
		//enter text in MCQ option2 text box...............Frame handled.................
		public void enterMCQoption2(String option2) throws InterruptedException {
			Actions act=new Actions(driver);
			Thread.sleep(10000);
			this.driver.switchTo().frame(this.answer2frm);
			WebElement ele=this.driver.findElement(By.xpath("html[@dir='ltr' and @lang='en']/body/p"));
			act.moveToElement(ele).build().perform();
			act.click().build().perform();
			act.sendKeys(option2).build().perform();;
			this.driver.switchTo().defaultContent();
		}
		
		//enter text in MCQ option3 text box...............Frame handled.................
		public void enterMCQoption3(String option3) throws InterruptedException {
			Actions act=new Actions(driver);
			Thread.sleep(10000);
			this.driver.switchTo().frame(this.answer3frm);
			WebElement ele=this.driver.findElement(By.xpath("html[@dir='ltr' and @lang='en']/body/p"));
			act.moveToElement(ele).build().perform();
			act.click().build().perform();
			act.sendKeys(option3).build().perform();;
			this.driver.switchTo().defaultContent();
		}
		
		//enter text in MCQ option4 text box...............Frame handled.................
		public void enterMCQoption4(String option4) throws InterruptedException {
			Actions act=new Actions(driver);
			Thread.sleep(10000);
			this.driver.switchTo().frame(this.answer4frm);
			WebElement ele=this.driver.findElement(By.xpath("html[@dir='ltr' and @lang='en']/body/p"));
			act.moveToElement(ele).build().perform();
			act.click().build().perform();
			act.sendKeys(option4).build().perform();;
			this.driver.switchTo().defaultContent();
		}
		
		//enter text in MCQ comment text box1/2/3/4........	

		public void enterMCQcomment1(String comment1) throws InterruptedException {
			Actions act=new Actions(driver);
			Thread.sleep(10000);
			this.driver.switchTo().frame(this.comment1frm);
			WebElement ele=this.driver.findElement(By.xpath("html[@dir='ltr' and @lang='en']/body/p"));
			act.moveToElement(ele).build().perform();
			act.click().build().perform();
			act.sendKeys(comment1).build().perform();;
			this.driver.switchTo().defaultContent();
		}
		
		public void enterMCQcomment2(String comment2) throws InterruptedException {
			Actions act=new Actions(driver);
			Thread.sleep(10000);
			this.driver.switchTo().frame(this.comment2frm);
			WebElement ele=this.driver.findElement(By.xpath("html[@dir='ltr' and @lang='en']/body/p"));
			act.moveToElement(ele).build().perform();
			act.click().build().perform();
			act.sendKeys(comment2).build().perform();;
			this.driver.switchTo().defaultContent();
		}
		
		public void enterMCQcomment3(String comment3) throws InterruptedException {
			Actions act=new Actions(driver);
			Thread.sleep(10000);
			this.driver.switchTo().frame(this.comment3frm);
			WebElement ele=this.driver.findElement(By.xpath("html[@dir='ltr' and @lang='en']/body/p"));
			act.moveToElement(ele).build().perform();
			act.click().build().perform();
			act.sendKeys(comment3).build().perform();;
			this.driver.switchTo().defaultContent();
		}
		
		public void enterMCQcomment4(String comment4) throws InterruptedException {
			Actions act=new Actions(driver);
			Thread.sleep(10000);
			this.driver.switchTo().frame(this.comment4frm);
			WebElement ele=this.driver.findElement(By.xpath("html[@dir='ltr' and @lang='en']/body/p"));
			act.moveToElement(ele).build().perform();
			act.click().build().perform();
			act.sendKeys(comment4).build().perform();;
			this.driver.switchTo().defaultContent();
		}
		
		//Answers radio buttons while adding questions...............
	public void clickRadioAns(String n) {
		
		switch(n) {		
		case("1"):	
			this.radioAns1.click();
			break;
		
		case("2"):
			this.radioAns2.click();
			break;
		
		case("3"):
			this.radioAns3.click();
			break;
		
		case("4"):
			this.radioAns4.click();
			break;
		}
	}
	
	//setting score while adding questions...................
public void enterScore(String n, String sc) {
		
		switch(n) {		
		case("1"):	
			this.score1.sendKeys(sc);;
			break;
		
		case("2"):
			this.score2.sendKeys(sc);
			break;
		
		case("3"):
			this.score3.sendKeys(sc);
			break;
		
		case("4"):
			this.score4.sendKeys(sc);
			break;
		}
	}
	
	
			
	public void clickaddQuestion() {
		this.addQuestionBtn.click(); 
	}
	
	//Get message alert after adding question
	public String verifyMsg() {
		String msg=this.displaymsg.getText(); 
		return msg;
	}
	
	public void clickPreview() {
		this.preview.click(); 
	}
	
	public boolean verifyStartTestBtn() {
		boolean flag=this.startTestBtn.isDisplayed();
		return flag;
	}
	
	public void clickStartTestBtn() {
		this.startTestBtn.click();
	}
	
	
	public void clickTestLink() {
		this.testlink.click();
	}
	
	public String getQuestn() {
		return(this.questText.getText());		
	}
	
	//select answer radio button 1 having correct answer....
	public void clickAnsRadioBtn(String answr) {
		List<WebElement> optns=driver.findElements(By.xpath("//label[@class='radio ']//p"));
		for(WebElement e:optns) {
			if(e.getText().contentEquals(answr)) {
				e.findElement(By.xpath("./preceding-sibling::input")).click();//label[@class='radio ']/input[contains(@id,'-5')]
			break;
			}
		}		
	}
	
	
	public void clickNextQtn() {
		this.nextQtnBtn.click();
	}
	
	public void clickEndTestBtn() {
		this.endTestBtn.click();
	}
	
	public void clickResultandFeedback() {
		this.resultandFeedback.click();
	}
	
	public void clickGradeActivity() {
		this.gradeActivity.click();
	}
	
	/*
	 * public void clickAnsRadioBtn2(String answr) { List<WebElement>
	 * optns=driver.findElements(By.xpath("//label[@class='radio ']//p"));
	 * for(WebElement e:optns) { if(e.getText().contentEquals("java"))
	 * driver.findElement(By.
	 * xpath("//label[@class='radio ']/input[@id='choice-156-2']")).click(); } }
	 */
	
	
	}
