package com.utils;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyntraDemo {

	static DriverUtils util;
	static WebDriver driver;

	@Test
	public static void TestStart ()throws IOException, ParseException {
		JsonCredential();
		CloseApplication();
	}

	@BeforeTest
	private static WebDriver OpenApplication() {
		System.setProperty("WebDriver.chrome.driver", "C:/Users/MOHAABBA/Downloads/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		util = new DriverUtils(driver);
		return driver;
	}

	public static void JsonCredential() throws IOException, ParseException {

		FileReader reader = new FileReader("./JSON/data.json");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> userData = mapper.readValue(reader, Map.class);
		for (Map.Entry<String, String> a : userData.entrySet()) {
			if (a.getValue() != null) {
				System.out.println(a.getKey() + ":" + "all good to go");

			} else {
				System.out.println(a.getKey() + ":" + "data missing");
			}
		}
		performClickOperations(userData);
		PerformDateSelection(userData);
		performSendKeysOperations(userData);

	}

	public static void performClickOperations(Map<String, String> userData) {
		util.Click(userData.get("skipsigninButton"));
		util.Click(userData.get("genderxpath"));
		util.Click(userData.get("hobby1"));
		util.Click(userData.get("hobby2"));

	}

	public static void PerformDateSelection(Map<String, String> userData) {
		//util.JsExecutors("window.scrollBy(0,document.body.scrollHeight)", "");
		util.Select(userData.get("yearxpath"), userData.get("yearvalue"));
		util.Select(userData.get("monthxpath"), userData.get("monthvalue"));
		util.Select(userData.get("dayxpath"), userData.get("dayvalue"));

	}

	public static void performSendKeysOperations(Map<String, String> userData) {
		util.Sendkeys(userData.get("searchfield"), userData.get("Nike Shoes"));
		util.Click(userData.get("usersubmit"));

	}

	@AfterTest
	public static void CloseApplication() {
		driver.quit();
	}

}


