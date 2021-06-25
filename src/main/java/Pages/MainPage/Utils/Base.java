package Pages.MainPage.Utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
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
        webDriver.get("https://inpost.pl/SzybkieNadania/");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.findElement(By.className("btn-cookie-trigger")).click();
    }

    @AfterAll
    public static void closeTestEnvironment() {
        //webDriver.quit();
    }
}
