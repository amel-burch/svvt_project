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

public class CommentsTest {
    private static WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/amelkarsic/Downloads/chromedriver-mac-x64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://klix.ba/");
    }

    @Test
    public void testLoggedInUserComment() {
        Login();

        try {
            WebElement firstArticleLink = driver.findElement(By.cssSelector("article > a"));
            firstArticleLink.click();
        } catch (StaleElementReferenceException e) {
            // Re-locate the element if it becomes stale
            WebElement firstArticleLink = driver.findElement(By.cssSelector("article > a"));
            firstArticleLink.click();
        }

        // Wait for the comments link to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement commentsLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.hover\\:no-underline.hover\\:text-gray-500.dark\\:hover\\:text-white.block")));
        commentsLink.click();

        WebElement commentInput = driver.findElement(By.id("komentarinput"));
        commentInput.sendKeys("Dobar potez");

        // if this was successful the comment would be added,
        // we did not do this to not spam the site
//    WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
//    submitButton.click();
    }

    @Test
    public void testNotLoggedInUserComment() {
        try {
            WebElement firstArticleLink = driver.findElement(By.cssSelector("article > a"));
            firstArticleLink.click();
        } catch (StaleElementReferenceException e) {
            // Re-locate the element if it becomes stale
            WebElement firstArticleLink = driver.findElement(By.cssSelector("article > a"));
            firstArticleLink.click();
        }

        // Wait for the comments link to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement commentsLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.hover\\:no-underline.hover\\:text-gray-500.dark\\:hover\\:text-white.block")));
        commentsLink.click();

        try {
            WebElement commentInput = driver.findElement(By.id("komentarinput"));
            boolean isVisible = commentInput.isDisplayed();

            if (!isVisible) {
                System.out.println("The comment input is not visible on the screen.");
            } else {
                System.out.println("The comment input is visible on the screen.");
            }
        } catch (NoSuchElementException e) {
            Assert.assertTrue(e.getMessage().contains("no such element"));
        }
    }
    private void Login() {
        String username = "burch-test";
        String password = "ButchTest1!";

        // Create WebDriverWait instance
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the login button to be visible and click it
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("user")));
        loginButton.click();

        // Wait for the login form to appear
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))); // Replace "username" with actual name attribute
        WebElement passwordField = driver.findElement(By.name("password")); // Replace "password" with actual name attribute

        // Enter credentials
        usernameField.sendKeys(username); // Replace with your username
        passwordField.sendKeys(password); // Replace with your password

        // Click the login button in the form
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Prijavi se')]")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.click();
    }
    @AfterClass
    public static void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
