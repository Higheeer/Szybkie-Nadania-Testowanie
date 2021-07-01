package SzybkieNadania.Pages.MainPage.Components.RecipientForm;

import SzybkieNadania.Pages.MainPage.Components.DeliveryMethod;
import SzybkieNadania.Pages.MainPage.MainPage;
import SzybkieNadania.Utils.Base;
import SzybkieNadania.Utils.PageAction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
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
            recipientAPM.clearName();
            recipientAPM.enterName(name);

            new Actions(webDriver).click().perform();

            assertTrue(recipientAPM.getNameError());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Name/correctNameData.csv")
        void shouldNameBeCorrectWhenCorrectNameGiven(String name) {
            recipientAPM.clearName();
            recipientAPM.enterName(name);

            new Actions(webDriver).click().perform();

            assertThrows(NoSuchElementException.class, () -> recipientAPM.getNameError());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Email/invalidEmailData.csv")
        void shouldDisplayErrorWhenInvalidEmailGiven(String email) {
            recipientAPM.clearEmail();
            recipientAPM.enterEmail(email);

            new Actions(webDriver).click().perform();

            assertTrue(recipientAPM.getEmailError());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Email/correctEmailData.csv")
        void shouldEmailBeCorrectWhenCorrectEmailGiven(String email) {
            recipientAPM.clearEmail();
            recipientAPM.enterEmail(email);

            new Actions(webDriver).click().perform();

            assertThrows(NoSuchElementException.class, () -> recipientAPM.getEmailError());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/PhoneNumber/invalidPhoneNumberData.csv")
        void shouldDisplayErrorWhenInvalidPhoneNumberGiven(String phoneNumber) {
            recipientAPM.clearPhoneNumber();
            recipientAPM.enterPhoneNumber(phoneNumber);

            new Actions(webDriver).click().perform();

            assertTrue(recipientAPM.getPhoneNumberError());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/PhoneNumber/correctPhoneNumberData.csv")
        void shouldPhoneNumberBeCorrectWhenCorrectPhoneNumberGiven(String phoneNumber) {
            recipientAPM.clearPhoneNumber();
            recipientAPM.enterPhoneNumber(phoneNumber);

            new Actions(webDriver).click().perform();

            assertThrows(NoSuchElementException.class, () -> recipientAPM.getPhoneNumberError());
        }

        @ParameterizedTest
        @EmptySource
        @ValueSource(strings = {"dawdawda"})
        void shouldDisplayErrorWhenInvalidParcelLockerGiven(String parcelLocker) {
            recipientAPM.clearParcelLocker();

            new Actions(webDriver).click().perform();

            assertThrows(TimeoutException.class, () ->
                    recipientAPM.enterParcelLocker(parcelLocker));
        }

        @ParameterizedTest
        @ValueSource(strings = {"POP-ZYA1"})
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
            recipientD2D.clearName();
            recipientD2D.enterName(name);

            new Actions(webDriver).click().perform();

            assertTrue(recipientD2D.getNameError());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Name/correctNameData.csv")
        void shouldNameBeCorrectWhenCorrectNameGiven(String name) {
            recipientD2D.clearName();
            recipientD2D.enterName(name);

            new Actions(webDriver).click().perform();

            assertThrows(NoSuchElementException.class, () -> recipientD2D.getNameError());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Email/invalidEmailData.csv")
        void shouldDisplayErrorWhenInvalidEmailGiven(String email) {
            recipientD2D.clearEmail();
            recipientD2D.enterEmail(email);

            new Actions(webDriver).click().perform();

            assertTrue(recipientD2D.getEmailError());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Email/correctEmailData.csv")
        void shouldEmailBeCorrectWhenCorrectEmailGiven(String email) {
            recipientD2D.clearEmail();
            recipientD2D.enterEmail(email);

            new Actions(webDriver).click().perform();

            assertThrows(NoSuchElementException.class, () -> recipientD2D.getEmailError());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/PhoneNumber/invalidPhoneNumberData.csv")
        void shouldDisplayErrorWhenInvalidPhoneNumberGiven(String phoneNumber) {
            recipientD2D.clearPhoneNumber();
            recipientD2D.enterPhoneNumber(phoneNumber);

            new Actions(webDriver).click().perform();

            assertAll(() -> {
                assertTrue(recipientD2D.getPhoneNumberError());
                assertDoesNotThrow(() -> recipientD2D.getPhoneNumberError());
            });
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/PhoneNumber/correctPhoneNumberData.csv")
        void shouldPhoneNumberBeCorrectWhenCorrectPhoneNumberGiven(String phoneNumber) {
            recipientD2D.clearPhoneNumber();
            recipientD2D.enterPhoneNumber(phoneNumber);

            new Actions(webDriver).click().perform();

            assertThrows(NoSuchElementException.class, () -> recipientD2D.getPhoneNumberError());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidAddressData.csv")
        void shouldDisplayErrorWhenInvalidZipCodeGiven(String zipCode) {
            recipientD2D.clearZipCode();
            recipientD2D.enterZipCode(zipCode);

            new Actions(webDriver).click().perform();

            assertAll(() -> {
                assertTrue(recipientD2D.getZipCodeError());
                assertDoesNotThrow(() -> recipientD2D.getZipCodeError());
            });
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctAddressData.csv")
        void shouldZipCodeBeCorrectWhenCorrectZipCodeGiven(String zipCode) {
            recipientD2D.clearZipCode();
            recipientD2D.enterZipCode(zipCode);

            new Actions(webDriver).click().perform();

            assertThrows(NoSuchElementException.class, () -> recipientD2D.getZipCodeError());
        }


        @ParameterizedTest
        @EmptySource
        @ValueSource(strings = {"dawdawdaddawdawdaddawdawdaddawdawdaddawdawdaddawddd", "?!?!?!?!?!?@#@#3$%$@"})
        void shouldDisplayErrorWhenInvalidTownGiven(String town) {
            recipientD2D.clearZipCode();
            recipientD2D.enterZipCode("96-320");

            recipientD2D.clearTown();
            recipientD2D.enterTown(town);

            new Actions(webDriver).click().perform();

            assertTrue(recipientD2D.getTownError());
        }

        @ParameterizedTest
        @ValueSource(strings = {"Mszczonów", "Adamowice"})
        void shouldTownBeCorrectWhenCorrectTownGiven(String town) {
            recipientD2D.clearZipCode();
            recipientD2D.enterZipCode("96-320");

            recipientD2D.clearTown();
            recipientD2D.enterTown(town);

            new Actions(webDriver).click().perform();

            assertEquals(town, recipientD2D.getTown());
        }

        @ParameterizedTest
        @EmptySource
        @ValueSource(strings = {"dawdawdaddawdawdaddawdawdaddawdawdaddawdawdaddawddd", "!@!#@$%&*&*#$&*$#(@)"})
        void shouldDisplayErrorWhenInvalidStreetGiven(String street) {
            recipientD2D.clearZipCode();
            recipientD2D.enterZipCode("96-320");
            recipientD2D.clearTown();
            recipientD2D.enterTown("Mszczonów");

            recipientD2D.clearStreet();
            recipientD2D.enterStreet(street);

            new Actions(webDriver).click().perform();

            assertTrue(recipientD2D.getStreetError());
        }

        @ParameterizedTest
        @ValueSource(strings = {"3-Maja", "Andersa", "Bagno"})
        void shouldStreetBeCorrectWhenCorrectStreetGiven(String street) {
            recipientD2D.clearZipCode();
            recipientD2D.enterZipCode("96-320");
            recipientD2D.clearTown();
            recipientD2D.enterTown("Mszczonów");

            recipientD2D.clearStreet();
            recipientD2D.enterStreet(street);

            new Actions(webDriver).click().perform();

            assertEquals(street, recipientD2D.getStreet());
        }

        @ParameterizedTest
        @EmptySource
        @ValueSource(strings = {"12312312311", " "})
        void shouldDisplayErrorWhenInvalidBuildingNumberGiven(String buildingNumber) {
            recipientD2D.clearBuildingNumber();
            recipientD2D.enterBuildingNumber(buildingNumber);

            new Actions(webDriver).click().perform();

            assertTrue(recipientD2D.getBuildingNumberError());
        }

        @ParameterizedTest
        @ValueSource(strings = {"1231231231", "0"})
        void shouldBuildingNumberBeCorrectWhenCorrectBuildingNumberGiven(String buildingNumber) {
            recipientD2D.clearBuildingNumber();
            recipientD2D.enterBuildingNumber(buildingNumber);

            new Actions(webDriver).click().perform();

            assertThrows(NoSuchElementException.class, () -> recipientD2D.getBuildingNumberError());
        }

        @ParameterizedTest
        @EmptySource
        @ValueSource(strings = {"12312312311"})
        void shouldDisplayErrorWhenInvalidFlatNumberGiven(String flatNumber) {
            recipientD2D.clearFlatNumber();
            recipientD2D.enterFlatNumber(flatNumber);

            new Actions(webDriver).click().perform();

            assertTrue(recipientD2D.getFlatNumberError());
        }

        @ParameterizedTest
        @ValueSource(strings = {"1231231231", "0", " "})
        void shouldFlatNumberBeCorrectWhenCorrectFlatNumberGiven(String flatNumber) {
            recipientD2D.clearFlatNumber();
            recipientD2D.enterFlatNumber(flatNumber);


            assertThrows(NoSuchElementException.class, () -> recipientD2D.getFlatNumberError());
        }

        @ParameterizedTest
        @EmptySource
        @ValueSource(strings = {"awdawdawdawdawdwadwadawdawdawdwaddawd", " "})
        void shouldDisplayErrorWhenInvalidExtraCommentGiven(String extraComment) {
            recipientD2D.clearExtraComment();
            recipientD2D.enterExtraComment(extraComment);

            new Actions(webDriver).click().perform();

            assertTrue(recipientD2D.getExtraCommentError());
        }

        @ParameterizedTest
        @ValueSource(strings = {"awdawdawdawdawdwadwadawdawdawdwaddaw", "d"})
        void shouldExtraCommentBeCorrectWhenCorrectExtraCommentGiven(String extraComment) {
            recipientD2D.clearExtraComment();
            recipientD2D.enterExtraComment(extraComment);

            new Actions(webDriver).click().perform();

            assertThrows(NoSuchElementException.class, () -> recipientD2D.getExtraCommentError());
        }
    }
}
