package SzybkieNadania.Pages.MainPage.Components.SenderForm.Invoice;

import SzybkieNadania.Pages.MainPage.Components.SenderForm.SenderForm;
import SzybkieNadania.Utils.Base;
import SzybkieNadania.Utils.PageAction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.*;

public class InvoiceTest extends Base {
    @BeforeAll
    static void staticSetup() {
        PageAction.checkCheckBox("Chcę otrzymać fakturę", webDriver);
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class Company {
        private CompanyInvoice companyInvoice;

        @BeforeAll
        void staticSetup() {
            PageAction.checkCheckBox("Firma w Polsce", webDriver);
            companyInvoice = new CompanyInvoice(webDriver);
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/NIP/invalidNipData.csv")
        void shouldDisplayErrorWhenInvalidNipGiven(String nip) {
            companyInvoice.clearNip();
            companyInvoice.enterNip(nip);

            new Actions(webDriver).click().perform();

            assertAll(() -> {
                assertDoesNotThrow(() -> companyInvoice.getNipError());
                assertTrue(companyInvoice.getNipError());
            });
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/NIP/correctNipData.csv")
        void shouldNipBeCorrectWhenCorrectNipGivenAndFillOtherFields(String nip, String expectedCompanyName, String expectedZipCode, String expectedTown, String expectedStreet, String expectedBuildingNumber) {
            companyInvoice.clearNip();
            companyInvoice.enterNip(nip.trim());

            new Actions(webDriver).click().perform();

            PageAction.wait(1000);

            assertAll(() ->
            {
                assertEquals(expectedCompanyName, companyInvoice.getCompanyName());
                assertEquals(expectedZipCode, companyInvoice.getZipCode());
                assertEquals(expectedTown, companyInvoice.getTown());
                assertEquals(expectedStreet, companyInvoice.getStreet());
                assertEquals(expectedBuildingNumber, companyInvoice.getBuildingNumber());
            });
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Name/invalidNameData.csv")
        void shouldDisplayErrorWhenInvalidCompanyNameGiven(String name) {
            companyInvoice.clearCompanyName();
            companyInvoice.enterCompanyName(name);

            new Actions(webDriver).click().perform();

            assertTrue(companyInvoice.getCompanyNameError());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Name/correctNameData.csv")
        void shouldCompanyNameBeCorrectWhenCorrectCompanyNameGiven(String name) {
            companyInvoice.clearCompanyName();
            companyInvoice.enterCompanyName(name);

            new Actions(webDriver).click().perform();

            assertThrows(NoSuchElementException.class, () -> companyInvoice.getCompanyNameError());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidZipCodeData.csv")
        void shouldDisplayErrorWhenInvalidZipCodeGiven(String zipCode) {
            companyInvoice.clearZipCode();
            companyInvoice.enterZipCode(zipCode);

            new Actions(webDriver).click().perform();

            assertAll(() -> {
                assertTrue(companyInvoice.getZipCodeError());
                assertDoesNotThrow(() -> companyInvoice.getZipCodeError());
            });
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctAddressData.csv")
        void shouldZipCodeBeCorrectWhenCorrectZipCodeGiven(String zipCode) {
            companyInvoice.clearZipCode();
            companyInvoice.enterZipCode(zipCode);

            new Actions(webDriver).click().perform();

            assertThrows(NoSuchElementException.class, () -> companyInvoice.getZipCodeError());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidTownData.csv")
        void shouldDisplayErrorWhenInvalidTownGiven(String town) {
            companyInvoice.clearZipCode();
            companyInvoice.enterZipCode("96-320");

            companyInvoice.clearTown();
            companyInvoice.enterTown(town);

            new Actions(webDriver).click().perform();

            assertTrue(companyInvoice.getTownError());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctAddressData.csv")
        void shouldTownBeCorrectWhenCorrectTownGiven(String zipCode, String town) {
            companyInvoice.clearZipCode();
            companyInvoice.enterZipCode(zipCode);

            companyInvoice.clearTown();
            companyInvoice.enterTown(town);

            new Actions(webDriver).click().perform();

            assertEquals(town, companyInvoice.getTown());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidStreetData.csv")
        void shouldDisplayErrorWhenInvalidStreetGiven(String street) {
            companyInvoice.clearZipCode();
            companyInvoice.enterZipCode("96-320");
            companyInvoice.clearTown();
            companyInvoice.enterTown("Mszczonów");

            companyInvoice.clearStreet();
            companyInvoice.enterStreet(street);

            new Actions(webDriver).click().perform();

            assertTrue(companyInvoice.getStreetError());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctAddressData.csv")
        void shouldStreetBeCorrectWhenCorrectStreetGiven(String zipCode, String town, String street) {
            companyInvoice.clearZipCode();
            companyInvoice.enterZipCode(zipCode);
            companyInvoice.clearTown();
            companyInvoice.enterTown(town);

            companyInvoice.clearStreet();
            companyInvoice.enterStreet(street);

            new Actions(webDriver).click().perform();

            assertEquals(street, companyInvoice.getStreet());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidNumberData.csv")
        void shouldDisplayErrorWhenInvalidBuildingNumberGiven(String buildingNumber) {
            companyInvoice.clearBuildingNumber();
            companyInvoice.enterBuildingNumber(buildingNumber);

            new Actions(webDriver).click().perform();

            assertTrue(companyInvoice.getBuildingNumberError());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctNumberData.csv")
        void shouldBuildingNumberBeCorrectWhenCorrectBuildingNumberGiven(String buildingNumber) {
            companyInvoice.clearBuildingNumber();
            companyInvoice.enterBuildingNumber(buildingNumber);

            new Actions(webDriver).click().perform();

            assertThrows(NoSuchElementException.class, () -> companyInvoice.getBuildingNumberError());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/invalidNumberData.csv")
        void shouldDisplayErrorWhenInvalidFlatNumberGiven(String flatNumber) {
            companyInvoice.clearFlatNumber();
            companyInvoice.enterFlatNumber(flatNumber);

            new Actions(webDriver).click().perform();

            assertTrue(companyInvoice.getFlatNumberError());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctNumberData.csv")
        void shouldFlatNumberBeCorrectWhenCorrectFlatNumberGiven(String flatNumber) {
            companyInvoice.clearFlatNumber();
            companyInvoice.enterFlatNumber(flatNumber);


            assertThrows(NoSuchElementException.class, () -> companyInvoice.getFlatNumberError());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Email/invalidEmailData.csv")
        void shouldDisplayErrorWhenInvalidEmailGiven(String email) {
            companyInvoice.clearEmail();
            companyInvoice.enterEmail(email);

            new Actions(webDriver).click().perform();

            assertTrue(companyInvoice.getEmailError());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Email/correctEmailData.csv")
        void shouldEmailBeCorrectWhenCorrectEmailGiven(String email) {
            companyInvoice.clearEmail();
            companyInvoice.enterEmail(email);

            new Actions(webDriver).click().perform();

            assertThrows(NoSuchElementException.class, () -> companyInvoice.getEmailError());
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class Individual {
        private IndividualInvoice individualInvoice;

        @BeforeAll
        void staticSetup() {
            PageAction.checkCheckBox("Osoba prywatna", webDriver);
            individualInvoice = new IndividualInvoice(webDriver);
        }

        @Test
        void shouldCopyAndFillCorrectlySenderNameAndEmailWhenCopyButtonClicked() {
            new SenderForm(webDriver).fill("Eryk", "różowaPantera69@gmail.com", "");

            assertTrue(individualInvoice.copySenderData());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Name/invalidNameData.csv")
        void shouldDisplayErrorWhenInvalidNameGiven(String name) {
            individualInvoice.clearName();
            individualInvoice.enterName(name);

            new Actions(webDriver).click().perform();

            assertTrue(individualInvoice.getNameError());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Name/correctNameData.csv")
        void shouldNameBeCorrectWhenCorrectNameGiven(String name) {
            individualInvoice.clearName();
            individualInvoice.enterName(name);

            new Actions(webDriver).click().perform();

            assertThrows(NoSuchElementException.class, () -> individualInvoice.getNameError());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Email/invalidEmailData.csv")
        void shouldDisplayErrorWhenInvalidEmailGiven(String email) {
            individualInvoice.clearEmail();
            individualInvoice.enterEmail(email);

            new Actions(webDriver).click().perform();

            assertTrue(individualInvoice.getEmailError());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Email/correctEmailData.csv")
        void shouldEmailBeCorrectWhenCorrectEmailGiven(String email) {
            individualInvoice.clearEmail();
            individualInvoice.enterEmail(email);

            new Actions(webDriver).click().perform();

            assertThrows(NoSuchElementException.class, () -> individualInvoice.getEmailError());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidZipCodeData.csv")
        void shouldDisplayErrorWhenInvalidZipCodeGiven(String zipCode) {
            individualInvoice.clearZipCode();
            individualInvoice.enterZipCode(zipCode);

            new Actions(webDriver).click().perform();

            assertAll(() -> {
                assertTrue(individualInvoice.getZipCodeError());
                assertDoesNotThrow(() -> individualInvoice.getZipCodeError());
            });
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctAddressData.csv")
        void shouldZipCodeBeCorrectWhenCorrectZipCodeGiven(String zipCode) {
            individualInvoice.clearZipCode();
            individualInvoice.enterZipCode(zipCode);

            new Actions(webDriver).click().perform();

            assertThrows(NoSuchElementException.class, () -> individualInvoice.getZipCodeError());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidTownData.csv")
        void shouldDisplayErrorWhenInvalidTownGiven(String town) {
            individualInvoice.clearZipCode();
            individualInvoice.enterZipCode("96-320");

            individualInvoice.clearTown();
            individualInvoice.enterTown(town);

            new Actions(webDriver).click().perform();

            assertTrue(individualInvoice.getTownError());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctAddressData.csv")
        void shouldTownBeCorrectWhenCorrectTownGiven(String zipCode, String town) {
            individualInvoice.clearZipCode();
            individualInvoice.enterZipCode(zipCode);

            individualInvoice.clearTown();
            individualInvoice.enterTown(town);

            new Actions(webDriver).click().perform();

            assertEquals(town, individualInvoice.getTown());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidStreetData.csv")
        void shouldDisplayErrorWhenInvalidStreetGiven(String street) {
            individualInvoice.clearZipCode();
            individualInvoice.enterZipCode("96-320");
            individualInvoice.clearTown();
            individualInvoice.enterTown("Mszczonów");

            individualInvoice.clearStreet();
            individualInvoice.enterStreet(street);

            new Actions(webDriver).click().perform();

            assertTrue(individualInvoice.getStreetError());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctAddressData.csv")
        void shouldStreetBeCorrectWhenCorrectStreetGiven(String zipCode, String town, String street) {
            individualInvoice.clearZipCode();
            individualInvoice.enterZipCode(zipCode);
            individualInvoice.clearTown();
            individualInvoice.enterTown(town);

            individualInvoice.clearStreet();
            individualInvoice.enterStreet(street);

            new Actions(webDriver).click().perform();

            assertEquals(street, individualInvoice.getStreet());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidNumberData.csv")
        void shouldDisplayErrorWhenInvalidBuildingNumberGiven(String buildingNumber) {
            individualInvoice.clearBuildingNumber();
            individualInvoice.enterBuildingNumber(buildingNumber);

            new Actions(webDriver).click().perform();

            assertTrue(individualInvoice.getBuildingNumberError());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctNumberData.csv")
        void shouldBuildingNumberBeCorrectWhenCorrectBuildingNumberGiven(String buildingNumber) {
            individualInvoice.clearBuildingNumber();
            individualInvoice.enterBuildingNumber(buildingNumber);

            new Actions(webDriver).click().perform();

            assertThrows(NoSuchElementException.class, () -> individualInvoice.getBuildingNumberError());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/invalidNumberData.csv")
        void shouldDisplayErrorWhenInvalidFlatNumberGiven(String flatNumber) {
            individualInvoice.clearFlatNumber();
            individualInvoice.enterFlatNumber(flatNumber);

            new Actions(webDriver).click().perform();

            assertTrue(individualInvoice.getFlatNumberError());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctNumberData.csv")
        void shouldFlatNumberBeCorrectWhenCorrectFlatNumberGiven(String flatNumber) {
            individualInvoice.clearFlatNumber();
            individualInvoice.enterFlatNumber(flatNumber);


            assertThrows(NoSuchElementException.class, () -> individualInvoice.getFlatNumberError());
        }

    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ForeignCompany {

    }
}
