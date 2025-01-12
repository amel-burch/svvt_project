package org.example;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShareableLinksTest {
    private static WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/amelkarsic/Downloads/chromedriver-mac-x64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://avaz.ba/vijesti/bih/949413/zavod-zdravstvenog-osiguranja-zdk-poceo-prodaju-godisnje-premije-osiguranja-po-cijeni-od-25-km");
    }

    @Test
    public void testFacebookShareLink() {
        RemoveModal();

        WebElement facebookLink = driver.findElement(By.cssSelector("a[href*='facebook']"));
        Assert.assertNotNull("Facebook share link is not displayed", facebookLink != null);
        Assert.assertTrue("Facebook share link is not enabled", facebookLink.isEnabled());
    }

    @Test
    public void testTwitterShareLink() {
        RemoveModal();

        WebElement twitterLink = driver.findElement(By.cssSelector("a[href*='twitter']"));
        Assert.assertNotNull("Twitter share link is not displayed", twitterLink != null);
        Assert.assertTrue("Twitter share link is not enabled", twitterLink.isEnabled());
    }

    @Test
    public void testWhatsAppShareLink() {
        RemoveModal();

        WebElement whatsappLink = driver.findElement(By.cssSelector("a[href*='whatsapp']"));
        Assert.assertNotNull("WhatsApp share link is not displayed", whatsappLink!= null);
        Assert.assertTrue("WhatsApp share link is not enabled", whatsappLink.isEnabled());
    }

    @Test
    public void testViberAppShareLink() {
        RemoveModal();

        WebElement whatsappLink = driver.findElement(By.cssSelector("a[href*='viber']"));
        Assert.assertNotNull("Viber share link is not displayed", whatsappLink!= null);
        Assert.assertTrue("Viber share link is not enabled", whatsappLink.isEnabled());
    }

    @Test
    public void testCopyLinkButton() {
        RemoveModal();

        WebElement copyButton = driver.findElement(By.cssSelector("button[aria-label='copy']"));
        Assert.assertNotNull("Copy link button is not displayed", copyButton!= null);
        Assert.assertTrue("Copy link button is not enabled", copyButton.isEnabled());
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
        if (driver != null) {
            driver.quit();
        }
    }
}
