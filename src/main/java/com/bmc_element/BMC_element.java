package com.bmc_element;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BMC_element {
	WebDriver driver;

	@FindBy(xpath = "//form//input[@class='gLFyf']")
	public WebElement search_inputbox;

	@FindBy(xpath = "//div[@class='OBMEnb']//li")
	public List<WebElement> getsearchlistname;

	@FindBy(xpath = "//div[@id='nav-tools']//span[text()='Hello, sign in']")
	public WebElement HeaderSignInBtn;

	@FindBy(xpath = "//input[@id='ap_email']")
	public WebElement AmazonSign_Email;

	@FindBy(xpath = "//input[@id='continue']")
	public WebElement AmazonSign_EmailContinue;

	@FindBy(xpath = "//input[@id='ap_password']")
	public WebElement AmazonSign_Pwd;

	@FindBy(xpath = "//input[@id='signInSubmit']")
	public WebElement AmazonSign_btn;

	@FindBy(xpath = "//a[@id='nav-hamburger-menu']")
	public WebElement clickAllTab;

	@FindBy(xpath = "//div[@id='hmenu-content']//li/a[text()=' All Computers & Accessories '] ")
	public WebElement clickComputetab;

	@FindBy(xpath = "//div[@id='hmenu-content']//div[text()='Mobiles, Computers']")
	public WebElement clickAllcomputers;

	@FindBy(xpath = "//div//input[@id='twotabsearchtextbox']")
	public WebElement searchInputbox;

	@FindBy(xpath = "//div//input[@id='nav-search-submit-button']")
	public WebElement ClickOnSearchbtn;
	
	@FindBy(xpath = "//div//input[@id='low-price']")
	public WebElement Enterminvalue;

	@FindBy(xpath = "//div//input[@id='high-price']")
	public WebElement Entermaxvalue;
	
	@FindBy(xpath = "//form//input[@class='a-button-input']")
	public WebElement filterGobtn;

	@FindBy(xpath = "//div[@id='reviewsRefinements']//li[@id='p_72/1318476031']")
	public WebElement filterRating;

	@FindBy(xpath = "//div[@class='a-section a-spacing-small a-spacing-top-small']//span[@class='a-size-medium a-color-base a-text-normal']")
	public List<WebElement> getproductlistname;

	@FindBy(xpath = "//div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/div/div/div/div[2]/div/div/div[1]/h2/a/span")
	public WebElement getproductname;

	@FindBy(xpath = "//input[@id='add-to-wishlist-button-submit']")
	public WebElement clickAddwishlist;
	
	public BMC_element(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// isEnabledSearchbox on empty_search_btn
	public boolean isEnabledSearchbox() {
		return search_inputbox.isEnabled();
	}

	public void EntersearchText(String searchText) {
		search_inputbox.sendKeys(searchText);
	}

	// HeaderSignIn Btn click
	public void HeaderSignInBtn_Click() {
		HeaderSignInBtn.click();
	}

	// Enter mobile number/EMail
	public boolean isDisplayedAmazonSign_Email() {
		return AmazonSign_Email.isDisplayed();
	}

	public void EnterAmazonSign_Email(String AmazonEmail) {
		AmazonSign_Email.sendKeys(AmazonEmail);
	}

	public boolean isDisplayedAmazonSign_EmailContinue() {
		return AmazonSign_EmailContinue.isDisplayed();
	}

	public void ContinueAmazonContinueBtn() {
		AmazonSign_EmailContinue.click();
	}

	public boolean isDisplayedAmazonSign_Pwd() {
		return AmazonSign_Pwd.isDisplayed();
	}

	public void EnterAmazonSign_Pwd(String AmazonPwd) {
		AmazonSign_Pwd.sendKeys(AmazonPwd);
	}

	public boolean isDisplayedContinueAmazonSignBtn() {
		return AmazonSign_btn.isDisplayed();
	}

	public void ContinueAmazonSignBtn() {
		AmazonSign_btn.click();
	}

	public void clickAllTabmenu() {
		clickAllTab.click();
	}

	public void clickOneTabmenu() {
		clickAllcomputers.click();
	}

	public void clicksubTabmenu() {
		clickComputetab.click();
	}

	public void ScrollmenuList() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", clickAllcomputers);
	}

	public void ScrollsubmenuList() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", clickComputetab);
	}

	public void EnterSeachAmazon(String EntersearchInputbox) {
		searchInputbox.sendKeys(EntersearchInputbox);
	}
	
	public void AmazonClickOnSearchbtn() {
		ClickOnSearchbtn.click();
	}
	public void ScrollonFilter() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("window.scrollTo(50, document.body.scrollHeight)");
	}
	
	public void filterminValue(String minValueInputbox) {
		Enterminvalue.sendKeys(minValueInputbox);
	}

	public void filtermaxValue(String maxValueInputbox) {
		Entermaxvalue.sendKeys(maxValueInputbox);
	}

	public void AmazonClickfilterGobtn() {
		filterGobtn.click();
	}
	public void AmazonClickfilterRating() {
		filterRating.click();
	}
	public void Amazongetproductname() {
	getproductname.click();
	}
	public void ScrollonProductDetails() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("window.scrollTo(50, document.body.scrollHeight)");
	}
	public void wishlistAddproduct() {
		clickAddwishlist.click();
	}	
	
}
