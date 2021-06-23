package Pages.MainPage.Components;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DeliveryMethodTest {
    private static WebDriver webDriver;

    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://inpost.pl/SzybkieNadania/");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterAll
    static void tearDown() {
        webDriver.quit();
    }

    @Test
    void shouldChooseP2PMethod() {
        DeliveryMethod deliveryMethod = new DeliveryMethod(webDriver);
        deliveryMethod.choose(DeliveryMethod.Type.P2P);
        assertTrue(deliveryMethod.isSelectedP2P() && !deliveryMethod.isSelectedP2H());
    }

    @Test
    void shouldChooseP2HMethod() {
        DeliveryMethod deliveryMethod = new DeliveryMethod(webDriver);
        deliveryMethod.choose(DeliveryMethod.Type.P2H);
        assertTrue(deliveryMethod.isSelectedP2H() && !deliveryMethod.isSelectedP2P());
    }

}