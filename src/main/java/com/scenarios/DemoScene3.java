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
import org.testng.annotations.Test;

import com.utils.DriverUtils;

public class DemoScene3 {
	static WebDriver driver;
	static Map<String, String> excel = new HashMap();
	static String selspo="//div[@class='row justify-content-center p-5']";
	static String spologo="//div[@class='row justify-content-around pt-4 pb-5 px-5']";
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
		XSSFSheet sh = book.getSheetAt(0);

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
	}

	public static void changeWindow() {
		String mainwin=driver.getWindowHandle();
		utils.Click(excel.get("clickbtn"));
		Set<String> newwin = driver.getWindowHandles();

		for( String window:newwin) {
			if(!window.equals(mainwin)) {
				driver.switchTo().window(window);
			}
		}

	}

	public static void scrollWindow() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1000);");
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
		scrollWindow();
		//screenShot();
	}

	@AfterMethod
	public static void endApp() {
		driver.quit();
	}
}
