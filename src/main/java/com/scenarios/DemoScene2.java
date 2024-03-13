package com.scenarios;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.utils.DriverUtils;

public class DemoScene2 {
	static WebDriver driver;
	static Map<String, String> excel = new HashMap();
	static DriverUtils utils;

	public static void openApp() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/MOHAABBA/Downloads/chromedriver.exe");
		driver=new ChromeDriver();
		utils=new DriverUtils(driver);
		driver.get("https://demo.automationtesting.in/Index.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
	}

	public static void readValue() throws IOException {
		FileInputStream file = new FileInputStream("C:/Users/MOHAABBA/Downloads/demoscenarios.xlsx");
		XSSFWorkbook book = new XSSFWorkbook(file);
		XSSFSheet sh = book.getSheet("scene2");

		for(Row row : sh) {
			String element = row.getCell(0).getStringCellValue();
			//Cell cellVal = row.getCell(1);
			String cellVal = row.getCell(1).getStringCellValue()	;	
			excel.put(element, cellVal);
		}
		book.close();

	}

	public static void signIn() {
		utils.Click(excel.get("skipbtn"));
		utils.Click(excel.get("switchdp"));
		utils.Click(excel.get("swiframes"));
		utils.Click(excel.get("iframe"));
	}

//	public static void ChangeFrame() {
//		
//		
//	}

	@Test
	public static  void startApp() throws IOException {
		openApp();
		readValue();
		signIn();
	}

	@AfterMethod
	public static void endApp() {
		driver.quit();
	}
}
