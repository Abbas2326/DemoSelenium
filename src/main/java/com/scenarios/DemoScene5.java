package com.scenarios;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.DriverUtils;

public class DemoScene5 {
	static WebDriver driver;
	static DriverUtils util;

	@BeforeTest
	private static void openApplication() {
		driver=new ChromeDriver();
		util = new DriverUtils(driver);
		util.OpenApp("https://www.myntra.com/");
	}

	@Test
	public static void jsonCredential() throws IOException, ParseException {
		FileReader reader = new FileReader("./JSON/scene5.json");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> userData = mapper.readValue(reader, Map.class);
		performSearch(userData);
		performSorting(userData);
		firstProductPrice(userData);
	}

	public static void performSearch(Map<String, String> userData) {
		util.Sendkeys(userData.get("search-field"), userData.get("item"));
		util.Click(userData.get("search-button"));
	}

	public static void performSorting(Map<String, String> userData) {
		util.Click(userData.get("filter-men"));
		util.Click(userData.get("sort-box"));
		util.Click(userData.get("low-high"));
	}

	public static void firstProductPrice(Map<String, String> userData) {
		String price = util.GetText(userData.get("first-product"));
		int amount=Integer.parseInt(price.replaceAll("[^0-9]", ""));
		System.out.println(amount);
		Assert.assertEquals(amount<10000, true);
	}

	@AfterMethod
	public static void CloseApplication() {
		util.CloseApp();;
	}

}


