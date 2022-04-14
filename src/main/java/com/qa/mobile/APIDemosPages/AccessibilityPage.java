package com.qa.mobile.APIDemosPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.mobile.utility.Utility;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AccessibilityPage {


	Utility utill;
	public AccessibilityPage(Utility utill) {
		this.utill=utill;

		PageFactory.initElements(new AppiumFieldDecorator(utill.getDriver()), this);
	}


	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Accessibility Node Provider']") private WebElement clickOnAccessNodeProvider;
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Accessibility Node Querying']") private WebElement clickOnAccessNodeQuering;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Accessibility Service']") private WebElement clickOnAccessService;
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Custom View']") private WebElement clickOnCustView;


}
