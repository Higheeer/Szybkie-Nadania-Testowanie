package SzybkieNadania.Pages.MainPage.Components;

import SzybkieNadania.Utils.Base;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParcelSizeTest extends Base {
    private static DeliveryMethod deliveryMethod;
    private static ParcelSize parcelSize;

    @BeforeAll
    static void staticSetup() {
        deliveryMethod = new DeliveryMethod(webDriver);
        parcelSize = new ParcelSize(webDriver);
    }

    @Test
    @Tag("P2P")
    void shouldChooseSmallSizeWhenP2PIsSelected() {
        deliveryMethod.choose(DeliveryMethod.Type.APM);
        parcelSize.choose(ParcelSize.Size.SMALL);
        assertTrue(parcelSize.isSelectedSmallParcel() && !parcelSize.isSelectedMediumParcel() && !parcelSize.isSelectedLargeParcel());
    }

    @Test
    @Tag("P2P")
    void shouldChooseMediumSizeWhenP2PIsSelected() {
        deliveryMethod.choose(DeliveryMethod.Type.APM);
        parcelSize.choose(ParcelSize.Size.MEDIUM);
        assertTrue(parcelSize.isSelectedMediumParcel() && !parcelSize.isSelectedSmallParcel() && !parcelSize.isSelectedLargeParcel());
    }

    @Test
    @Tag("P2P")
    void shouldChooseLargeSizeWhenP2PIsSelected() {
        deliveryMethod.choose(DeliveryMethod.Type.APM);
        parcelSize.choose(ParcelSize.Size.LARGE);
        assertTrue(parcelSize.isSelectedLargeParcel() && !parcelSize.isSelectedSmallParcel() && !parcelSize.isSelectedMediumParcel());
    }

    @Test
    @Tag("P2H")
    void shouldChooseSmallSizeWhenP2HIsSelected() {
        deliveryMethod.choose(DeliveryMethod.Type.D2D);
        parcelSize.choose(ParcelSize.Size.SMALL);
        assertTrue(parcelSize.isSelectedSmallParcel() && !parcelSize.isSelectedMediumParcel() && !parcelSize.isSelectedLargeParcel());
    }

    @Test
    @Tag("P2H")
    void shouldChooseMediumSizeWhenP2HIsSelected() {
        deliveryMethod.choose(DeliveryMethod.Type.D2D);
        parcelSize.choose(ParcelSize.Size.MEDIUM);
        assertTrue(parcelSize.isSelectedMediumParcel() && !parcelSize.isSelectedSmallParcel() && !parcelSize.isSelectedLargeParcel());
    }

    @Test
    @Tag("P2H")
    void shouldChooseLargeSizeWhenP2HIsSelected() {
        deliveryMethod.choose(DeliveryMethod.Type.D2D);
        parcelSize.choose(ParcelSize.Size.LARGE);
        assertTrue(parcelSize.isSelectedLargeParcel() && !parcelSize.isSelectedSmallParcel() && !parcelSize.isSelectedMediumParcel());
    }

}