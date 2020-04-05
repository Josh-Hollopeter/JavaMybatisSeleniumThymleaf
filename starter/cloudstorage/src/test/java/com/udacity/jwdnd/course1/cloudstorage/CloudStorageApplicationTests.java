package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
		driver.get("http://localhost:" + this.port + "/login.do");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void unauthorizedHomePage() {
		driver.get("http://localhost:" + this.port + "/home.html");
		Assertions.assertEquals("Error page", driver.getTitle());
	}

	@Test
	public void validLoginTest() {
		driver.get("http://localhost:" + this.port + "/login.do");
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("jim");
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("jim");
		WebElement login = driver.findElement(By.id("login"));
		login.click();
//		WebElement notes = driver.findElement(By.id("nav-notes-tab"));
//		driver.navigate();
//		WebElement addNoteButton = driver.findElement(By.id("notebutton"));
//		addNoteButton.click();
//		WebElement noteTitle = driver.findElement(By.id("note-title"));
//		noteTitle.sendKeys("Yay Selenium");
//		WebElement noteDescription = driver.findElement(By.id("note-description"));
//		noteDescription.sendKeys("Why would someone do this? I GUESS THEY WERE BORED");
//		WebElement noteSubmit = driver.findElement(By.id("save-note"));
//		noteSubmit.click();
		Assertions.assertEquals("Home", driver.getTitle());
		
		

		
	}

}
