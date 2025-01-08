package org.example;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/amelkarsic/Downloads/chromedriver-mac-x64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testAvazTitle() {

        // Navigate to Google
        driver.get("https://avaz.ba");

        // Verify the title
        String title = driver.getTitle();
        System.out.println("Current URL: " + title);

        Assert.assertEquals(title, "Dnevni avaz - Najnovije vijesti iz BiH i svijeta");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}