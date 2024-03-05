package com.demo;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitsClassDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//alertAccept();
		//waitLogin();
		//getTitle();
	}

	private static void alertAccept() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/MOHAABBA/Downloads/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://letcode.in/waits");
		driver.manage().window().maximize();
		driver.findElement(By.id("accept")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		driver.close();
	}
	private static void waitLogin() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/MOHAABBA/Downloads/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.manage().timeouts().
		

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		Actions act = new Actions(driver);
		
//		WebElement login = driver.findElement(By.xpath("//div[@class='_39Ul0t']"));
//		wait.until(ExpectedConditions.invisibilityOf(login));

		WebElement elec= driver.findElement(By.xpath("//span[text()='Electronics']"));
		act.moveToElement(elec).perform();
		WebElement lap = driver.findElement(By.linkText("Laptop and Desktop"));
		act.moveToElement(lap).perform();
		WebElement all = driver.findElement(By.linkText("All"));
		all.click();
		driver.close();
	}
	
	private static void getTitle() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/MOHAABBA/Downloads/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//a[text()=' Electronics ']")).click();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.titleContains("Electronics"));
		System.out.println(driver.getTitle());
		driver.close();
	}

}
