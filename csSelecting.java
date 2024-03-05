package com.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class csSelecting {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:/Users/MOHAABBA/Downloads/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.yahoo.com/");
		driver.manage().window().maximize();
		//by id
		driver.findElement(By.cssSelector("#login-container")).click();
		//by class
		driver.findElement(By.cssSelector(".sign-up-link")).click();
		//by attribute
		driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys("Mohammed");
		//ends with
		driver.findElement(By.cssSelector("input[id$='lastName']")).sendKeys("Abbas");
		driver.findElement(By.cssSelector("input[name='userId']")).sendKeys("abbas222333");
		//contains
		driver.findElement(By.cssSelector("input[id*='password']")).sendKeys("Abbas@123123");
		//starts with
		driver.findElement(By.cssSelector("select[aria-label^='Birth']")).click();
		
		

	}

}
