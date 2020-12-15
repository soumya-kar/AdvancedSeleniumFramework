package pageObjects;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;

import testBase.BaseTest;
import testBase.DriverFactory;

public class TaskPageObjects extends BaseTest{
	
	By btn_addTask = By.xpath("//button[text()='Add Task']");
	By field_Search = By.id("search_menu");
	By txt_Search = By.xpath("//*[@id='search_menu']//input[@name='search[keywords]']");
	By btn_Search = By.xpath("//*[@id='search_menu']//input[@type='submit']");
	By dd_SelectProjectForNewTaskCreation = By.id("form_projects_id");
	By dd_taskType = By.id("tasks_tasks_type_id");
	By txt_taskName = By.id("tasks_name");
	By dd_taskStatus = By.id("tasks_tasks_status_id");
	By dd_taskPriority = By.id("tasks_tasks_priority_id");
	By dd_taskLabel = By.id("tasks_tasks_label_id");
	By dd_taskCreatedBy = By.id("tasks_created_by");
	By btn_save = By.xpath("//button[@type='submit' and text()='Save']");


	public void createTask() throws Throwable  {
		Thread.sleep(2000);
		selectDropDownByVisibleText(dd_SelectProjectForNewTaskCreation, "NewTaskProjectDropDown", excel.getDataFromExcelUsingKey("Sheet1", "ProjectToCreateTaskUnder"));
		selectDropDownByVisibleText(dd_taskType, "NewTaskType", excel.getDataFromExcelUsingKey("Sheet1","TaskType"));
		type(txt_taskName, "newTaskName",excel.getDataFromExcelUsingKey("Sheet1", "TaskName"));
		selectDropDownByVisibleText(dd_taskStatus, "NewTaskStatus", excel.getDataFromExcelUsingKey("Sheet1","TaskStatus"));
		selectDropDownByVisibleText(dd_taskPriority, "NewTaskPriority", excel.getDataFromExcelUsingKey("Sheet1","TaskPriority"));
		selectDropDownByVisibleText(dd_taskLabel, "NewTaskLabel", excel.getDataFromExcelUsingKey("Sheet1","Label"));
		click(btn_save, "NewTaskSaveButton");

	}

	public void Search_Verify_TaskCreationOnUI(HashMap<String, String> testData) throws Throwable {

//		moveToElement_custom(DriverFactory.getInstance().getDriver().findElement(field_Search), "TaskSearchOption");
//		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(txt_Search), "TaskSearchBox", testData.get("TaskName"));
//		click_custom(DriverFactory.getInstance().getDriver().findElement(btn_Search), "SearchButton");
//		
//		//table verification
//		assertEqualsString_custom(testData.get("TaskName"), getTaskTableCellValueByColumnName("Name"), "TaskNameInTable");

	}

	private String getTaskTableCellValueByColumnName(String columnName) {
		
		String valueXpath = "//table[starts-with(@id, 'itmes_listing')]/tbody/tr/td[count(//table[starts-with(@id, 'itmes_listing')]/thead/tr/th/div[text()='"+columnName+"']/parent::th/preceding-sibling::th)+1]";
		String value = DriverFactory.getInstance().getDriver().findElement(By.xpath(valueXpath)).getText();
		return value;
	}

}
