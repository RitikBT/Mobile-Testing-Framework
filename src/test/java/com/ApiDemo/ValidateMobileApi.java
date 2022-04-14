package com.ApiDemo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ValidateMobileApi extends Base {




	@Test
	public void test001() throws InterruptedException, MalformedURLException {
	
		driver.findElement(By.xpath("//*[@content-desc='Views']"));
		utill.tapOnElement(driver.findElement(By.xpath("//*[@content-desc='Views']")));

		utill.scrollByElement("Popup Menu");
		driver.findElement(By.xpath("//*[@text='Popup Menu']")).click();
		//		driver.findElementByAndroidUIAutomator("Attribute(\"value\")");
		tabOnPopUp();

		driver.findElement(By.xpath("//*[@text='Search']")).click();

		//		pdriver.findElementByAndroidUIAutomator("text(\"Search\")").click();

		try {
			String searchtMsg= readToastMsg();
			Assert.assertEquals(searchtMsg, "Clicked popup menu item Search");
		}
		catch (AssertionError e) {
			System.out.println(e.getMessage());
		}

		tabOnPopUp();
		driver.findElementByAndroidUIAutomator("text(\"Add\")").click();

		try {
			String addMsg= readToastMsg();
			Assert.assertEquals(addMsg, "Clicked popup menu item Add");

		}
		catch (AssertionError e) {
			System.out.println(e.getMessage());
		}

		tabOnPopUp();
		driver.findElementByAndroidUIAutomator("text(\"Edit\")").click();;
		try {
			String editMsg= readToastMsg();
			Assert.assertEquals(editMsg, "Clicked popup menu item Edit");

		}
		catch (AssertionError e) {
			System.out.println(e.getMessage());
		}
		Thread.sleep(2000);

		driver.findElement(By.xpath("//android.widget.TextView[@text='Share']")).click();
		try {
			String shareMsg= readToastMsg();
			Assert.assertEquals(shareMsg, "Clicked popup menu item Share");

		}catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();

		}



	}


	@Test
	public void test_002() {

		driver.findElement(By.xpath("//android.widget.TextView[@text='Preference']")).click();
		driver.findElementByAndroidUIAutomator("text(\"5. Preferences from code\")").click();
		driver.findElement(By.xpath("//*[@text='Checkbox preference']")).click();
		try {
			utill.tapOnElement(driver.findElement(By.xpath("//*[@text='OFF']")));
		}catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println(e.getCause());
			utill.tapOnElement(driver.findElement(By.xpath("//*[@text='ON']")));
		}

		driver.findElement(By.xpath("//android.widget.TextView[@text='Edit text preference']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@index='0']")).sendKeys("Hello");
		utill.tapOnElement(driver.findElement(By.xpath("//android.widget.Button[@text='OK']")));
		utill.tapOnElement(driver.findElement(By.xpath("//android.widget.TextView[@text='List preference']")));
		utill.tapOnElement(driver.findElement(By.xpath("//*[@text='Alpha Option 01']")));
		

	}

	public void tabOnPopUp() {
		driver.findElementByAndroidUIAutomator("text(\"Make a Popup!\")").click();
	}


	public String readToastMsg() {
		String toastMsg=	driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
		return toastMsg;
	}

}
