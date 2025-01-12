package org.example;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class PaginationTest {

    private static WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/amelzekovic/Downloads/chromedriver-mac-arm64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize(); // Ensure the window is maximized for proper visibility
    }

    @Test
    public void testNextPagePagination() {
        // URL of the page to test
        String url = "https://www.klix.ba/sport/nogomet?str=2"; // Replace with actual URL

        // Open the URL
        driver.get(url);

        // Wait for the page to load completely (adjust this as needed)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        try {
            // Wait for the next page link to be visible
            WebElement nextPageLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, 'str=3')]")));

            // Verify if the button is visible
            if (nextPageLink.isDisplayed()) {
                System.out.println("Next page button is visible.");

                // Get the current URL before clicking the "Next Page" button
                String currentUrl = driver.getCurrentUrl();
                System.out.println("Current URL before clicking next: " + currentUrl);

                // Click the "Next Page" button
                nextPageLink.click();

                // Wait for the page to load after clicking the "Next Page" button
                wait.until(ExpectedConditions.stalenessOf(nextPageLink)); // Wait for the page content to change

                // Get the new URL after clicking the button
                String newUrl = driver.getCurrentUrl();
                System.out.println("New URL after clicking next: " + newUrl);

                // Verify if the URL has changed (indicating that the page has loaded the next set of articles)
                if (!currentUrl.equals(newUrl)) {
                    System.out.println("Successfully navigated to the next page.");
                } else {
                    System.out.println("Page did not change after clicking the next button.");
                }
            } else {
                System.out.println("Next page button is not visible.");
            }
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        }

        // Close the browser after test
        driver.quit();
    }

    @Test
    public void testPreviousPagePagination() {
        // URL of the page to test
        String url = "https://www.klix.ba/sport/nogomet?str=2"; // Replace with actual URL

        // Open the URL
        driver.get(url);

        // Wait for the page to load completely (adjust this as needed)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        try {
            // Wait for the next page link to be visible
            WebElement previousPageLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, 'str=1')]")));

            // Verify if the button is visible
            if (previousPageLink.isDisplayed()) {
                System.out.println("Previous page button is visible.");

                // Get the current URL before clicking the "Next Page" button
                String currentUrl = driver.getCurrentUrl();
                System.out.println("Current URL before clicking previous: " + currentUrl);

                // Click the "Next Page" button
                previousPageLink.click();

                // Wait for the page to load after clicking the "Next Page" button
                wait.until(ExpectedConditions.stalenessOf(previousPageLink)); // Wait for the page content to change

                // Get the new URL after clicking the button
                String newUrl = driver.getCurrentUrl();
                System.out.println("New URL after clicking previous: " + newUrl);

                // Verify if the URL has changed (indicating that the page has loaded the next set of articles)
                if (!currentUrl.equals(newUrl)) {
                    System.out.println("Successfully navigated to the previous page.");
                } else {
                    System.out.println("Page did not change after clicking the previous button.");
                }
            } else {
                System.out.println("Next page button is not visible.");
            }
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        }

        // Close the browser after test
        driver.quit();
    }
}