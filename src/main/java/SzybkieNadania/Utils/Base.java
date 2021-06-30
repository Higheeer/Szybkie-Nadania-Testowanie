package SzybkieNadania.Utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public abstract class Base {
    public static WebDriver webDriver;

    @BeforeAll
    public static void prepareTestEnvironment() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

        try {
            webDriver.get("https://test-oneclick-pl.easypack24.net/SzybkieNadania/");
        } catch (TimeoutException e) {
            webDriver.navigate().refresh();
        }
        PageAction.closeCookies(webDriver);
    }

    @AfterAll
    public static void closeTestEnvironment() {
        webDriver.quit();
    }
}
