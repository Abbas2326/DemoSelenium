package com.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

public class DriverUtils {
	private WebDriver driver;

	public DriverUtils(WebDriver driver) {
		this.driver=driver;
	}
	public void Click(String xpath) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds (20)) ;
		By by=By.xpath(xpath); 
		wait.until(ExpectedConditions.elementToBeClickable(by)); 
		driver.findElement(by).click();
	}

	public void Select(String xpath, String option) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds (20));
		By by=By.xpath(xpath); 
		//wait.until(ExpectedConditions.elementToBeSelected (by));
		//driver.findElement (by).click();
		new Select (driver.findElement (by)).selectByValue(option);
	}

	public void Sendkeys (String xpath, String inputValue) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds (20)); 
		By by=By.xpath(xpath);
		wait.until (ExpectedConditions.elementToBeClickable(by)); 
		driver.findElement (by).sendKeys(inputValue);
	}
	
	public String GetText(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		By by=By.xpath(xpath);
		wait.until(ExpectedConditions.elementToBeClickable(by));
		return driver.findElement(by).getText();
	}

}

