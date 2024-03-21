package com.testgrid;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridTest {

	public static void main(String[] args) {

		String hubUrl = "http://localhost:4444/wd/hub";
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName("chrome");

		try {

			WebDriver driver = new RemoteWebDriver(new URL(hubUrl), capabilities);
			driver.get("https://www.w3schools.com");
			System.out.println("Page title: " + driver.getTitle());
			driver.quit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}