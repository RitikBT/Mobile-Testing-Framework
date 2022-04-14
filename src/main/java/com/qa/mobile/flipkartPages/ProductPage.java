package com.qa.mobile.flipkartPages;

import org.openqa.selenium.support.PageFactory;

import com.qa.mobile.utility.Utility;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;





public class ProductPage {

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sort']") private MobileElement sort_Btn;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Price -- High to Low']") private MobileElement highToLow_Btn;
	@AndroidFindBy(xpath = "//*[@text='F-Assured']") private MobileElement f_Assured_Btn;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Filter']") private MobileElement filter_Btn;
	@AndroidFindBy(xpath = "//android.widget.ImageView[@index='0']") private MobileElement fAssured_chk_Bx;
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@index='2']") private MobileElement filterApply_Btn;



	private Utility utill;;
	public ProductPage(Utility utill) {
		this.utill=utill;

		PageFactory.initElements(new AppiumFieldDecorator(utill.getDriver()), this);

	}




	public void sortProduct() {
		sort_Btn.click();
		highToLow_Btn.click();

	}

	public void filterOptions() {
		filter_Btn.click();
		f_Assured_Btn.click();
		fAssured_chk_Bx.click();
		filterApply_Btn.click();


	}



}
