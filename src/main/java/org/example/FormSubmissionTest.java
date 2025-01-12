package org.example;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class FormSubmissionTest {

    private static WebDriver driver;

    @Before
    public void setUp() {
        // Set up the WebDriver for Chrome
        System.setProperty("webdriver.chrome.driver", "/Users/amelzekovic/Downloads/chromedriver-mac-arm64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        options.addArguments("accept-language=en-US,en;q=0.9");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize(); // Ensure the window is maximized for proper visibility
    }

    @Test
    public void formSubmissionTest() throws InterruptedException {
        // URL of the form page
        String url = "https://avaz.pressreader.com/accounting/signup";
        driver.get(url);

        // Wait for the page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        try {
            // Find and fill out the form fields
            WebElement emailField = driver.findElement(By.id("Contract_EmailAddress"));
            WebElement passwordField= driver.findElement(By.id("Contract_Password"));
            WebElement confirmPasswordField = driver.findElement(By.id("Contract_ConfirmPassword"));
            WebElement firstNameField = driver.findElement(By.id("Contract_FirstName"));
            WebElement lastNameField = driver.findElement(By.id("Contract_LastName"));
            WebElement checkbox1 = driver.findElement(By.className("checkbox"));
            WebElement submitButton = driver.findElement(By.xpath("//button[@type=\"submit\"]"));


            // Fill out the form
            emailField.sendKeys("testuser@example.com");
            firstNameField.sendKeys("Test");
            lastNameField.sendKeys("User");
            passwordField.sendKeys("TestPassword123");
            confirmPasswordField.sendKeys("test555");
            // Submit the form
            submitButton.click();

            Thread.sleep(5000);


            // Wait for the confirmation (success message or page change)
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-body"))); // Adjust the selector

            // If a success message appears, print success
            WebElement successMessage = driver.findElement(By.className("alert-body"));
            if (successMessage.isDisplayed()) {
                successMessage.getText();
                System.out.println("Form submission failed." + successMessage.getText());
                throw new Exception("Form submission failed.");
            } else {
                System.out.println("Form submission passed.");
            }

        } catch (Exception e) {
            System.out.println("Test Failed: " + e.getMessage());
        }

        // Close the browser when done testing
        driver.quit();
    }
}