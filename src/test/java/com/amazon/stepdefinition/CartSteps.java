package com.amazon.stepdefinition;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CartSteps {
	public static WebDriver driver;
	private static WebDriverWait waitDriver;
	public final static int TIMEOUT = 30;
	public final static int PAGE_LOAD_TIMEOUT = 50;
	public static String productName;
	public static String productPrice;
	
	@Given("Open Browser and user on the \"([^\"]*)\" page")
	public void open_Browser_and_user_on_the_page(String url) {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		waitDriver = new WebDriverWait(driver, TIMEOUT);
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts()
				.pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.get(url);
	}
	@When ("Title should be same")
	public void Title_should_be_same()
	{
		String title = driver.getTitle();
		Assert.assertEquals("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more", title);
	}

	@Then("user click Sign in button")
	public void user_click_Sign_in_button() {
		driver.findElement(By.xpath("//a[@id='nav-link-accountList']")).click();
	}

	@Then("user enter the \"([^\"]*)\" as mailid")
	public void user_enter_the_as_mailid(String username) {
		driver.findElement(By.xpath("//input[@id='ap_email']")).clear();
		driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys(username);
	}

	@Then("user click continue button")
	public void user_click_continue_button() {
		driver.findElement(By.xpath("//input[@id='continue']")).click();
	}

	@Then("user should be see error message \"([^\"]*)\"")
	public void user_should_be_see_error_message(String expected) {
		String actual = driver.findElement(By.xpath("//div[@id='auth-error-message-box']//li//span")).getText();
		Assert.assertEquals(expected,actual);
	}

	@Then("user enter the \"([^\"]*)\" as password")
	public void user_enter_the_as_password(String password) {
		driver.findElement(By.xpath("//input[@id='ap_password']")).clear();
		driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys(password);
	}

	@Then("user click signin button")
	public void user_click_signin_button() {
		driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();
	}

	@Then("user should be see homepage")
	public void user_should_be_see_homepage() {
		String name = driver.findElement(By.xpath("//a[@id='nav-link-accountList']//span[@class='nav-line-1']")).getText();
		String[] customernamesplit = name.split("\\,");
		Assert.assertFalse(customernamesplit[1].equals(" Sign in"));
	}

	@Then("user enter \"([^\"]*)\" in Search page")
	public void user_enter_in_Search_page(String productName) {
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).clear();
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(productName);
	}

	@Then("user click Search button")
	public void user_click_Search_button() {
		driver.findElement(By.xpath("//input[@value='Go']")).click();
	}

	@Then("user Collect Product Name and Price")
	public void user_Collect_Product_Name_and_Price() {
		productName = driver.findElement(By.xpath("//ul[@id='s-results-list-atf']//h2")).getText();
		String currency = driver.findElement(By.xpath("//ul[@id='s-results-list-atf']//a/span//sup[1]")).getText();
		String whole = driver.findElement(By.xpath("//ul[@id='s-results-list-atf']//a/span//span[@class='sx-price-whole']")).getText();
		String fractional = driver.findElement(By.xpath("//ul[@id='s-results-list-atf']//a/span//sup[2]")).getText();
		productPrice = currency+whole+"."+fractional;
	}

	@Then("user select the product")
	public void user_select_the_product() {
		driver.findElement(By.xpath("//ul[@id='s-results-list-atf']//h2")).click();
	}

	@Then("user click Add to Cart Button")
	public void user_click_Add_to_Cart_Button() {
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
	}
	@Then("user check added product in product page \"([^\"]*)\"")
	public void user_check_added_product_in_product_page(String expected) {
		String actual = driver.findElement(By.xpath("//div[@id='huc-v2-order-row-with-divider']//h1")).getText();
		Assert.assertEquals(expected,actual);
	}

	@Then("user click Cart button")
	public void user_click_Cart_button() {
		driver.findElement(By.xpath("//a[@id='nav-cart']")).click();
	}	

	@Then("user check added product in basket page")
	public void user_check_added_product_in_basket_page() {
		String xpath ="//div[@class='sc-list-body sc-java-remote-feature']/div";
		List<WebElement> products = driver.findElements(By.xpath(xpath));
		for(WebElement product:products)
		{
		//	String xpath1 =xpath+"//a/span";
			String pname = driver.findElement(By.xpath(xpath+"//a/span")).getText();
			if(pname.equalsIgnoreCase(productName))
			{
				Assert.assertTrue("Product Present in Basket",true);
				String pprice = driver.findElement(By.xpath(xpath+"//p/span")).getText();
				Assert.assertEquals(productPrice, pprice);
			}
		}
	}

	@Then("user logout")
	public void user_logout() {
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.id("nav-link-accountList"));
		Action moveTo =  action.moveToElement(element).build();
		moveTo.perform();
		driver.findElement(By.xpath("//*[@id='nav-item-signout-sa']")).click();
		
	}

	
	@And("Close Browser")
	public void Close_Browser()
	{
		driver.quit();
	}

}
