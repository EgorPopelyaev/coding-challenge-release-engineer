package com.ebayk.web;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class EbayKWebTest {
    private static WebDriver driver;

    @BeforeClass
    public static void init() {
        driver = new ChromeDriver();
    }

    @Test
    public void openEbayKLoginPage() {
        driver.get("https://www.ebay-kleinanzeigen.de/");
        WebElement loginButton = driver.findElement(By.linkText("Einloggen"));
        loginButton.click();

        Assert.assertTrue(driver.findElement(By.id("login-email")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("login-password")).isDisplayed());
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
