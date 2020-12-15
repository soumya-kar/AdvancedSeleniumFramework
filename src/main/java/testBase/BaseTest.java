package testBase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import reusableComponents.ExcelUtility;
import reusableComponents.PropertyOperation;

public class BaseTest extends BasePage{
	
	protected BrowserFactory bf=new BrowserFactory();
	protected ExcelUtility excel= new ExcelUtility(System.getProperty("user.dir") + "\\Testdata\\TestdataFile.xlsx");
	
	@BeforeMethod
	public void launchApplication(){
		DriverFactory.getInstance().setDriver(bf.getDriver(DriverTypes.CHROME));
		WebDriver driver = DriverFactory.getInstance().getDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(PropertyOperation.getPropertyValueByKey("url"));
		
	}
	
	@AfterMethod
	public void tearDown(){
		DriverFactory.getInstance().closeDriver();
	}

}
