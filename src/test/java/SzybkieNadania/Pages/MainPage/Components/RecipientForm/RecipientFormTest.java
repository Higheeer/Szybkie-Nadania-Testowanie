package SzybkieNadania.Pages.MainPage.Components.RecipientForm;

import SzybkieNadania.Pages.MainPage.Components.DeliveryMethod;
import SzybkieNadania.Utils.Base;
import SzybkieNadania.Utils.PageAction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.*;


public class RecipientFormTest extends Base {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class APM {
        private RecipientAPM recipientAPM;

        @BeforeAll
        void staticSetup() {
            (new DeliveryMethod(webDriver)).choose(DeliveryMethod.Type.APM);
            recipientAPM = new RecipientAPM(webDriver);
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Name/invalidNameData.csv")
        void shouldDisplayErrorWhenInvalidNameGiven(String name) {
            recipientAPM.name().fill(name);

            new Actions(webDriver).click().perform();

            assertTrue(recipientAPM.name().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Name/correctNameData.csv")
        void shouldNameBeCorrectWhenCorrectNameGiven(String name) {
            recipientAPM.name().fill(name);

            new Actions(webDriver).click().perform();

            assertFalse(recipientAPM.name().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Email/invalidEmailData.csv")
        void shouldDisplayErrorWhenInvalidEmailGiven(String email) {
            recipientAPM.email().fill(email);

            new Actions(webDriver).click().perform();

            assertTrue(recipientAPM.email().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Email/correctEmailData.csv")
        void shouldEmailBeCorrectWhenCorrectEmailGiven(String email) {
            recipientAPM.email().fill(email);

            new Actions(webDriver).click().perform();

            assertFalse(recipientAPM.email().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/PhoneNumber/invalidPhoneNumberData.csv")
        void shouldDisplayErrorWhenInvalidPhoneNumberGiven(String phoneNumber) {
            recipientAPM.phoneNumber().fill(phoneNumber);

            new Actions(webDriver).click().perform();

            assertTrue(recipientAPM.phoneNumber().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/PhoneNumber/correctPhoneNumberData.csv")
        void shouldPhoneNumberBeCorrectWhenCorrectPhoneNumberGiven(String phoneNumber) {
            recipientAPM.phoneNumber().fill(phoneNumber);

            new Actions(webDriver).click().perform();

            assertFalse(recipientAPM.phoneNumber().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/ParcelLocker/invalidParcelLockerData.csv")
        void shouldDisplayErrorWhenInvalidParcelLockerGiven(String parcelLocker) {
            recipientAPM.clearParcelLocker();

            new Actions(webDriver).click().perform();

            assertAll(() -> {
                assertThrows(TimeoutException.class, () ->
                        recipientAPM.enterParcelLocker(parcelLocker));
                assertTrue(recipientAPM.getParcelLockerError());
            });

        }

        @ParameterizedTest
        @CsvFileSource(resources = "/ParcelLocker/correctParcelLockerData.csv")
        void shouldParcelLockerBeCorrectWhenCorrectParcelLockerGiven(String parcelLocker) {
            recipientAPM.clearParcelLocker();
            recipientAPM.enterParcelLocker(parcelLocker);

            new Actions(webDriver).click().perform();

            assertThrows(NoSuchElementException.class, () -> recipientAPM.getParcelLockerError());
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class D2D {
        private RecipientD2D recipientD2D;

        @BeforeAll
        void staticSetup() {
            (new DeliveryMethod(webDriver)).choose(DeliveryMethod.Type.D2D);
            recipientD2D = new RecipientD2D(webDriver);
            PageAction.checkCheckBox("Chcę podać dodatkowe informacje", webDriver);
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Name/invalidNameData.csv")
        void shouldDisplayErrorWhenInvalidNameGiven(String name) {
            recipientD2D.name().fill(name);

            new Actions(webDriver).click().perform();

            assertTrue(recipientD2D.name().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Name/correctNameData.csv")
        void shouldNameBeCorrectWhenCorrectNameGiven(String name) {
            recipientD2D.name().fill(name);

            new Actions(webDriver).click().perform();

            assertFalse(recipientD2D.name().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Email/invalidEmailData.csv")
        void shouldDisplayErrorWhenInvalidEmailGiven(String email) {
            recipientD2D.email().fill(email);

            new Actions(webDriver).click().perform();

            assertTrue(recipientD2D.email().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Email/correctEmailData.csv")
        void shouldEmailBeCorrectWhenCorrectEmailGiven(String email) {
            recipientD2D.email().fill(email);

            new Actions(webDriver).click().perform();

            assertFalse(recipientD2D.email().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/PhoneNumber/invalidPhoneNumberData.csv")
        void shouldDisplayErrorWhenInvalidPhoneNumberGiven(String phoneNumber) {
            recipientD2D.phoneNumber().fill(phoneNumber);

            new Actions(webDriver).click().perform();

            assertTrue(recipientD2D.phoneNumber().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/PhoneNumber/correctPhoneNumberData.csv")
        void shouldPhoneNumberBeCorrectWhenCorrectPhoneNumberGiven(String phoneNumber) {
            recipientD2D.phoneNumber().fill(phoneNumber);

            new Actions(webDriver).click().perform();

            assertFalse(recipientD2D.phoneNumber().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidZipCodeData.csv")
        void shouldDisplayErrorWhenInvalidZipCodeGiven(String zipCode) {
            recipientD2D.zipCode().fill(zipCode);

            new Actions(webDriver).click().perform();

            assertTrue(recipientD2D.zipCode().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctAddressData.csv")
        void shouldZipCodeBeCorrectWhenCorrectZipCodeGiven(String zipCode) {
            recipientD2D.zipCode().fill("96-320");

            new Actions(webDriver).click().perform();

            assertFalse(recipientD2D.zipCode().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidTownData.csv")
        void shouldDisplayErrorWhenInvalidTownGiven(String town) {
            recipientD2D.zipCode().fill("96-320");
            recipientD2D.town().fill(town);

            new Actions(webDriver).click().perform();

            assertTrue(recipientD2D.town().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctAddressData.csv")
        void shouldTownBeCorrectWhenCorrectTownGiven(String zipCode, String town) {
            recipientD2D.zipCode().fill("96-320");
            recipientD2D.town().fill(town);

            new Actions(webDriver).click().perform();

            assertFalse(recipientD2D.town().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidStreetData.csv")
        void shouldDisplayErrorWhenInvalidStreetGiven(String street) {
            recipientD2D.zipCode().fill("96-320");
            recipientD2D.town().fill("Mszczonów");

            recipientD2D.street().fill(street);

            new Actions(webDriver).click().perform();

            assertTrue(recipientD2D.street().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctAddressData.csv")
        void shouldStreetBeCorrectWhenCorrectStreetGiven(String zipCode, String town, String street) {
            recipientD2D.zipCode().fill(zipCode);
            recipientD2D.town().fill(town);

            recipientD2D.street().fill(street);

            new Actions(webDriver).click().perform();

            assertFalse(recipientD2D.street().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidNumberData.csv")
        void shouldDisplayErrorWhenInvalidBuildingNumberGiven(String buildingNumber) {
            recipientD2D.buildingNumber().fill(buildingNumber);

            new Actions(webDriver).click().perform();

            assertTrue(recipientD2D.buildingNumber().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctNumberData.csv")
        void shouldBuildingNumberBeCorrectWhenCorrectBuildingNumberGiven(String buildingNumber) {
            recipientD2D.buildingNumber().fill(buildingNumber);

            new Actions(webDriver).click().perform();

            assertFalse(recipientD2D.buildingNumber().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/invalidNumberData.csv")
        void shouldDisplayErrorWhenInvalidFlatNumberGiven(String flatNumber) {
            recipientD2D.flatNumber().fill(flatNumber);

            new Actions(webDriver).click().perform();

            assertTrue(recipientD2D.flatNumber().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctNumberData.csv")
        void shouldFlatNumberBeCorrectWhenCorrectFlatNumberGiven(String flatNumber) {
            recipientD2D.flatNumber().fill(flatNumber);

            new Actions(webDriver).click().perform();

            assertFalse(recipientD2D.flatNumber().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/ExtraComment/invalidExtraCommentData.csv")
        void shouldDisplayErrorWhenInvalidExtraCommentGiven(String extraComment) {
            recipientD2D.extraComment().fill(extraComment);

            new Actions(webDriver).click().perform();

            assertTrue(recipientD2D.extraComment().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/ExtraComment/correctExtraCommentData.csv")
        void shouldExtraCommentBeCorrectWhenCorrectExtraCommentGiven(String extraComment) {
            recipientD2D.extraComment().fill(extraComment);

            new Actions(webDriver).click().perform();

            assertFalse(recipientD2D.extraComment().error());
        }
    }
}
