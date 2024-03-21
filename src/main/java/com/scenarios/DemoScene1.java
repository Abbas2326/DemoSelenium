package com.scenarios;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.DriverUtils;

public class DemoScene1 {
	static WebDriver driver;
	static DriverUtils utils;
	
	@Test
	public static void jsonCredential() throws IOException, ParseException{
		FileReader reader = new FileReader("./JSON/scene1.json");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> userDetails = mapper.readValue(reader, Map.class);
		startApp(userDetails);
		enterValues(userDetails);
	} 

	public static void startApp(Map<String, String> userDetails) {
		driver=new ChromeDriver();
		utils = new DriverUtils(driver);
		utils.OpenApp("https://demo.automationtesting.in/Index.html");
	}

	public static void enterValues(Map<String, String> userDetails) {
		utils.Click(userDetails.get("skip-button"));
		utils.Sendkeys(userDetails.get("first-name"), userDetails.get("name1"));
		utils.Sendkeys(userDetails.get("last-name"), userDetails.get("name2"));
		utils.Sendkeys(userDetails.get("e-mail"), userDetails.get("mailid"));
		utils.Sendkeys(userDetails.get("phone"), userDetails.get("number"));
		utils.Click(userDetails.get("gender"));
		utils.Click(userDetails.get("hobbie1"));
		utils.Click(userDetails.get("hobbie2"));
		WebElement ele = driver.findElement(By.xpath("//select[@id='countries']"));
		Select sc= new Select(ele);
		sc.selectByVisibleText("Select Country");
		utils.Select(userDetails.get("dobyear"), userDetails.get("year"));
		utils.Select(userDetails.get("dobmonth"), userDetails.get("month"));
		utils.Select(userDetails.get("dobday"), userDetails.get("day"));
	}

	@AfterMethod
	public static void closeApp() {
		driver.quit();
	}
}
