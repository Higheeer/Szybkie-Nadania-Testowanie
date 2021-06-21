import Pages.MainPage.Components.ParcelSize;
import Pages.MainPage.MainPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class MainPageTest {
    private static WebDriver webDriver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://inpost.pl/SzybkieNadania/");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterAll
    public static void tearDown() {
        webDriver.quit();
    }

    @Test
    public void shouldChooseMediumParcelSize() {
        MainPage test = new MainPage(webDriver, new ParcelSize(webDriver));
        test.chooseParcelSize(ParcelSize.Size.MEDIUM);

    }

    @Test
    public void shouldChooseP2DDelivery() {
        MainPage test = new MainPage(webDriver, new ParcelSize(webDriver));
        test.chooseDeliveryType(MainPage.DeliveryType.P2D);
    }
}
