package org.example;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ResponsiveDesignTest {
    private static WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/amelkarsic/Downloads/chromedriver-mac-x64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://avaz.ba/vijesti/bih/949413/zavod-zdravstvenog-osiguranja-zdk-poceo-prodaju-godisnje-premije-osiguranja-po-cijeni-od-25-km");
    }

    @Test
    public void testMobileView() {
        // Set screen size to simulate mobile (e.g., iPhone 6/7/8)
        driver.manage().window().setSize(new Dimension(375, 667));

        WebElement header = driver.findElement(By.cssSelector(".fixed"));
        Assert.assertTrue("Header should be visible in mobile view", header.isDisplayed());

        WebElement menuButton = driver.findElement(By.cssSelector(".btn.btn-square.btn-ghost"));
        Assert.assertTrue("Mobile menu button should be present", menuButton.isDisplayed());
    }

    @Test
    public void testTabletView() {
        // Set screen size to simulate tablet (e.g., iPad)
        driver.manage().window().setSize(new Dimension(768, 1024));

        WebElement header = driver.findElement(By.cssSelector(".fixed"));
        Assert.assertTrue("Header should be visible in tablet view", header.isDisplayed());

        WebElement menuButton = driver.findElement(By.cssSelector(".btn.btn-square.btn-ghost"));
        Assert.assertTrue("Tablet menu button should be present", menuButton.isDisplayed());
    }

    @Test
    public void testDesktopView() {
        // Set screen size to simulate desktop
        driver.manage().window().setSize(new Dimension(1366, 768));

        WebElement header = driver.findElement(By.cssSelector(".fixed"));
        Assert.assertTrue("Header should be visible in mobile view", header.isDisplayed());

        // finding element in nav-bar to verify it is visible
        WebElement link = driver.findElement(By.cssSelector("a.category-bar__item--active"));
        Assert.assertTrue("Main navigation should be visible in desktop view", link.isDisplayed());
    }
    
    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
