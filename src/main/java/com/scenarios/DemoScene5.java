package com.scenarios;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.apache.xmlbeans.impl.values.XmlUnsignedShortImpl;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.DriverUtils;

public class DemoScene5 {
	static WebDriver driver;
	static DriverUtils util;

	@Test
	public static void TestStart ()throws IOException, ParseException {
		jsonCredential();
	}

	@BeforeTest
	private static void openApplication() {
		System.setProperty("WebDriver.chrome.driver", "C:/Users/MOHAABBA/Downloads/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		util = new DriverUtils(driver);
	}

	public static void jsonCredential() throws IOException, ParseException {
		FileReader reader = new FileReader("./JSON/data.json");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> userData = mapper.readValue(reader, Map.class);
		performSearch(userData);
		performSorting(userData);
		firstproductPrice(userData);
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

	public static void firstproductPrice(Map<String, String> userData) {
		String price = util.GetText(userData.get("product-price"));
		int amount=Integer.parseInt(price.replaceAll("[^0-9]", ""));
		System.out.println(amount);
		if(amount<10000) {
			System.out.println("Price is less than Rs.10,000");
		}else {
			System.out.println("Price is too high!");
		}
	}

	@AfterTest
	public static void CloseApplication() {
		driver.quit();
	}
}


