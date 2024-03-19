package com.scenarios;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.utils.DriverUtils;

public class DemoScene2 {
	static WebDriver driver;
	static Map<String, String> excel = new HashMap();
	static String url="https://demo.automationtesting.in/Index.html";
	static DriverUtils utils;

	public static void runApp() {
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
			Cell cellVal = row.getCell(1);
			//String cellVal = row.getCell(1).getStringCellValue()	;
			excel.put(element, getStringValue(cellVal));
		}

		book.close();
	}

	private static String getStringValue(Cell cell) {

		if (cell == null) {
			return null;
		} else if (cell.getCellType() == CellType.STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else {
			return null;
		}

	}

	public static void signIn() {
		utils.Click(excel.get("skipbtn"));
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//a[text()='SwitchTo']")))
		.moveToElement(driver.findElement(By.xpath("//a[text()='Frames']")))
		.click().build().perform();
		utils.Click(excel.get("iframe"));
	}

	public static void changeFrame() {
		WebElement pframe=driver.findElement(By.xpath("//iframe[@src='MultipleFrames.html']"));
		driver.switchTo().frame(pframe);
		WebElement cframe=driver.findElement(By.xpath("//iframe[@src='SingleFrame.html']"));
		driver.switchTo().frame(cframe);
		utils.Sendkeys(excel.get("textbox"),excel.get("tbvalue"));
		driver.switchTo().defaultContent();
	}

	public static void selectDate() {
		utils.Click(excel.get("widgets"));
		utils.Click(excel.get("datepicker"));
		utils.Click(excel.get("picker1"));
		utils.Click(excel.get("today"));
		utils.Click(excel.get("picker2"));
		utils.Sendkeys(excel.get("month"),excel.get("mvalue"));
		utils.Sendkeys(excel.get("year"),excel.get("yvalue"));
		utils.Click(excel.get("date2"));
	}

	@Test
	public static  void startApp() throws IOException {
		runApp();
		readValue();
		signIn();
		changeFrame();
		selectDate();
	}

	@AfterMethod
	public static void exitApp() {
		utils.CloseApp();
	}

}
