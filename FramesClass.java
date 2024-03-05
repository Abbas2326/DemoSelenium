package com.demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FramesClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:/Users/MOHAABBA/Downloads/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://letcode.in/frame");
		driver.manage().window().maximize();

		//switching by index
		driver.switchTo().frame(0);
		
		//driver.switchTo().frame("firstFr");
		driver.findElement(By.xpath("//input[@name='fname']")).sendKeys("Moham");
		driver.findElement(By.name("lname")).sendKeys("Abbas");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		//switching by element
		WebElement ele=driver.findElement(By.xpath("//iframe[@src='innerFrame']"));
		driver.switchTo().frame(ele);
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("abbas@gamil.com");
		

		//parent frame
		driver.switchTo().parentFrame();
		driver.findElement(By.name("fname")).sendKeys("med");
		
		
		//default frame
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[text()='Watch tutorial']")).click();
		

		driver.close();


	}

}
