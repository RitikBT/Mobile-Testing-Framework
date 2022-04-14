package com.qa.mobile.APIDemosPages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.mobile.utility.Utility;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {

	Utility utill;

	private  final Logger logger = Logger.getLogger(this.getClass());
	public HomePage(Utility utill) {
		this.utill=utill;
		PageFactory.initElements(new AppiumFieldDecorator(utill.getDriver()), this);
	}


	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Accessibility']") private WebElement accessibilityTab;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Animation']") private WebElement animationTab;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='App']") private WebElement appTab;
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Media']") private WebElement  mediaTab;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Content']") private WebElement contentTab;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Graphics']") private WebElement graphicsTab;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='NFC']") private WebElement nfcTab;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='OS']") private WebElement OSTab;
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Preference']") private WebElement preferenceTab;
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Text']") private WebElement  textTab;
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Views']") private WebElement viewsTab;
	@AndroidFindBy(id = "android:id/text1") private List<WebElement> allModules;


	public void verifyAllElement() {
		for(WebElement module:allModules) {
			String moduleName= 	utill.getText(module);
			utill.click(module);
			utill.back();
		}
	}


	public void clickAccessibility() {

		utill.click(accessibilityTab);

	}

	public void clickOnAnimation() {
		utill.click(animationTab);
	}
	public void clickOnApp() {
		utill.click(appTab);
	}
	public void clickONContent() {
		utill.click(contentTab);
	}
	public void clickOnGraphics() {
		utill.click(graphicsTab);
	}
	public void clickOnMedia() {
		utill.click(mediaTab);
	}
	public void clickOnNFC() {
		utill.click(nfcTab);
	}
	public void clickOnOS() {
		utill.click(OSTab);
	}

	public void clickOnpreference() {
		utill.click(preferenceTab);
	}
	public void clickOnText() {
		utill.click(textTab);
	}
	public void clickOnViews() {
		utill.click(viewsTab);
	}

}
