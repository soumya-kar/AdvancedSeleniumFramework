package testBase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import reusableComponents.PropertyOperation;

public class ExtentReportSetup {
	
	static ExtentReports extent;

	public static ExtentReports setupExtentReport() throws Exception {
		DateFormat sdf = new SimpleDateFormat("MM_dd_yyyy_HH_mm_SS");
		Date date = new Date();
		String actualDate = sdf.format(date);
		
		String reportPath = System.getProperty("user.dir")+
				"/Reports/ExecutionReport_"+actualDate+".html";
		
		ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath);
		
		 extent = new ExtentReports();
		 extent.attachReporter(sparkReport);
		
		sparkReport.config().setDocumentTitle("DocumentTitle");
		sparkReport.config().setTheme(Theme.DARK);
		sparkReport.config().setReportName("ReportName");
		
		extent.setSystemInfo("Executed on Environment: ", PropertyOperation.getPropertyValueByKey("url"));
		extent.setSystemInfo("Executed on Browser: ", "chrome");
		extent.setSystemInfo("Executed on OS: ", System.getProperty("os.name"));
		extent.setSystemInfo("Executed by User: ", System.getProperty("user.name"));

		return extent;
	}

}
