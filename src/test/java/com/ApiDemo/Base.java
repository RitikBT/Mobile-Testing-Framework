package com.ApiDemo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.mobile.utility.Utility;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Base {
	
	
	protected AndroidDriver<AndroidElement> driver ;
	protected Utility utill;
	
	@BeforeMethod
	public AndroidDriver<AndroidElement> setUP() throws MalformedURLException {
		File f = new File("APK file");
		File fs = new File(f,"ApiDemos-debug.apk");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "ritikemulator");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());

		driver = new AndroidDriver<> (new URL ("http://127.0.1.1:4723/wd/hub"),cap);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		utill=	new Utility();
		
		return driver;
	}

	@AfterMethod
	public void closeAndroid() {
		if(driver!=null)
			driver.quit();
	}
}
