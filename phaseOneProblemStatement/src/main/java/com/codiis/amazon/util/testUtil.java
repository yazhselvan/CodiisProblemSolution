package com.codiis.amazon.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.codiis.amazon.testBase.testBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class testUtil extends testBase {

	public static int PAGE_LOAD_TIMEOUT = 20;
	public static int IMPLICIT_WAIT = 10;
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	
	static Workbook book;
	static Sheet sheet;

	public static String TESTDATA_SHEET_PATH = "D:\\Interview\\CODIIS_ProblemStatement\\phaseOneProblemStatement\\TestData.xlsx";

	public static Object[][] getTestData(String sheetname) {
		FileInputStream file = null;

		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetname);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
			}
		}
		return data;
	}
	
	public static void setExtent(String TestSetName) {
		  String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		  extent = new ExtentReports(System.getProperty("user.dir")+
				  "/ExtentReports/"+TestSetName+"/AmazonApplicationTest_" + TestSetName + 
				  "_" + dateName +".html", true);	  
	  }
	
	  public static void endReport() {
		  extent.flush();
		  extent.close();
	  }
	  
	  public static String getScreenshot(WebDriver driver, String ScreenshotName) throws IOException {
		  String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		  TakesScreenshot ts = (TakesScreenshot)driver;
		  File source = ts.getScreenshotAs(OutputType.FILE);
		  String destination = System.getProperty("user.dir") +
				  "/FailedTestsScreenshots/" + ScreenshotName + dateName + ".png";
		  File FinalDestination = new File(destination);
		  FileUtils.copyFile(source, FinalDestination);
		  return destination;
	  }
	
		public static void TestClosureReport(ITestResult result) throws IOException {
			
			 if(result.getStatus()==ITestResult.FAILURE) {
				  extentTest.log(LogStatus.FAIL, "Test Case Failed is " + result.getMethod().getMethodName());
				  extentTest.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
				  
				  String ScreenshotPath = getScreenshot(driver,result.getMethod().getMethodName());
				  extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(ScreenshotPath));
			  }
			  else if (result.getStatus()==ITestResult.SKIP) {
				  extentTest.log(LogStatus.SKIP, "Test Case Skipped is " + result.getMethod().getMethodName());
				  
			  } 
			  else if (result.getStatus()==ITestResult.SUCCESS) {
				  extentTest.log(LogStatus.PASS, "Test Case Passed is " + result.getMethod().getMethodName());
			  }
			  extent.endTest(extentTest);
		}
}
