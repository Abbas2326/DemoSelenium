package com.scenarios;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.io.FileHandler;

import com.utils.*;

public class demoScenarios {
	static String clickbtn="//button[text()='    click   ']";
	static WebDriver driver;
	static String selspo="//div[@class='row justify-content-center p-5']";
	static String skipbtn="//button[@id='btn2']";
	static String spologo="//div[@class='row justify-content-around pt-4 pb-5 px-5']";
	static String switchdp="//a[text()='SwitchTo']";
	static String swiframes="//a[text()='Windows']";
	static DriverUtils utils;

	public static WebDriver openApplication() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/MOHAABBA/Downloads/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://demo.automationtesting.in/Index.html");
		driver.manage().window().maximize();
		utils=new DriverUtils(driver);
		return driver;
	}

	public static void signIn() {
		utils.Click(skipbtn);
		utils.Click(switchdp);
		utils.Click(swiframes);
	} 

	public static void changeWindow() {
		String mainwin=driver.getWindowHandle();
		utils.Click(clickbtn);
		Set<String> newwin = driver.getWindowHandles();
	
		for( String window:newwin) {
			if(!window.equals(newwin)) {
				driver.switchTo().window(window);			}
		}

	}

	//ALSO TRY WITH TRY&CATCH
	public static void screenShot(String xpath, String xpath2) throws IOException {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds (20)); 
		WebElement ssel = driver.findElement(By.xpath(xpath));
		File file1=ssel.getScreenshotAs(OutputType.FILE);
		File fileloc1=new File("./screenshots/sel.png");
		FileHandler.copy(file1, fileloc1);
		WebElement ssell = driver.findElement(By.xpath(xpath2)); 
		File file2=ssell.getScreenshotAs(OutputType.FILE);
		File fileloc2=new File("./screenshots/sponser.png");
		FileHandler.copy(file2, fileloc2);
	}

	public static void main(String[] args) throws IOException {
		openApplication();
		signIn();
		changeWindow();
		screenShot(selspo,spologo);
		driver.close();
		//driver.quit();
	}
}
