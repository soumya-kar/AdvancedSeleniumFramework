package pageObjects;

import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import testBase.BasePage;
import testBase.DriverFactory;

public class HomePageObjects extends BasePage{

	private By sideMinuItems= By.xpath("//ul[@class='page-sidebar-menu']//li//following-sibling::span[@class='title']");
	private By sideSubMenuItems= By.xpath("//ancestor::li//ul[@class='sub-menu']//span[@class='title']");
	By sidebarMenu_Dashboard = By.xpath("//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='Dashboard']");

	//click on menu bar - by passing name of menu
	public void clickOnSideMenu(String menu) {
		String MenuXpath = "//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='"+menu+"']";
		DriverFactory.getInstance().getDriver().findElement(By.xpath(MenuXpath)).click();
	}

	//click on sub menu bar - by passing name of menu
	public void clickOnSideSubMenu(String menu, String submenu) {
		String menuXpath = "//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='"+menu+"']";
		DriverFactory.getInstance().getDriver().findElement(By.xpath(menuXpath)).click();
		String submenuXpath="//ul[@class='page-sidebar-menu']//i/following-sibling::span[text()='"+menu+"']/ancestor::a/following-sibling::ul//span[text()='"+submenu+"']";
		DriverFactory.getInstance().getDriver().findElement(By.xpath(submenuXpath)).click();
	}


	public void clickOnSideMenu(String menu, String submenu){
		getElements(sideMinuItems)
			.stream()
			.filter(mainMenu ->  mainMenu.getText().equals(menu))
			.forEach(ele -> ele.click());
					/*findElements(sideSubMenuItems)
					.stream()
					.filter(chileMenu ->  chileMenu.getText().equals(submenu))
					.forEach(element -> element.click()));*/
		
	}
	
	
}
