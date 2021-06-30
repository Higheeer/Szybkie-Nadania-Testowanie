package SzybkieNadania.Pages.MainPage.Components;

import SzybkieNadania.Utils.Base;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParcelSizeTest extends Base {
    private static DeliveryMethod deliveryMethod;
    private static ParcelSize parcelSize;

    @BeforeAll
    static void staticSetup() {
        deliveryMethod = new DeliveryMethod(webDriver);
        parcelSize = new ParcelSize(webDriver);
    }

    @ParameterizedTest
    @EnumSource(ParcelSize.Size.class)
    void shouldChooseCorrectSizeWhenSizeGivenAndDeliveryMethodIsAPM(ParcelSize.Size size) {
        deliveryMethod.choose(DeliveryMethod.Type.APM);

        parcelSize.choose(size);

        assertEquals(size, parcelSize.isSelected());
    }

    @ParameterizedTest
    @EnumSource(ParcelSize.Size.class)
    void shouldChooseCorrectSizeWhenSizeGivenAndDeliveryMethodIsD2D(ParcelSize.Size size) {
        deliveryMethod.choose(DeliveryMethod.Type.D2D);

        parcelSize.choose(size);

        assertEquals(size, parcelSize.isSelected());
    }


}