package Pages.MainPage;

import Pages.MainPage.Components.DeliveryMethod;
import Pages.MainPage.Components.ParcelSize;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest {
    private static WebDriver webDriver;
    private static MainPage mainPage;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://inpost.pl/SzybkieNadania/");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.findElement(By.className("btn-cookie-trigger")).click();

        mainPage = new MainPage(webDriver, new DeliveryMethod(webDriver), new ParcelSize(webDriver));
    }

    @AfterAll
    public static void tearDown() {
        webDriver.quit();
    }

    @Test
    @Tag("Consents")
    void shouldAcceptTermsAndConditions() {
        assertTrue(mainPage.acceptTermsAndConditions());
    }

    @Test
    @Tag("Consents")
    void shouldAcceptMarketingMessages() {
        assertTrue(mainPage.acceptMarketingMessages());
    }
}
