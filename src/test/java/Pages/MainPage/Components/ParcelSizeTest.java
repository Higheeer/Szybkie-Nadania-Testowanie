package Pages.MainPage.Components;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class ParcelSizeTest {
    private static WebDriver webDriver;
    private static DeliveryMethod deliveryMethod;
    private static ParcelSize parcelSize;

    @BeforeAll
    static void staticSetUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://inpost.pl/SzybkieNadania/");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        deliveryMethod = new DeliveryMethod(webDriver);
        parcelSize = new ParcelSize(webDriver);
    }

    @AfterAll
    static void tearDown() {
        webDriver.quit();
    }

    @Test
    @Tag("P2P")
    void shouldChooseSmallSizeWhenP2PIsSelected() {
        deliveryMethod.choose(DeliveryMethod.Type.P2P);
        parcelSize.choose(ParcelSize.Size.SMALL);
        assertTrue(parcelSize.isSelectedSmallParcel() && !parcelSize.isSelectedMediumParcel() && !parcelSize.isSelectedLargeParcel());
    }

    @Test
    @Tag("P2P")
    void shouldChooseMediumSizeWhenP2PIsSelected() {
        deliveryMethod.choose(DeliveryMethod.Type.P2P);
        parcelSize.choose(ParcelSize.Size.MEDIUM);
        assertTrue(parcelSize.isSelectedMediumParcel() && !parcelSize.isSelectedSmallParcel() && !parcelSize.isSelectedLargeParcel());
    }

    @Test
    @Tag("P2P")
    void shouldChooseLargeSizeWhenP2PIsSelected() {
        deliveryMethod.choose(DeliveryMethod.Type.P2P);
        parcelSize.choose(ParcelSize.Size.LARGE);
        assertTrue(parcelSize.isSelectedLargeParcel() && !parcelSize.isSelectedSmallParcel() && !parcelSize.isSelectedMediumParcel());
    }

    @Test
    @Tag("P2H")
    void shouldChooseSmallSizeWhenP2HIsSelected() {
        deliveryMethod.choose(DeliveryMethod.Type.P2H);
        parcelSize.choose(ParcelSize.Size.SMALL);
        assertTrue(parcelSize.isSelectedSmallParcel() && !parcelSize.isSelectedMediumParcel() && !parcelSize.isSelectedLargeParcel());
    }

    @Test
    @Tag("P2H")
    void shouldChooseMediumSizeWhenP2HIsSelected() {
        deliveryMethod.choose(DeliveryMethod.Type.P2H);
        parcelSize.choose(ParcelSize.Size.MEDIUM);
        assertTrue(parcelSize.isSelectedMediumParcel() && !parcelSize.isSelectedSmallParcel() && !parcelSize.isSelectedLargeParcel());
    }

    @Test
    @Tag("P2H")
    void shouldChooseLargeSizeWhenP2HIsSelected() {
        deliveryMethod.choose(DeliveryMethod.Type.P2H);
        parcelSize.choose(ParcelSize.Size.LARGE);
        assertTrue(parcelSize.isSelectedLargeParcel() && !parcelSize.isSelectedSmallParcel() && !parcelSize.isSelectedMediumParcel());
    }

}