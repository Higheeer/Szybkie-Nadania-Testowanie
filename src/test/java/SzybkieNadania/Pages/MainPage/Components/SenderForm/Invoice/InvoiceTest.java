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

            PageAction.wait(1500);

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
        private ForeignCompanyInvoice foreignCompanyInvoice;

        @BeforeAll
        void staticSetup() {
            PageAction.checkCheckBox("Firma za granicą", webDriver);
            foreignCompanyInvoice = new ForeignCompanyInvoice(webDriver);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/NIP/correctNipPrefixData.csv")
        void shouldNipPrefixBeCorrectWhenCorrectNipPrefixGiven(String nipPrefix) {
            foreignCompanyInvoice.nipPrefix().fill(nipPrefix);

            new Actions(webDriver).click().perform();

            assertFalse(foreignCompanyInvoice.nipPrefix().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/NIP/invalidNipData.csv")
        void shouldDisplayErrorWhenInvalidNipGiven(String nip) {
            foreignCompanyInvoice.nipPrefix().fill("AT");
            foreignCompanyInvoice.nip().fill(nip);

            new Actions(webDriver).click().perform();

            assertTrue(foreignCompanyInvoice.nip().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/NIP/correctNipPrefixData.csv")
        void shouldNipBeCorrectWhenCorrectNipGiven(String nipPrefix, String nip) {
            foreignCompanyInvoice.nipPrefix().fill(nipPrefix);
            foreignCompanyInvoice.nip().fill(nip);

            new Actions(webDriver).click().perform();

            assertFalse(foreignCompanyInvoice.nip().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Name/invalidNameData.csv")
        void shouldDisplayErrorWhenInvalidCompanyNameGiven(String name) {
            foreignCompanyInvoice.companyName().fill(name);

            new Actions(webDriver).click().perform();

            assertTrue(foreignCompanyInvoice.companyName().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Name/correctNameData.csv")
        void shouldCompanyNameBeCorrectWhenCorrectCompanyNameGiven(String name) {
            foreignCompanyInvoice.companyName().fill(name);

            new Actions(webDriver).click().perform();

            assertFalse(foreignCompanyInvoice.companyName().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/Foreign/invalidCountryData.csv")
        void shouldDisplayErrorWhenInvalidCountryGiven(String country) {
            foreignCompanyInvoice.country().fill(country);

            new Actions(webDriver).click().perform();

            assertTrue(foreignCompanyInvoice.country().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/Foreign/correctCountryData.csv")
        void shouldCountryBeCorrectWhenCorrectCountryGiven(String country) {
            foreignCompanyInvoice.country().fill(country);

            new Actions(webDriver).click().perform();

            assertFalse(foreignCompanyInvoice.country().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/Foreign/invalidZipCodeData.csv")
        void shouldZipCodeWhenInvalidZipCodeGiven(String zipCode) {
            foreignCompanyInvoice.zipCode().fill(zipCode);

            new Actions(webDriver).click().perform();

            assertTrue(foreignCompanyInvoice.zipCode().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/Foreign/correctZipCodeData.csv")
        void shouldZipCodeBeCorrectWhenCorrectZipCodeGiven(String zipCode) {
            foreignCompanyInvoice.zipCode().fill(zipCode);

            new Actions(webDriver).click().perform();

            assertFalse(foreignCompanyInvoice.zipCode().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/Foreign/invalidTownData.csv")
        void shouldDisplayErrorWhenInvalidTownGiven(String town) {
            foreignCompanyInvoice.town().fill(town);

            new Actions(webDriver).click().perform();

            assertTrue(foreignCompanyInvoice.town().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/Foreign/correctTownData.csv")
        void shouldTownBeCorrectWhenCorrectTownGiven(String town) {
            foreignCompanyInvoice.town().fill(town);

            new Actions(webDriver).click().perform();

            assertFalse(foreignCompanyInvoice.town().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/Foreign/invalidStreetData.csv")
        void shouldDisplayErrorWhenInvalidStreetGiven(String street) {
            foreignCompanyInvoice.street().fill(street);

            new Actions(webDriver).click().perform();

            assertTrue(foreignCompanyInvoice.street().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/Foreign/correctStreetData.csv")
        void shouldStreetBeCorrectWhenCorrectStreetGiven(String street) {
            foreignCompanyInvoice.street().fill(street);

            new Actions(webDriver).click().perform();

            assertFalse(foreignCompanyInvoice.street().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Address/invalidNumberData.csv")
        void shouldDisplayErrorWhenInvalidBuildingNumberGiven(String buildingNumber) {
            foreignCompanyInvoice.buildingNumber().fill(buildingNumber);

            new Actions(webDriver).click().perform();

            assertTrue(foreignCompanyInvoice.buildingNumber().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctNumberData.csv")
        void shouldBuildingNumberBeCorrectWhenCorrectBuildingNumberGiven(String buildingNumber) {
            foreignCompanyInvoice.buildingNumber().fill(buildingNumber);

            new Actions(webDriver).click().perform();

            assertFalse(foreignCompanyInvoice.buildingNumber().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/invalidNumberData.csv")
        void shouldDisplayErrorWhenInvalidFlatNumberGiven(String flatNumber) {
            foreignCompanyInvoice.flatNumber().fill(flatNumber);

            new Actions(webDriver).click().perform();

            assertTrue(foreignCompanyInvoice.flatNumber().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Address/correctNumberData.csv")
        void shouldFlatNumberBeCorrectWhenCorrectFlatNumberGiven(String flatNumber) {
            foreignCompanyInvoice.flatNumber().fill(flatNumber);

            new Actions(webDriver).click().perform();

            assertFalse(foreignCompanyInvoice.flatNumber().error());
        }

        @ParameterizedTest
        @EmptySource
        @CsvFileSource(resources = "/Email/invalidEmailData.csv")
        void shouldDisplayErrorWhenInvalidEmailGiven(String email) {
            foreignCompanyInvoice.email().fill(email);

            new Actions(webDriver).click().perform();

            assertTrue(foreignCompanyInvoice.email().error());
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/Email/correctEmailData.csv")
        void shouldEmailBeCorrectWhenCorrectEmailGiven(String email) {
            foreignCompanyInvoice.email().fill(email);

            new Actions(webDriver).click().perform();

            assertFalse(foreignCompanyInvoice.email().error());
        }

    }
}
