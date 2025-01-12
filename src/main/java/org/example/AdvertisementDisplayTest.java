package org.example;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class AdvertisementDisplayTest {

    private static WebDriver driver;

    @Before
    public void setUp() {
        // Set up WebDriver for Chrome
        System.setProperty("webdriver.chrome.driver", "/Users/amelzekovic/Downloads/chromedriver-mac-arm64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        options.addArguments("accept-language=en-US,en;q=0.9");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize(); // Ensure the window is maximized for proper visibility
    }

    @Test
    public void advertisementDisplayTest() throws InterruptedException {
        // URL of the page to test
        String url = "https://www.klix.ba/vijesti/regija/hrvatska-muskarac-glasao-pa-preminuo-na-birackom-mjestu/250112049#google_vignette"; // Replace with the actual URL

        // Open the URL
        driver.get(url);

        // Wait for the page and essential elements to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        try {
            // Step 1: Verify if advertisements are loaded correctly
            WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("intext_container")));
            if (iframe.isDisplayed()) {
                System.out.println("Ad iframe loaded successfully.");
            } else {
                System.out.println("Ad iframe not displayed.");
                throw new Exception("Ad iframe not displayed.");
            }

            // Step 2: Verify if the ad element is visible
            WebElement divAbove = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Stariji muškarac umro je u nedjelju na biračkom')]")));
            if (divAbove.isDisplayed()) {
                System.out.println("Main content above ads is visible.");
            } else {
                System.out.println("Main content above ads not visible.");
                throw new Exception("Main content is obstructed by ads.");

            }

            // Step 3: Verify if ads are obstructing main content
            WebElement mainContent = driver.findElement(By.id("text")); // Use correct selector for your page
            if (mainContent.isDisplayed()) {
                System.out.println("Main content under ads is visible and not obstructed by ads.");
            } else {
                System.out.println("Main content is obstructed by ads.");
                throw new Exception("Main content is obstructed by ads.");
            }

        } catch (Exception e) {
            System.out.println("Test Failed: " + e.getMessage());
        }

        // Close the browser when done testing
        driver.quit();
    }
}
