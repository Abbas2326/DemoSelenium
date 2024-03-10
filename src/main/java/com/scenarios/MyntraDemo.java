package com.scenarios;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MyntraDemo {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:/Users/MOHAABBA/Downloads/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.myntra.com/");
		Actions act = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		//seraching for product
		driver.findElement(By.xpath("//input[@class='desktop-searchBar']")).sendKeys("Nike Shoes");
		driver.findElement(By.xpath("//a[@class='desktop-submit']")).click();

		//selecting filter-Men
		driver.findElement(By.xpath("(//label[@class='common-customRadio gender-label'])[1]")).click();

		//sorting price
		WebElement sort=driver.findElement(By.xpath("//div[@class='sort-sortBy']"));
		act.moveToElement(sort).perform();
		driver.findElement(By.xpath("(//div[@class='sort-sortBy']//ul[@class='sort-list']//li//label[@class='sort-label '])[6]")).click();

		//comparing value
		wait.until(ExpectedConditions.urlContains("sort=price_asc"));
		WebElement price=driver.findElement(By.xpath("(//div[@class='product-price'])[1]"));
		String amount=price.getText();
//		String amt=amount.replaceAll("[^0-9]","");
//		int rate = Integer.parseInt(amt);
		System.out.println(amount);
		driver.close();

	} 
}

