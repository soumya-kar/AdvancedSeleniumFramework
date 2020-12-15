package pageObjects;

import org.openqa.selenium.By;

import testBase.BasePage;
import testBase.BaseTest;


public class LoginPageObjects extends BaseTest{
	
	private By username= By.name("login[email]");
	private By password= By.name("login[password]");
	private By loginButton= By.cssSelector("button[type='submit']");
	
	public void loginToQDPM(String uname, String pwd){
		type(username, "Email Field", uname);
		type(password, "Pasword field", pwd);
		click(loginButton, "Login button");
	}
		

}
