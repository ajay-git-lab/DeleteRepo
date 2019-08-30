package com.facebooksignup.qa.pages;

//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.facebooksignup.qa.base.TestBase;

public class SignupPage extends TestBase {

	@FindBy(xpath="//input[@name='firstname']")
	WebElement firstname;
	
	@FindBy(id="u_0_n")
	WebElement lastname;
	
	@FindBy(id="u_0_q")
	WebElement emailOrMobile;
	
	@FindBy(id="u_0_t")
	WebElement emailOrMobile2;
	
	@FindBy(id="u_0_x")
	WebElement password;
	
	@FindBy(id="day")
	WebElement birthDay;
	
	@FindBy(id="month")
	WebElement birthMonth;
	
	@FindBy(id="year")
	WebElement birthYear;
	
	@FindBy(id="u_0_5")
	WebElement femaleRadioBtn;
	
	@FindBy(xpath="//label[contains(text(),'Male')]//parent::span//preceding-sibling::input[@type='radio']")
	WebElement maleRadioBtn;
	
	@FindBy(id="u_0_7")
	WebElement customRadioBtn;
	
	@FindBy(xpath="//button[contains(text(),'Sign Up')]")
	WebElement submitBtn;
	
	public SignupPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String fbSignUp(String ftName, String ltName, String emailOrMob, String pwd, String day, String month, String year, String sex) throws InterruptedException {
		
		System.out.println("Enter fbSignUp");
		firstname.sendKeys(ftName);
		//driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(ftName);
		lastname.sendKeys(ltName);
		emailOrMobile.sendKeys(emailOrMob);
		emailOrMobile2.sendKeys(emailOrMob);
		password.sendKeys(pwd);
		Select selectDay = new Select(birthDay);
		selectDay.selectByVisibleText(day);
		
		Select selectMonth = new Select(birthMonth);
		selectMonth.selectByVisibleText(month);
		
		Select selectYear = new Select(birthYear);
		selectYear.selectByVisibleText(year);
		
		if(sex =="Female") {
			femaleRadioBtn.click();
		} else if (sex =="Male") {
			maleRadioBtn.click();
		} else if (sex =="Custom") {
			customRadioBtn.click();
			Select selectPronoun = new Select(driver.findElement(By.xpath("//select[@name='preferred_pronoun']")));
			selectPronoun.selectByIndex(0);
			driver.findElement(By.name("custom_gender")).sendKeys("Female");
		}
		
		submitBtn.click();
		Thread.sleep(5000);
		
		new Actions(driver).sendKeys(Keys.ESCAPE).build().perform();
		
		String welcomeMsg = driver.findElement(By.xpath("//Div[contains(text(),'Welcome to Facebook')]")).getText();
		
		/*
		 * Alert alert = driver.switchTo().alert(); 
		 * alert.dismiss();
		 */
		 
		return welcomeMsg;
								
	}
	
	
}
