package com.qa.mobile.flipkartPages;

import org.openqa.selenium.support.PageFactory;

import com.qa.mobile.utility.Utility;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage {
	
	
	@AndroidFindBy(xpath ="//*[@text='English']") private MobileElement lanuage;
	@AndroidFindBy(id="com.flipkart.android:id/select_btn") private MobileElement continue_btn;
	@AndroidFindBy(id = "com.flipkart.android:id/custom_back_icon") private MobileElement cancel_btn;
	
	
	
	
	private Utility utill;
	
	public LoginPage(Utility utill) {
		this.utill=utill;
		
		PageFactory.initElements(new AppiumFieldDecorator(utill.getDriver()), this);
	
	}

	public void withoutLogin() {
		
		utill.scrollByElement("English");
		lanuage.click();
		continue_btn.click();
		cancel_btn.click();
		
		
	}
	
	

}
