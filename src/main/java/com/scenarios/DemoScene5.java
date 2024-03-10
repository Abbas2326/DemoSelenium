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

	static DriverUtils util;
	static WebDriver driver;

	@Test
	public static void TestStart ()throws IOException, ParseException {
		JsonCredential();
		
	}

	@BeforeTest
	private static void OpenApplication() {
		System.setProperty("WebDriver.chrome.driver", "C:/Users/MOHAABBA/Downloads/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		util = new DriverUtils(driver);

	}

	public static void JsonCredential() throws IOException, ParseException {

		FileReader reader = new FileReader("./JSON/data.json");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> userData = mapper.readValue(reader, Map.class);
		//		for (Map.Entry<String, String> a : userData.entrySet()) {
		//			if (a.getValue() != null) {
		//				System.out.println(a.getKey() + ":" + "all good to go");
		//
		//			} else {
		//				System.out.println(a.getKey() + ":" + "data missing");
		//			}
		//		}
		performSearch(userData);
		performSorting(userData);
		firstproductPrice(userData);
		//		}

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
			System.out.println(util.GetText(userData.get("product-price")));
			//System.out.println(price);
			
	}

	@AfterTest
	public static void CloseApplication() {
		driver.quit();
	}

}


