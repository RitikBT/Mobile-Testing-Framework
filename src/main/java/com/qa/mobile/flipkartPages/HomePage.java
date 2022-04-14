package com.qa.mobile.flipkartPages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

import com.qa.mobile.utility.Utility;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {


	@AndroidFindBy(xpath = "//*[@text='Search for Products, Brands and More']") private MobileElement searchBar;
	@AndroidFindBy(xpath = "//*[@text='shoes']") private MobileElement searchText;
	@AndroidFindBy(xpath = "//android.widget.Button[@text='NOT NOW']") private MobileElement deniedLocation;
	

	Utility utill;

	public  HomePage(Utility utill) {
		this.utill=utill;

		PageFactory.initElements(new AppiumFieldDecorator(utill.getDriver()), this);

	}


	public void searchItem(String itemName) {

		searchBar.click();
		try {
		searchBar.sendKeys(itemName);
		}
		catch (NoSuchElementException e) {
			searchBar.clear();
			searchBar.sendKeys(itemName);
		}
		
		String actText=searchText.getText();
		
		if(searchText.getText().equalsIgnoreCase(itemName)) {
			searchText.click();
		}
		else {
			System.out.println("Product is not available...");
		}

	}

	
	public void deniedAllowLocation() {
		deniedLocation.click();
	}


}
