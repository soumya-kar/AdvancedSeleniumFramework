package testBase;

import java.util.List;
import com.aventstack.extentreports.Status;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public abstract class BasePage {

	protected WebElement getElement(By locator) {
		return DriverFactory.getInstance().getDriver().findElement(locator);
	}

	protected List<WebElement> getElements(By locator) {
		return DriverFactory.getInstance().getDriver().findElements(locator);
	}

	protected void type(By locator, String text) {
		getElement(locator).clear();
		if (text.length() > 0)
			getElement(locator).sendKeys(text);
	}

	protected void click(By locator) {
		getElement(locator).click();
	}

	protected String getTitle() {
		return DriverFactory.getInstance().getDriver().getTitle();
	}

	protected boolean isDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	protected String getElementText(By locator) {
		return getElement(locator).getText();
	}

	// Customized sendkeys method-> To log sendkeys message for every occ.
	public void type(By locator, String fieldName, String valueToBeSent) {
		try {
			getElement(locator).sendKeys(valueToBeSent);
			// log success message in exgent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS,
					fieldName + "==> Entered value as: " + valueToBeSent);
		} catch (Exception e) {
			// log failure in extent
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Value enter in field: " + fieldName + " is failed due to exception: " + e);
		}
	}

	// custom click method to log evey click action in to extent report
	public void click(By locator, String fieldName) {
		try {
			getElement(locator).click();
			// log success message in exgent report
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName + "==> Clicked Successfully! ");
		} catch (Exception e) {
			// log failure in extent
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Unable to click on field: " + fieldName + " due to exception: " + e);
		}
	}

	// clear data from field
	public void clear(By locator, String fieldName) {
		try {
			getElement(locator).clear();
			Thread.sleep(250);
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName + "==> Data Cleared Successfully! ");
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Unable to clear Data on field: " + fieldName + " due to exception: " + e);

		}
	}

	// custom mouseHover
	public void moveToElement(By locator, String fieldName) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getInstance().getDriver();
			executor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
			Actions actions = new Actions(DriverFactory.getInstance().getDriver());
			actions.moveToElement(getElement(locator)).build().perform();
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName + "==> Mouse hovered Successfully! ");
			Thread.sleep(1000);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Unable to hover mouse on field: " + fieldName + " due to exception: " + e);

		}
	}

	// check if element is Present
	public boolean isElementDisplayed(By locator, String fieldName) {
		boolean flag = false;
		try {
			flag = getElement(locator).isDisplayed();
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName + "==> Presence of field is: " + flag);
			return flag;
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Checking for presence of field: " + fieldName + " not tested due to exception: " + e);
			return flag;
		}
	}

	// Select dropdown value value by visibleText
	public void selectDropDownByVisibleText(By locator, String fieldName, String ddVisibleText)
			throws Throwable {
		try {
			Select s = new Select(getElement(locator));
			s.selectByVisibleText(ddVisibleText);
			ExtentFactory.getInstance().getExtent().log(Status.PASS,
					fieldName + "==> Dropdown Value Selected by visible text: " + ddVisibleText);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Dropdown value not selected for field: " + fieldName + "  due to exception: " + e);
		}
	}

	// Select dropdown value value by value
	public void selectDropDownByValue(By locator, String fieldName, String ddValue) throws Throwable {
		try {
			Select s = new Select(getElement(locator));
			s.selectByValue(ddValue);
			ExtentFactory.getInstance().getExtent().log(Status.PASS,
					fieldName + "==> Dropdown Value Selected by visible text: " + ddValue);
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					"Dropdown value not selected for field: " + fieldName + "  due to exception: " + e);
		}
	}

	// String Asserts
	public void assertEqualsString(String expvalue, String actualValue, String locatorName) throws Throwable {
		try {
			if (actualValue.equals(expvalue)) {
				ExtentFactory.getInstance().getExtent().log(Status.PASS, "String Assertion is successful on field "
						+ locatorName + " Expected value was: " + expvalue + " actual value is: " + actualValue);
			} else {
				ExtentFactory.getInstance().getExtent().log(Status.FAIL, "String Assertion FAILED on field "
						+ locatorName + " Expected value was: " + expvalue + " actual value is: " + actualValue);
				Assert.assertTrue(false);
			}
		} catch (Exception e) {
			Assert.assertTrue(false, e.toString());
		}
	}

	// Get text from webelement
	public String getElementText(By locator, String fieldName) {
		String text = "";
		try {
			text = getElement(locator).getText();
			ExtentFactory.getInstance().getExtent().log(Status.PASS, fieldName + "==> Text retried is: " + text);
			return text;
		} catch (Exception e) {
			ExtentFactory.getInstance().getExtent().log(Status.FAIL,
					fieldName + "==> Text not retried due to exception: " + e);

		}
		return text;
	}

}
