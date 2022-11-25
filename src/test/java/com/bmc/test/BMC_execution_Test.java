package com.bmc.test;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bmc_element.BMC_element;
import com.utility.methods.BaseClass;

public class BMC_execution_Test extends BaseClass {

	String homePageUrl;
	String homePageTitle;

	String homePage_expectedUrl;
	String homePage_expectedTitle;

	String AmozonpageUrl;
	String AmozonpageTitle;

	String AmazonPage_expectedUrl;
	String AmazonPage_expectedTitle;

	String AmazonSignInPageUrl;
	String AmazonSignInPageTitle;

	String AmazonSignInPage_expectedUrl;
	String AmazonSignInPage_expectedTitle;

	@Test(priority = 1)
	public void Smoke_test() throws IOException, InterruptedException {
		FileReader reader = new FileReader(
				"D://Manendra_jain//Automation_project//bmc_Automation_tasks//config.properties");
		Properties props = new Properties();
		props.load(reader);

		FileInputStream fis = new FileInputStream(
				"D:\\Manendra_jain\\Automation_project\\bmc_Automation_tasks\\ExcelRead\\testData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		// Cell A1 = row 0 and column 0. It reads first row as 0 and Column A as 0.
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        //System.out.println(cell);

		System.out.println(sheet.getRow(0).getCell(1));
		System.out.println(sheet.getRow(1).getCell(0));
		System.out.println(sheet.getRow(1).getCell(1));

		String cellval = cell.getStringCellValue();
		 System.out.println(cellval);

		// extent.createTest set the test name
		test = extent.createTest("Smoke_test");
		homePage_expectedUrl = props.getProperty("homePage_expectedUrl");
		homePage_expectedTitle = props.getProperty("homePage_expectedTitle");

		homePageUrl = driver.getCurrentUrl();
		homePageTitle = driver.getTitle();
		// validation for assertion page url and title
		Assert.assertEquals(homePageUrl, homePage_expectedUrl, "Verify Home page Url");
		Assert.assertEquals(homePageTitle, homePage_expectedTitle, "Verify Home page Title");

		BMC_element bmc_Elementdata = new BMC_element(driver);
		Assert.assertTrue(bmc_Elementdata.isEnabledSearchbox(), "verify search input box isEnbled");

		// get the search input text box
		bmc_Elementdata.EntersearchText(props.getProperty("EntersearchText"));

		List<WebElement> myList = bmc_Elementdata.getsearchlistname;
		for (WebElement listname : myList) {
			String searchresult = listname.getText();
			System.out.println("Search Result are-- " + searchresult);
		}
		// navigate the amazon link
		driver.get(props.getProperty("AmozonUrl"));

		AmazonPage_expectedUrl = props.getProperty("AmazonPage_expectedUrl");
		AmazonPage_expectedTitle = props.getProperty("AmazonPage_expectedTitle");

		AmozonpageUrl = driver.getCurrentUrl();
		AmozonpageTitle = driver.getTitle();

		// validation for assertion page url and title
		Assert.assertEquals(AmozonpageUrl, AmazonPage_expectedUrl, "Verify Amazon page Url");
		Assert.assertEquals(AmozonpageTitle, AmazonPage_expectedTitle, "Verify Amazon page Title");

		Thread.sleep(30000);
		bmc_Elementdata.HeaderSignInBtn_Click();

		AmazonSignInPageUrl = driver.getCurrentUrl();
		AmazonSignInPageTitle = driver.getTitle();

		AmazonSignInPage_expectedUrl = props.getProperty("AmazonSignInPage_expectedUrl");
		AmazonSignInPage_expectedTitle = props.getProperty("AmazonSignInPage_expectedTitle");

		// validation for assertion page url and title
		Assert.assertEquals(AmazonSignInPageUrl, AmazonSignInPage_expectedUrl, "Verify AmazonSignIn page Url");
		Assert.assertEquals(AmazonSignInPageTitle, AmazonSignInPage_expectedTitle, "Verify AmazonSignIn Page Title");

		Thread.sleep(10000);
		Assert.assertTrue(bmc_Elementdata.isDisplayedAmazonSign_Email(), "verify email input box is displayed");

		bmc_Elementdata.EnterAmazonSign_Email(props.getProperty("AmazonSign_Email"));
		bmc_Elementdata.isDisplayedAmazonSign_EmailContinue();
		bmc_Elementdata.ContinueAmazonContinueBtn();

		Thread.sleep(10000);
		Assert.assertTrue(bmc_Elementdata.isDisplayedAmazonSign_Pwd(), "verify email input box is displayed");
		bmc_Elementdata.EnterAmazonSign_Pwd(props.getProperty("EnterAmazonSign_Pwd"));
		bmc_Elementdata.isDisplayedContinueAmazonSignBtn();
		bmc_Elementdata.ContinueAmazonSignBtn();
		Thread.sleep(5000);

		// Login succesfully then click on all button
		bmc_Elementdata.clickAllTabmenu();
		Thread.sleep(10000);

		bmc_Elementdata.ScrollmenuList();
		// click on one menu tab
		bmc_Elementdata.clickOneTabmenu();
		Thread.sleep(5000);
		bmc_Elementdata.ScrollsubmenuList();
		// click on one submenu tab
		bmc_Elementdata.clicksubTabmenu();
		Thread.sleep(5000);
		// search for dell computers in input box
		bmc_Elementdata.EnterSeachAmazon(props.getProperty("EnterSearchInputbox"));
		Thread.sleep(10000);
		bmc_Elementdata.AmazonClickOnSearchbtn();
		Thread.sleep(10000);
		bmc_Elementdata.ScrollonFilter();
		// Apply the filter price 3000-5000
		bmc_Elementdata.filterminValue(props.getProperty("filterEnterminValue"));
		bmc_Elementdata.filtermaxValue(props.getProperty("filtermaxValue"));
		Thread.sleep(10000);
		bmc_Elementdata.AmazonClickfilterGobtn();
		Thread.sleep(10000);
//		11.	print all the products on the first 2 pages whose rating is 5 out of 5
		bmc_Elementdata.ScrollonFilter();
		bmc_Elementdata.AmazonClickfilterRating();
	
//		add the first product whose rating is 5 out of 5 to the wish list. (Create a new wish list)

		List<WebElement> productList = bmc_Elementdata.getproductlistname;
		for (WebElement listname : productList) {
			String productListresult = listname.getText();
			System.out.println("productList Result are-- " + productListresult);
		}
//		Validate the product is added to the wish list
		bmc_Elementdata.Amazongetproductname();
//		bmc_Elementdata.ScrollonProductDetails();
//		Thread.sleep(10000);

	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));

		bmc_Elementdata.wishlistAddproduct();
		Thread.sleep(10000);
	    
		
		
	}
}
