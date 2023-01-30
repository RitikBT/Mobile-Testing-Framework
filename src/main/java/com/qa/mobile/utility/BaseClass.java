package com.qa.mobile.utility;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;



import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class BaseClass {

	protected Utility utill = new Utility();
	private  final Logger logger = Logger.getLogger(this.getClass());
	private  ExtentHtmlReporter htmlReporter;
	private  ExtentReports extent;
	public static  ExtentTest test;


	@BeforeSuite
	public void setReport() {
		htmlReporter = new ExtentHtmlReporter("ExtentReport/STMExtentReport.html");
		// Create an object of Extent Reports
		extent = new ExtentReports();  
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Conneqt Bussiness Solution Lim.");
		extent.setSystemInfo("Environment", "Local");
		extent.setSystemInfo("OS", "Mobile Testing");
		extent.setSystemInfo("User Name", "Ritik Yadav");
		
		htmlReporter.config().setDocumentTitle("Automation Report"); 
		// Name of the report
		htmlReporter.config().setReportName("Functional Report "); 
		// Dark Theme
		htmlReporter.config().setTheme(Theme.STANDARD);	

		logger.info("@BeforeSuite Method has been completed");
	}

	@BeforeMethod
	public void setUp(Method methodName) throws MalformedURLException  {
		utill.setDesiredCapabilities("real");
		test=extent.createTest(methodName.getName());

		logger.info("App launched");
	}



	@AfterMethod
	public void closeAndroid(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE)
		{
			//MarkupHelper is used to display the output in different colorsvbb
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

			//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
			//We do pass the path captured by this method in to the extent reports using "logger.addScreenCapture" method. 

			//	String Scrnshot=TakeScreenshot.captuerScreenshot(driver,"TestCaseFailed");
			utill.holdOn(5);


			try {
				test.fail("<b><font color=red>"+"Screenshot of failure "+ "</font></b>");
				String screenshotPath = utill.takeScreenShotMobile(result.getName());	
				logger.info("Screenshot taken for failed "+result.getName()+" testcases");
			}catch (IOException e) {
				test.fail("Test Failed, can't attach screenshot");
			}

		}
		else if(result.getStatus() == ITestResult.SKIP){
			//logger.info(Status.SKIP, "Test Case Skipped is "+result.getName());
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE)); 
			logger.info("TestCase skipped "+result.getName());
		} 
		else if(result.getStatus() == ITestResult.SUCCESS)
		{

			test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
			logger.info("TestCase passed "+result.getName());
		}


		if(utill.getDriver()!=null)
			utill.getDriver().quit();

		logger.info("driver closed");
	}

	@AfterTest
	public void closeServer() {
		utill.stopServer();
		logger.info("Appium Server Closed");
	}
	
	@AfterSuite
	public void closeExtentReport() {
		extent.flush();
	  
	}






}
