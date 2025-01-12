package org.example;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DarkLightModeTest {

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
    public void lightDarkTest() throws InterruptedException {
            // URL of the page to test
            String url = "https://klix.ba"; // Replace with the actual URL

            driver.get(url);
        try {

            WebElement lightElement = driver.findElement(By.xpath("//*[contains(@class, 'light')]"));

            WebElement lightButton = driver.findElement(By.xpath("//li//label[text()=\"Theme\"]/following-sibling::button"));

            // Click on the element
            if (lightElement.isDisplayed()) {
                lightElement.click();
            }

            // Wait for a short period to allow changes to take place
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            // Check if an element with class "dark" exists
            boolean isDarkPresent = !driver.findElements(By.xpath("//*[contains(@class, 'dark')]")).isEmpty();

            if (isDarkPresent) {
                Thread.sleep(5000);
                System.out.println("Test passed: dark mode is active.");
            } else {
                System.out.println("Test failed: dark mode is not active.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        driver.quit();
    }
}
