package com.scenarios;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.DriverUtils;

public class RepliconBalanceOffs {
	WebDriver driver;
	Map<String, String> excel = new HashMap();
	DriverUtils utils;

	@BeforeMethod
	public void openApp() {
		driver = new ChromeDriver();
		utils = new DriverUtils(driver);

		utils.OpenApp("https://talent.capgemini.com/in");
	}

	public void readValue() throws IOException {
		FileInputStream file = new FileInputStream("C:\\Users/MOHAABBA\\Downloads\\demoscenarios.xlsx");
		XSSFWorkbook book = new XSSFWorkbook(file);
		XSSFSheet sheet = book.getSheet("repliconBalanceOff");

		for(Row row : sheet) {
			String element = row.getCell(0).getStringCellValue();
			//Cell cellVal = row.getCell(1);
			String cellVal = row.getCell(1).getStringCellValue()	;
			excel.put(element, cellVal);
		}

		book.close();
	} 

	public void navigateToBalanceOffMyReplicon() {
		String mainpage = driver.getWindowHandle();

		utils.Click(excel.get("RepliconLink"));

		Set<String> replicon = driver.getWindowHandles();
		utils.SwitchWindow(mainpage, replicon);

		utils.Click(excel.get("MyReplicon"));
		utils.Click(excel.get("TimeOffTab"));
		utils.Click(excel.get("BalanceOffs"));
	}

	public void listBalanceLeave() {
		List<WebElement> offTypes = driver.findElements(By.xpath(excel.get("TimeOffTypes")));
		List<WebElement> balances = driver.findElements(By.xpath(excel.get("BalanceLeaves")));

		// Iterate over offtypes and balance leaves
		Map<String, String> allBalance = new LinkedHashMap<>();
		for (int i = 0; i < offTypes.size()-1; i++) {
			WebElement off = offTypes.get(i);
			WebElement bal = balances.get(i);

			// Getting text from the element
			String offTypeText = off.getText();
			String balText = bal.getText();

			// Remove and trimming for expected result
			String offName = offTypeText.replace("[IND] -", "").trim();
			String leaveCount = balText.replace(" days", "").trim();

			// Adding keys and values to the map
			allBalance.put(offName, leaveCount);
		}

		for (Map.Entry<String, String> entry : allBalance.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
	}

	@Test
	public void runMethods() throws IOException {
		readValue();
		navigateToBalanceOffMyReplicon();
		listBalanceLeave();
	}

	@AfterMethod
	public void closeApp() {
		driver.quit();
	}

}
