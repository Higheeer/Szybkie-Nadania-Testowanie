package SzybkieNadania.Pages.MainPage.Components;

import SzybkieNadania.Utils.Base;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DeliveryMethodTest extends Base {

    @Test
    void shouldChooseP2PMethod() {
        DeliveryMethod deliveryMethod = new DeliveryMethod(webDriver);
        deliveryMethod.choose(DeliveryMethod.Type.APM);
        assertTrue(deliveryMethod.isSelectedP2P() && !deliveryMethod.isSelectedP2H());
    }

    @Test
    void shouldChooseP2HMethod() {
        DeliveryMethod deliveryMethod = new DeliveryMethod(webDriver);
        deliveryMethod.choose(DeliveryMethod.Type.D2D);
        assertTrue(deliveryMethod.isSelectedP2H() && !deliveryMethod.isSelectedP2P());
    }

}