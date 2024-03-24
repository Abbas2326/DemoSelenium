package com.scenarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.utils.DriverUtils;

public class DemoScene3 {
	static WebDriver driver;
	static Map<String, String> excel = new HashMap();
	static DriverUtils utils;

	public static void openApp() {
		driver=new ChromeDriver();
		utils = new DriverUtils(driver);
		utils.OpenApp("https://demo.automationtesting.in/Index.html");
	}

	public static void readValue() throws IOException {
		FileInputStream file = new FileInputStream("C:\\Users/MOHAABBA\\Downloads\\demoscenarios.xlsx");
		XSSFWorkbook book = new XSSFWorkbook(file);
		XSSFSheet sheet = book.getSheet("scene3");

		for(Row row : sheet) {
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
		utils.Click(excel.get("swiwindow"));
		utils.Click(excel.get("separatewin"));
	}

	public static void changeWindow() { 
		String mainwin=driver.getWindowHandle();
		utils.Click(excel.get("clickbtn"));
		Set<String> newwin = driver.getWindowHandles();

		for( String window:newwin) {
			if(!window.equals(mainwin)) {
				driver.switchTo().window(window);
				driver.manage().window().maximize();
			}
		}

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0, 800)");
	}

	public static void screenShot() throws IOException {
		File file1=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File fileloc1=new File("./screenshots/sel.png");
		FileHandler.copy(file1, fileloc1);
	}

	@Test
	public static  void startApp() throws IOException {
		openApp();
		readValue();
		signIn();
		changeWindow();
		//screenShot();
		driver.close();
	}

	@AfterTest
	public static void endApp() {
		driver.quit();
	}

}
