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
// TODO
        void shouldDisplayErrorWhenInvalidNipGiven(String nip) {
            companyInvoice.nip().fill(nip);

            new Actions(webDriver).click().perform();

            assertTrue(companyInvoice.nip().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/NIP/correctNipData.csv")
        void shouldNipBeCorrectWhenCorrectNipGivenAndFillOtherFields(String nip, String expectedCompanyName, String expectedZipCode, String expectedTown, String expectedStreet, String expectedBuildingNumber) {
            companyInvoice.nip().fill(nip);

            new Actions(webDriver).click().perform();

            PageAction.wait(1000);

            assertAll(() ->
            {
                assertEquals(expectedCompanyName, companyInvoice.companyName().value());
                assertEquals(expectedZipCode, companyInvoice.zipCode().value());
                assertEquals(expectedTown, companyInvoice.town().value());
                assertEquals(expectedStreet, companyInvoice.street().value());
                assertEquals(expectedBuildingNumber, companyInvoice.buildingNumber().value());
            });
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Name/invalidNameData.csv")
        void shouldDisplayErrorWhenInvalidCompanyNameGiven(String name) {
            companyInvoice.companyName().fill(name);

            new Actions(webDriver).click().perform();

            assertTrue(companyInvoice.companyName().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Name/correctNameData.csv")
        void shouldCompanyNameBeCorrectWhenCorrectCompanyNameGiven(String name) {
            companyInvoice.companyName().fill(name);

            new Actions(webDriver).click().perform();

            assertFalse(companyInvoice.companyName().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidZipCodeData.csv")
        void shouldDisplayErrorWhenInvalidZipCodeGiven(String zipCode) {
            companyInvoice.zipCode().fill(zipCode);

            new Actions(webDriver).click().perform();

            assertTrue(companyInvoice.zipCode().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctAddressData.csv")
        void shouldZipCodeBeCorrectWhenCorrectZipCodeGiven(String zipCode) {
            companyInvoice.companyName().fill(zipCode);

            new Actions(webDriver).click().perform();

            assertFalse(companyInvoice.companyName().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidTownData.csv")
        void shouldDisplayErrorWhenInvalidTownGiven(String town) {
            companyInvoice.zipCode().fill("96-320");
            companyInvoice.town().fill(town);

            new Actions(webDriver).click().perform();

            assertTrue(companyInvoice.town().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctAddressData.csv")
        void shouldTownBeCorrectWhenCorrectTownGiven(String zipCode, String town) {
            companyInvoice.zipCode().fill(zipCode);
            companyInvoice.town().fill(town);

            new Actions(webDriver).click().perform();

            assertFalse(companyInvoice.town().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidStreetData.csv")
        void shouldDisplayErrorWhenInvalidStreetGiven(String street) {
            companyInvoice.zipCode().fill("96-320");
            companyInvoice.town().fill("Mszczonów");

            companyInvoice.street().fill(street);

            new Actions(webDriver).click().perform();

            assertTrue(companyInvoice.street().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctAddressData.csv")
        void shouldStreetBeCorrectWhenCorrectStreetGiven(String zipCode, String town, String street) {
            companyInvoice.zipCode().fill(zipCode);
            companyInvoice.town().fill(town);

            companyInvoice.street().fill(street);

            new Actions(webDriver).click().perform();

            assertFalse(companyInvoice.street().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidNumberData.csv")
        void shouldDisplayErrorWhenInvalidBuildingNumberGiven(String buildingNumber) {
            companyInvoice.buildingNumber().fill(buildingNumber);

            new Actions(webDriver).click().perform();

            assertTrue(companyInvoice.buildingNumber().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctNumberData.csv")
        void shouldBuildingNumberBeCorrectWhenCorrectBuildingNumberGiven(String buildingNumber) {
            companyInvoice.buildingNumber().fill(buildingNumber);

            new Actions(webDriver).click().perform();

            assertFalse(companyInvoice.buildingNumber().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/invalidNumberData.csv")
        void shouldDisplayErrorWhenInvalidFlatNumberGiven(String flatNumber) {
            companyInvoice.flatNumber().fill(flatNumber);

            new Actions(webDriver).click().perform();

            assertTrue(companyInvoice.flatNumber().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctNumberData.csv")
        void shouldFlatNumberBeCorrectWhenCorrectFlatNumberGiven(String flatNumber) {
            companyInvoice.flatNumber().fill(flatNumber);

            new Actions(webDriver).click().perform();

            assertFalse(companyInvoice.flatNumber().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Email/invalidEmailData.csv")
        void shouldDisplayErrorWhenInvalidEmailGiven(String email) {
            companyInvoice.email().fill(email);

            new Actions(webDriver).click().perform();

            assertTrue(companyInvoice.email().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Email/correctEmailData.csv")
        void shouldEmailBeCorrectWhenCorrectEmailGiven(String email) {
            companyInvoice.email().fill(email);

            new Actions(webDriver).click().perform();

            assertFalse(companyInvoice.email().error());
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
            individualInvoice.name().fill(name);

            new Actions(webDriver).click().perform();

            assertTrue(individualInvoice.name().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Name/correctNameData.csv")
        void shouldNameBeCorrectWhenCorrectNameGiven(String name) {
            individualInvoice.name().fill(name);

            new Actions(webDriver).click().perform();

            assertFalse(individualInvoice.name().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Email/invalidEmailData.csv")
        void shouldDisplayErrorWhenInvalidEmailGiven(String email) {
            individualInvoice.email().fill(email);

            new Actions(webDriver).click().perform();

            assertTrue(individualInvoice.email().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Email/correctEmailData.csv")
        void shouldEmailBeCorrectWhenCorrectEmailGiven(String email) {
            individualInvoice.email().fill(email);

            new Actions(webDriver).click().perform();

            assertFalse(individualInvoice.email().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidZipCodeData.csv")
        void shouldDisplayErrorWhenInvalidZipCodeGiven(String zipCode) {
            individualInvoice.zipCode().fill(zipCode);

            new Actions(webDriver).click().perform();

            assertTrue(individualInvoice.zipCode().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctAddressData.csv")
        void shouldZipCodeBeCorrectWhenCorrectZipCodeGiven(String zipCode) {
            individualInvoice.zipCode().fill(zipCode);

            new Actions(webDriver).click().perform();

            assertFalse(individualInvoice.zipCode().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidTownData.csv")
        void shouldDisplayErrorWhenInvalidTownGiven(String town) {
            individualInvoice.zipCode().fill("96-320");

            individualInvoice.town().fill(town);

            new Actions(webDriver).click().perform();

            assertTrue(individualInvoice.town().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctAddressData.csv")
        void shouldTownBeCorrectWhenCorrectTownGiven(String zipCode, String town) {
            individualInvoice.zipCode().fill(zipCode);

            individualInvoice.town().fill(town);

            new Actions(webDriver).click().perform();

            System.out.println(individualInvoice.town().value());
            assertFalse(individualInvoice.town().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidStreetData.csv")
        void shouldDisplayErrorWhenInvalidStreetGiven(String street) {
            individualInvoice.zipCode().fill("96-320");
            individualInvoice.town().fill("Mszczonów");

            individualInvoice.street().fill(street);

            new Actions(webDriver).click().perform();

            assertTrue(individualInvoice.street().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctAddressData.csv")
        void shouldStreetBeCorrectWhenCorrectStreetGiven(String zipCode, String town, String street) {
            individualInvoice.zipCode().fill(zipCode);
            individualInvoice.town().fill(town);

            individualInvoice.street().fill(street);

            new Actions(webDriver).click().perform();

            assertFalse(individualInvoice.street().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidNumberData.csv")
        void shouldDisplayErrorWhenInvalidBuildingNumberGiven(String buildingNumber) {
            individualInvoice.buildingNumber().fill(buildingNumber);

            new Actions(webDriver).click().perform();

            assertTrue(individualInvoice.buildingNumber().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctNumberData.csv")
        void shouldBuildingNumberBeCorrectWhenCorrectBuildingNumberGiven(String buildingNumber) {
            individualInvoice.buildingNumber().fill(buildingNumber);

            new Actions(webDriver).click().perform();

            assertFalse(individualInvoice.buildingNumber().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/invalidNumberData.csv")
        void shouldDisplayErrorWhenInvalidFlatNumberGiven(String flatNumber) {
            individualInvoice.flatNumber().fill(flatNumber);

            new Actions(webDriver).click().perform();

            assertTrue(individualInvoice.flatNumber().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctNumberData.csv")
        void shouldFlatNumberBeCorrectWhenCorrectFlatNumberGiven(String flatNumber) {
            individualInvoice.flatNumber().fill(flatNumber);

            new Actions(webDriver).click().perform();

            assertFalse(individualInvoice.flatNumber().error());
        }

    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ForeignCompany {

    }
}
