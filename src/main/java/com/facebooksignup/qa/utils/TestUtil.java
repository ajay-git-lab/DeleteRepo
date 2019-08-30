package com.facebooksignup.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.facebooksignup.qa.base.TestBase;

public class TestUtil extends TestBase{

	public static int PAGE_LOAD_TIMEOUT = 20;
	public static int IMPLICIT_WAIT = 20;
	public static String TEST_SHEET_PATH = "C:\\Personal Documents\\Ajay\\AjayEclipseWS\\FacebookSignUp\\src\\main\\java\\com\\facebooksignup\\qa\\testdata\\FacebookSignUpTestData.xlsx";
	public static Workbook book;
	public static Sheet sheet;
	public static Object[][] getTestData(String sheetName){
		System.out.println("Inside data laod method");
		FileInputStream file = null;
		try {
			file = new FileInputStream(TEST_SHEET_PATH);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(file);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		//System.out.println("Sheet loaded");
		//System.out.println(sheet.getLastRowNum()+"___"+sheet.getRow(0).getLastCellNum());
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0; i<sheet.getLastRowNum(); i++) {
			for(int k=0; k<sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i+1).getCell(k).toString();
			}
			}
		System.out.println("returning data");
		return data;
	}
	
	public static String currentDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
		String currDate = formatter.format(date);
		return currDate;
	}
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(srcFile, new File(currentDir+"Screenshots"+currentDate()+"_"+System.currentTimeMillis()+".png"));
	}
	
}
