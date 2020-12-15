package testBase;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	
	private final Map<DriverTypes, Supplier<WebDriver>>  driverMap= new HashMap();
	
	//chrome driver supplier
	private final Supplier<WebDriver> chromeDriverSupplier= () ->{
		WebDriverManager.chromedriver().setup();
		return new ChromeDriver();
	};
	
	//firefox driver supplier
	private final Supplier<WebDriver> firefoxDriverSupplier = () ->{
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver();
	};
	
	//Edge driver supplier
	private final Supplier<WebDriver> edgeDriverSupplier = () -> {
		WebDriverManager.edgedriver().setup();
		return new EdgeDriver();
	};
	
	
	//add all drivers in to map
	 {
		driverMap.put(DriverTypes.CHROME, chromeDriverSupplier);
		driverMap.put(DriverTypes.FIREFOX, firefoxDriverSupplier);
		driverMap.put(DriverTypes.EDGE, edgeDriverSupplier);
	 }
	
	//return a new driver from the map
	public final WebDriver getDriver(DriverTypes type){
		return driverMap.get(type).get();
	}
	
}
