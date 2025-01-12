package org.example;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationLinksTest {
    private static WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/amelkarsic/Downloads/chromedriver-mac-x64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://avaz.ba/");
    }

    @Test
    public void testVijestiLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement vijestiLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Vijesti")));

        vijestiLink.click();
        String expectedUrl = "https://avaz.ba/vijesti";
        System.out.println(driver.getCurrentUrl().contains(expectedUrl));
        Assert.assertTrue("Vijesti URL mismatch", driver.getCurrentUrl().contains(expectedUrl));
    }

    @Test
    public void testSportLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement sportLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sport")));
        sportLink.click();

        String expectedUrl = "https://avaz.ba/sport";
        System.out.println(driver.getCurrentUrl().contains(expectedUrl));

        Assert.assertTrue("Sport URL mismatch", driver.getCurrentUrl().contains(expectedUrl));
    }

    private void RemoveModal(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement overlay = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("onesignal-slidedown-container")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", overlay);
        } catch (Exception e) {
            // If the overlay doesn't appear, continue without issues
        }
    }

    @AfterClass
    public static void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
