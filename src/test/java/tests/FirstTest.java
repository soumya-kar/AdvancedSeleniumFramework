package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;
import pageObjects.TaskPageObjects;
import testBase.BaseTest;
import testBase.MyLogger;

public class FirstTest extends BaseTest{
	
	LoginPageObjects loginPage= new LoginPageObjects();
	HomePageObjects homePage = new HomePageObjects();
	TaskPageObjects taskPage= new TaskPageObjects();
	
	@Test
	public void testcase01(){
		System.out.println("First Test case");
		MyLogger.startTestCase(new Throwable().getStackTrace()[0].getMethodName());
		MyLogger.info("Test 1 starts");
		loginPage.loginToQDPM("administrator@localhost.com", "administrator");
		Assert.assertTrue(true);
		MyLogger.info("Test 1 ends");		
	}
	
	@Test
	public void testcase02() throws Throwable{
		System.out.println("Second Test case");
		MyLogger.startTestCase(new Throwable().getStackTrace()[0].getMethodName());
		MyLogger.info("Test 2 starts");
		loginPage.loginToQDPM("administrator@localhost.com", "administrator");
		Assert.assertTrue(true);
		homePage.clickOnSideSubMenu("Tasks", "Add Task");
		taskPage.createTask();
		
		MyLogger.info("Test 2 ends");
	}
	
	//@Test
	public void testcase03(){
		System.out.println("Third Test case");
		MyLogger.startTestCase(new Throwable().getStackTrace()[0].getMethodName());
		MyLogger.info("Test 3 starts");
	}

}
