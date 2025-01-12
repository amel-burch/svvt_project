package org.example;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class PageLoadTimeTest {

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
    public void measurePageLoadTime() {
        // URL of the page to test
        String url = "https://avaz.ba"; // Replace with the actual URL

        // Start measuring time before page load
        long startTime = System.currentTimeMillis();

        // Open the URL
        driver.get(url);

        // Measure the time after the page has loaded
        long endTime = System.currentTimeMillis();

        // Calculate the page load time
        long loadTime = endTime - startTime;

        System.out.println("Page load time: " + loadTime + " milliseconds");

        // You can set a performance threshold (e.g., 5000 ms)
        long threshold = 5000;  // Example threshold (5 seconds)

        if (loadTime <= threshold) {
            System.out.println("Performance test passed: Page loaded in acceptable time.");
        } else {
            System.out.println("Performance test failed: Page load time exceeded the threshold.");
        }

        // Close the browser
        driver.quit();
    }
}
