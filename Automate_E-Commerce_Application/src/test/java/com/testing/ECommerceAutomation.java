package com.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ECommerceAutomation {
	WebDriver chromedriver;

	@BeforeTest
	public void beforeTest() {

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32/chromedriver.exe");
		System.out.println("Testing is about to start..");
	}

	@Test
	public void executeChromeTest() throws InterruptedException {
		chromedriver = new ChromeDriver();

		long s = System.currentTimeMillis();

	
		chromedriver.get("https://www.flipkart.com/");
		chromedriver.manage().window().maximize();
		System.out.println("Website is opened!!");

		long e = System.currentTimeMillis();

		long r = e - s;
		System.out.println("Page load time in milliseconds: " + r);
		System.out.println("=====================================");
		Thread.sleep(2000);

		chromedriver.findElement(By.xpath("//button[contains(.,'✕')]")).click();
		System.out.println("Singup activity is closed.");

		// search iPhone 13
		chromedriver.findElement(By.xpath("//input[@name='q']")).sendKeys("iPhone 13");
		System.out.println("Inserted the keyword in Search Box..");
		chromedriver.findElement(By.cssSelector(".L0Z3Pu > svg")).click();
		System.out.println("Clicked On Search Icon..");

		// Image loading
		String url = "https://www.flipkart.com/search?q=iphone%2013&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off";
		chromedriver.get(url);

		// identify image
		WebElement i = chromedriver.findElement(By.xpath(
				"//img[contains(@src,'https://rukminim1.flixcart.com/image/312/312/ktketu80/mobile/s/l/c/iphone-13-mlpf3hn-a-apple-original-imag6vzz5qvejz8z.jpeg?q=70')]"));

		// JavaScript executor to check image
		Boolean p = (Boolean) ((JavascriptExecutor) chromedriver).executeScript("return arguments[0].complete "
				+ "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0", i);

		// verify if status is true
		if (p) {
			System.out.println("Image Loaded");
		} else {
			System.out.println("Image not Loaded");
		}

		// Check Scrolling

		long lastHeight = (Long) ((JavascriptExecutor) chromedriver).executeScript("return document.body.scrollHeight");

		while (true) {
			((JavascriptExecutor) chromedriver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
			Thread.sleep(2000);

			long newHeight = (Long) ((JavascriptExecutor) chromedriver)
					.executeScript("return document.body.scrollHeight");
			if (newHeight == lastHeight) {
				break;
			}
			lastHeight = newHeight;
		}

		if (lastHeight > 0)
			System.out.println("Scrolled & Height of Page : " + lastHeight);
		else
			System.out.println("Not Scrolled");
		 Thread.sleep(6000);
	}
	 

	@AfterTest
	public void AfterTest() {
		chromedriver.close();
	}

}
