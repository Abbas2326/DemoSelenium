package com.utils;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverUtils {
	private static WebDriver driver;

	public DriverUtils(WebDriver driver) {
		DriverUtils.driver=driver;
	}

	public void OpenApp(String url) {
		System.setProperty("webdriver.chrome.driver", "C:/Users/MOHAABBA/Downloads/chromedriver.exe");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
		driver.manage().window().maximize();
	}

	public void Click(String xpath) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds (20)) ;
		By by=By.xpath(xpath);
		wait.until(ExpectedConditions.elementToBeClickable(by));
		driver.findElement(by).click();
	}

	public void Select(String xpath, String option) {
		//WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds (20));
		By by=By.xpath(xpath);
		//wait.until(ExpectedConditions.elementToBeSelected (by));
		new Select(driver.findElement(by)).selectByValue(option);
	}

	public void Sendkeys (String xpath, String inputValue) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds (20));
		By by=By.xpath(xpath);
		wait.until (ExpectedConditions.elementToBeClickable(by));
		driver.findElement(by).sendKeys(inputValue);
	}

	public String GetText(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		By by=By.xpath(xpath);
		wait.until(ExpectedConditions.elementToBeClickable(by));
		return driver.findElement(by).getText();
	}

	public void SwitchWindow(String windowId, Set<String> windowIds) {
		for( String window:windowIds) {
			if(!window.equals(windowId)) {
				driver.switchTo().window(window);
				driver.manage().window().maximize();
			}
		}
	}

	public void CloseApp() {
		driver.quit();
	}

}

