package testBase;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
	
	//Singleton design
	private DriverFactory(){
		
	}

	private static DriverFactory instance= new DriverFactory();
	
	public static DriverFactory getInstance(){
		return instance;
	}
	
	ThreadLocal<WebDriver>  threadSafeDriver= new ThreadLocal<>();
	
	
	public void setDriver(WebDriver driver){
		threadSafeDriver.set(driver);
	}
	
	public WebDriver getDriver(){
		return threadSafeDriver.get();
	}
	
	public void closeDriver(){
		if(threadSafeDriver.get() != null)
			threadSafeDriver.get().close();
		threadSafeDriver.remove();
	}
}

