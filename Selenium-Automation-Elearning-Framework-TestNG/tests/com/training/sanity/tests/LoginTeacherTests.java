package com.training.sanity.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.training.pom.LoginPOM;

public class LoginTeacherTests extends LoginTests {
		
	@Test
	public void validLoginTest() {
		/*
		 * try { super.setUpBeforeClass(); super.setUp();
		 * 
		 * } catch(Exception e){ System.out.println(e); }
		 */
		loginPOM.sendUserName("pramita");
		loginPOM.sendPassword("pramita");
		loginPOM.clickLoginBtn();
		String actualTitle=loginPOM.pageTitle();
		Assert.assertEquals(actualTitle, "e Learning - My education - My courses");
		screenShot.captureScreenShot("login1");

	}
}
