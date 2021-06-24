package Pages.MainPage;

import Pages.MainPage.Components.DeliveryMethod;
import Pages.MainPage.Components.ParcelSize;
import Pages.MainPage.Utils.Base;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest extends Base {
    private static MainPage mainPage;

    @BeforeAll
    public static void staticSetUp() {
        webDriver.findElement(By.className("btn-cookie-trigger")).click();
        //webDriver.findElement(By.id("onetrust-accept-btn-handler")).click();

        mainPage = new MainPage(webDriver, new DeliveryMethod(webDriver), new ParcelSize(webDriver));
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
