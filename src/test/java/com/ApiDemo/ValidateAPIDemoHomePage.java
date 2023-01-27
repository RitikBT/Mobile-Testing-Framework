package com.ApiDemo;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.mobile.APIDemosPages.HomePage;
import com.qa.mobile.utility.BaseClass;

public class ValidateAPIDemoHomePage extends BaseClass {


	private  final Logger logger = Logger.getLogger(this.getClass());

	@Test
	public void validateAllMainModule() {
		HomePage hm=	new HomePage(utill);
		hm.verifyAllElement();
		Assert.assertEquals(true, true);

	}

}
