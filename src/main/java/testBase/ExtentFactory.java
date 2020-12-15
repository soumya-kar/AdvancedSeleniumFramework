package testBase;

import com.aventstack.extentreports.ExtentTest;

public class ExtentFactory {
	//Singleton design Pattern
		//private constructor so that no one else can create object of this class
		private ExtentFactory() {
			
		}
		
		private static ExtentFactory instance  = new ExtentFactory();
		
		public static ExtentFactory getInstance() {
			return instance;
		}
		
		
		//factory design pattern --> define separate factory methods for creating objects and create objects by calling that methods
		ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
		
		public ExtentTest getExtent() {
			return extentTest.get();
		}
		
		public void setExtent(ExtentTest extentTestObject) {
			extentTest.set(extentTestObject);
		}
		
		public void removeExtentObject() {
			extentTest.remove();
		}
}
