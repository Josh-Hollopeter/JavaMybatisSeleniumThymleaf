package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void unauthorizedHomePage() {
		driver.get("http://localhost:" + this.port + "/home.html");
		Assertions.assertEquals("Error page", driver.getTitle());
	}
	
	// sometimes manual clicking of nav tab clicking is neccessary to access note and credential tabs
	// test will sometimes fail only for this reason, no reasonable solution found yet


	@Test
	public void validLoginAndNoteCreationTest() {
		WebDriverWait wait = new WebDriverWait (driver, 30);
		driver.get("http://localhost:" + this.port + "/");
		driver.manage().window().maximize();
		JavascriptExecutor jse =(JavascriptExecutor) driver;
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("jim");
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("jim");
		WebElement login = driver.findElement(By.id("login"));
		login.click();
		WebElement notes = driver.findElement(By.xpath("//a[@href='#nav-notes']"));
		notes.sendKeys("dkdkdfdsfsdfsfsdfsdfsfd");
		jse.executeScript("arguments[0].checked=true;",notes);
//		notes.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#nav-notes']"))).click();
		WebElement addNoteButton = driver.findElement(By.xpath("//button[@id='notebutton']"));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("notebutton"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("note-title"))).sendKeys("Yay Selenium");;

		WebElement noteDescription = driver.findElement(By.id("note-description"));
		noteDescription.click();
		noteDescription.sendKeys("Get some lunch");
		WebElement noteSubmit = driver.findElement(By.id("save-note"));
		noteSubmit.click();
		Assertions.assertEquals("Home", driver.getTitle());
		
		}
	// sometimes manual clicking of nav tab clicking is neccessary to access note and credential tabs
	// test will sometimes fail only for this reason, no reasonable solution found yet

	@Test
	public void validLoginAndCredentialCreationTest() {
		WebDriverWait wait = new WebDriverWait (driver, 30);
		driver.get("http://localhost:" + this.port + "/");
		driver.manage().window().maximize();
		JavascriptExecutor jse =(JavascriptExecutor) driver;
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("jim");
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("jim");
		WebElement login = driver.findElement(By.id("login"));
		login.click();
		WebElement credentials = driver.findElement(By.xpath("//a[@href='#nav-credentials']"));
		credentials.sendKeys("dkdksdfdsfdffsdfsdfsdfsdf");
		jse.executeScript("arguments[0].checked=true;",credentials);
//		credentials.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#nav-credentials']"))).click();
		WebElement addCredentialButton = driver.findElement(By.xpath("//button[@id='credentialbutton']"));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("credentialbutton"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("credential-url"))).sendKeys("YaySelenium.com");

		WebElement usernameText = driver.findElement(By.id("credential-username"));
		usernameText.click();
		usernameText.sendKeys("Java Jimmy");
		WebElement passwordText = driver.findElement(By.id("credential-password"));
		passwordText.click();
		passwordText.sendKeys("randomPassword");

		WebElement credentialSubmit = driver.findElement(By.id("save-credential"));
		credentialSubmit.click();
		Assertions.assertEquals("Home", driver.getTitle());
		
	}

}
