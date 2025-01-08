package org.example;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
        // Locate and click the "Vijesti" link
        WebElement vijestiLink = driver.findElement(By.linkText("Vijesti"));
        vijestiLink.click();

        // Verify the URL and content
        String expectedUrl = "https://avaz.ba/vijesti"; // Adjust if needed
        Assert.assertTrue("Vijesti URL mismatch", driver.getCurrentUrl().contains(expectedUrl));
    }

    @Test
    public void testCrnaHronikaLink() {
        // Locate and click the "Crna Hronika" link
        WebElement crnaHronikaLink = driver.findElement(By.linkText("CRNA HRONIKA"));
        crnaHronikaLink.click();

        // Verify the URL and content
        String expectedUrl = "https://avaz.ba/vijesti/crna-hronika"; // Adjust if needed
        Assert.assertTrue("Crna Hronika URL mismatch", driver.getCurrentUrl().contains(expectedUrl));
    }

    @Test
    public void testSportLink() {
        // Locate and click the "Sport" link
        WebElement sportLink = driver.findElement(By.linkText("Sport"));
        sportLink.click();

        // Verify the URL and content
        String expectedUrl = "https://avaz.ba/sport"; // Adjust if needed
        Assert.assertTrue("Sport URL mismatch", driver.getCurrentUrl().contains(expectedUrl));
    }

    @AfterClass
    public static void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
