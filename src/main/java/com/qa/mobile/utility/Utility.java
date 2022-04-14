package com.qa.mobile.utility;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;
import io.appium.java_client.TouchAction;
import static io.appium.java_client.touch.TapOptions.tapOptions;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import static io.appium.java_client.touch.offset.ElementOption.element;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import static java.time.Duration.ofSeconds;

public class Utility {


	private  final Logger logger = Logger.getLogger(this.getClass());
	private AndroidDriver<AndroidElement> driver=null;
	private AppiumDriverLocalService service;
	private AppiumServiceBuilder builder;

	public  AndroidDriver<AndroidElement> getDriver(){
		return driver;
	}

	public void startServer() {
		CommandLine cmd = new CommandLine("C:\\Program Files\\nodejs\\node.exe");
		cmd.addArgument("C:\\Users\\288568\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js");
		cmd.addArgument("--address");
		cmd.addArgument("127.0.0.1");
		cmd.addArgument("--port");
		cmd.addArgument("4723");

		DefaultExecuteResultHandler handler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		try {
			executor.execute(cmd, handler);
			Thread.sleep(10000);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setDesiredCapabilities() throws MalformedURLException {

		/*
		 * Using AppiumDriverLocalService
		 */

		//Set Capabilities
		File f=new File("APK File");
		File fs=	new File(f,"ApiDemos-debug.apk");

		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator");
			cap.setCapability("unicodeKeyboard", "true");                                     
			cap.setCapability("resetKeyboard", "true");
			cap.setCapability("noReset", "false");
			cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());

			//Build the Appium service
			builder = new AppiumServiceBuilder();
			builder.withIPAddress("127.0.0.1");
			builder.usingPort(4723);
			builder.withCapabilities(cap);
			builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
			builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

			//Start the server with the builder
			service = AppiumDriverLocalService.buildService(builder);
			service.start();

			URL url=	new URL("http://localhost:4723/wd/hub");

			driver = new AndroidDriver<>(url, cap);
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		}
		catch (Exception exp) {
			System.out.println("Cause is: "+exp.getCause());
			System.out.println("Message is : "+ exp.getMessage());	
		}

		/*
		 * Both are working for startAppium server	(Using Appium.js with Node.exe)
		 */


		/**
		File f=new File("APK File");
		File fs=	new File(f,"ApiDemos-debug.apk");

		try {
			DesiredCapabilities dc= new DesiredCapabilities();

			//  dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			//	dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");

			dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
			dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator");
			dc.setCapability("unicodeKeyboard", "true");                                     
			dc.setCapability("resetKeyboard", "true");
			//			dc.setCapability("appPackage","com.flipkart.android");
			//			dc.setCapability("appActivity","com.flipkart.android.SplashActivity");

			dc.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
			//dc.setCapability(MobileCapabilityType.lo, false);

			startServer();

			URL url=	new URL("http://localhost:4723/wd/hub");

			driver =new AndroidDriver<>(url, dc);
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

		}
		catch(Exception exp){
			System.out.println("Cause is: "+exp.getCause());
			System.out.println("Message is : "+ exp.getMessage());	
		} 

		 **/
	}


	public void stopServer() {
		/*    Using AppiumDriverLocalService
		 */
		service.stop();

		/* 	It will be work for cmd  (Using Appium.js with Node.exe)
		 */

		/***
	Runtime runtime=	Runtime.getRuntime();
	try {
	    runtime.exec("taskkill /F /IM node.exe");
	} catch (IOException e) {
	    e.printStackTrace();
	} */

	}


	public void scrollByElement(String visibleText) {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))."
				+ "scrollIntoView(new UiSelector().textContains(\""+visibleText+"\"))");

	}


	public void tapOnElement(WebElement we) {
		TouchAction touch= new TouchAction(driver);
		touch.tap(tapOptions().withElement(element(we))).perform();

	}
	public void longPress(WebElement we, int durationInSecond) {
		TouchAction touch= new TouchAction(driver);
		touch.longPress(longPressOptions().withElement(element(we)).withDuration(ofSeconds(durationInSecond))).release().perform();
	}


	public void dragAndDrop(WebElement source, WebElement dest  ) {
		TouchAction touch= new TouchAction(driver);
		touch.longPress(longPressOptions().withElement(element(source))).moveTo(element(dest)).release().perform();

	}

	public void scroll() {
		Dimension size=	driver.manage().window().getSize();
		int x=size.getWidth() / 2;
		int startY=(int) (size.getHeight() /2 *0.60);
		int endY=(int) (size.getHeight() /2 *0.10);	



	}

	/**
	 * method to hide keyboard
	 */
	public void hideKeyboard() {
		
		try {
		driver.hideKeyboard();
		}
		catch(Exception e) {
			logger.error(e.getCause());
		}
	}



	public void holdOn(int timeOutInSeconds){
		try {
			long time=(long)1000*timeOutInSeconds;
			Thread.sleep(time);
		} catch (Exception e) {	
			logger.debug( "InterruptedException occured", e);
		}
	}

	public void click(WebElement element)  {
		try{
			String elementText=	getText(element);
			element.click();
			logger.info("Clicked on "+elementText);
			holdOn(2);

		}catch(ElementNotVisibleException e){
			logger.info("ElementNotVisibleException occured try again to click"+e);

		}
	}

	public void setTextBoxValue(WebElement element, String fieldName) {
		try {
		click(element);
		element.sendKeys(fieldName);
		//BaseTestMethod.test.log(Status.PASS, fieldName+ " is enter in textbox");
		}
		catch (Exception e) {
			logger.info(e.getCause());
		}

	}

	public String getText(WebElement element){

		return element.getText();
	}


	/**
	 * method to go back by Android Native back click
	 */
	public void back() {
		driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		logger.info("Clicked on back button");
	}



	/**
	 * method to wait for an element to be visible
	 *
	 * @param targetElement element to be visible
	 * @return true if element is visible else throws TimeoutException
	 */
	public boolean waitForVisibility(WebElement element,int timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (TimeoutException e) {
			System.out.println("Element is not visible: " + element);
			throw e;

		}
	}


	/**
	 * method to tap on the screen on provided coordinates
	 *
	 * @param xPosition x coordinate to be tapped
	 * @param yPosition y coordinate to be tapped
	 */
	public void tap(double xPosition, double yPosition) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, Double> tapObject = new HashMap<String, Double>();
		tapObject.put("startX", xPosition);
		tapObject.put("startY", yPosition);
		js.executeScript("mobile: tap", tapObject);
	}


	public  String takeScreenShotMobile(String methodName) throws IOException {
		String dateAndTimeFormat=new SimpleDateFormat(" yyyy-MM-dd hhmmss").format(new Date());
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destFile="ScreenShot//"+methodName+dateAndTimeFormat+".png";
		//String timeStamp=SystemUtil.getObject().getTimeStamp(Constants.DATE_FORMAT_PATTERN);
		//		File destFile=new File(System.getProperty(USER_DIR)+File.separator+"test-output"+ File.separator+methodName+"_"+timeStamp+".png");
		File strFile =new File(destFile);
		FileUtils.copyFile(scrFile, strFile);
		// To add it in the extent report 
		Reporter.log("<a href='"+ strFile.getAbsolutePath() + "'> <img src='"+ strFile.getAbsolutePath() + "' height='100' width='100'/> </a>",true);
		BaseClass.test.log(Status.PASS, "<a href='"+ strFile.getAbsolutePath() + "'> <img src='"+ strFile.getAbsolutePath() + "' height='100' width='100'/> </a>");

		return  destFile;
	}

}
