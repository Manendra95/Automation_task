
package com.utility.methods;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BaseClass {

	public WebDriver driver;

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeSuite
	public void setExtent() throws IOException {
		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/test-output/ExtentReport/BMC_test_report.html");

		htmlReporter.config().setDocumentTitle("Automation Test Report");
		htmlReporter.config().setReportName("altradar Test Automation Report");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("HostName", "BMC_project");
		extent.setSystemInfo("ProjectName", "BMC_project");
		extent.setSystemInfo("Tester", "Manendra_Jain");
		extent.setSystemInfo("OS", "Win11");
		extent.setSystemInfo("Browser", "Chrome");
	}

	@AfterSuite
	public void endReport() {
		extent.flush();
	}

	@BeforeMethod
	public void start_up_sanity() throws InterruptedException, IOException {
		FileReader reader = new FileReader(
				"D://Manendra_jain//Automation_project//bmc_Automation_tasks//config.properties");
		Properties props = new Properties();
		props.load(reader);
	
		
		// setting up property to suppress the warning
		System.setProperty("webdriver.chrome.silentOutput", "true");

		System.setProperty("webdriver.chrome.driver",
				"D://Manendra_jain//Selenium_jav_file//chromedriver_win32//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(props.getProperty("BaseUrl"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException, InterruptedException {

		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("FAIL ----" + result.getName());
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			test.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			String pathString = TakeScreenShot.screenShot(driver, result.getName());
			test.addScreenCaptureFromPath(pathString);

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			System.out.println("PASS ----" + result.getName());
			test.log(Status.PASS, "Pass Test case is: " + result.getName());

		} else if (result.getStatus() == ITestResult.SKIP) {
			System.out.println("SKIP ----" + result.getStatus());
			test.log(Status.SKIP, "Pass Test case is: " + result.getName());
		}
		Thread.sleep(15000);
		driver.close();
	}
}
