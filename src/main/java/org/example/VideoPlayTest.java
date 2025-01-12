package org.example;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.time.Duration;

public class VideoPlayTest {

    private static WebDriver driver;

    @Before
    public void setUp() {
        // Set up the WebDriver for Chrome
        System.setProperty("webdriver.chrome.driver", "/Users/amelzekovic/Downloads/chromedriver-mac-arm64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        options.addArguments("accept-language=en-US,en;q=0.9");
        options.addArguments("accept-encoding=gzip, deflate, br");
        options.addArguments("connection=keep-alive");
        options.addArguments("upgrade-insecure-requests=1");
        options.addArguments("cache-control=max-age=0");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize(); // Ensure the window is maximized for proper visibility
    }

    @Test
    public void videoTest() throws InterruptedException {
        // URL to test (the page with the embedded video)
        String url = "https://www.klix.ba/vijesti/crna-hronika/sudar-dva-automobila-na-marijin-dvoru-obustavljen-tramvajski-saobracaj/250112040";

        // Open the URL
        driver.get(url);

        // Wait for the page and key elements to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        Thread.sleep(3000);

        try {
            // Wait for the video element to be visible
            WebElement videoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id("TargetVideo_36685029")));  // Adjust the selector for embedded video (YouTube, etc.)

            // Interact with the video if it's a <video> tag
            if (videoElement.getTagName().equals("video") | videoElement.getTagName().equals("div")) {
                // Click to play the video if it's a <video> element
                videoElement.click();
                Thread.sleep(7000);
                System.out.println("Video element found and clicked to play.");
            } else if (videoElement.getTagName().equals("iframe")) {
                // If the video is in an iframe (e.g., embedded YouTube video), handle the iframe
                System.out.println("Embedded video in iframe detected.");
                // Optionally, switch to iframe and click the play button inside it
                driver.switchTo().frame(videoElement);

                // You may need to interact with the embedded player (e.g., YouTube) inside the iframe
                WebElement playButton = driver.findElement(By.xpath("//button[contains(@aria-label, 'Play')]"));
                playButton.click();
                System.out.println("Video in iframe clicked to play.");
                driver.switchTo().defaultContent();  // Switch back to the main page after interacting with the iframe
            }

            System.out.println("Test Passed: Embedded video is working.");

        } catch (Exception e) {
            System.out.println("Test Failed: " + e.getMessage());
        }

        // Close the browser when done testing
        driver.quit();
    }
}