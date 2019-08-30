package com.facebooksignup.qa.testcases;



import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.facebooksignup.qa.base.TestBase;
import com.facebooksignup.qa.pages.SignupPage;
import com.facebooksignup.qa.utils.TestUtil;

public class SignupPageTest extends TestBase {

	SignupPage signupPage;
	TestUtil testUtil;
	String sheetName = "signup";
	
	public SignupPageTest() {
		super();
		System.out.println("Super init");
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException {
		initialization();
		signupPage = new SignupPage();
		testUtil = new TestUtil();
	}
	
	@DataProvider 
	public Object[][] getFacebookRegistrationTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		
		//System.out.println("Data loaded");
		
		return data;
		
	}
	
	@Test(dataProvider = "getFacebookRegistrationTestData")
	public void validateFacebookRegistration(String firstname, String lastname, String emailOrMobile, String password, String objDay, String objMonth, String objYear, String sex) throws InterruptedException {
		String welcome = signupPage.fbSignUp(firstname, lastname, emailOrMobile, password, objDay, objMonth, objYear, sex);
		Assert.assertEquals(welcome, "Welcome to Facebook");
		}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	
}
