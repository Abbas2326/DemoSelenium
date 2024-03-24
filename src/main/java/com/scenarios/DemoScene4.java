package com.scenarios;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.utils.DriverUtils;

public class DemoScene4 {
	static WebDriver driver;
	static Map<String, String> excel = new HashMap<>();
	static DriverUtils util;

	@Test
	public void compareValues() throws Exception {
		readExcelValues();
		getValues();
	}

	@BeforeTest
	public void openApp() {
		driver = new ChromeDriver();
		util = new DriverUtils(driver);
		util.OpenApp("https://practice.expandtesting.com/dynamic-table");
	}

	public static void readExcelValues() throws Exception {
		FileInputStream fis = new FileInputStream("C:\\\\Users/MOHAABBA\\\\Downloads\\\\demoscenarios.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(2);

		for (Row row : sheet) {
			String element = row.getCell(0).getStringCellValue();
			String cellVal = row.getCell(1).getStringCellValue()	;
			excel.put(element, cellVal);
		}

		workbook.close();
	}

	public static void getValues() {
		String text1 = util.GetText(excel.get("value1"));
		String num = text1.replace("Chrome CPU: ", "").trim();
		String text2 = util.GetText(excel.get("value2"));
		Assert.assertEquals(num, text2, "both equal");
	}

	@AfterTest
	public void closeWindow() {
		util.CloseApp();
	}

}

