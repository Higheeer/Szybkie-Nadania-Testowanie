package SzybkieNadania.Pages.MainPage.Components.SenderForm;

import SzybkieNadania.Pages.MainPage.MainPage;
import SzybkieNadania.Utils.Base;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EmptySource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SenderFormTest extends Base {
    private static SenderForm senderForm;

    @BeforeAll
    static void staticSetup() {
        senderForm = new SenderForm(webDriver);
    }

    @ParameterizedTest
    @EmptySource
    @CsvFileSource(resources = "/Name/invalidNameData.csv")
    void shouldDisplayErrorWhenInvalidNameGiven(String name) {
        senderForm.name().fill(name);

        (new MainPage(webDriver)).submit();

        assertTrue(senderForm.name().error());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Name/correctNameData.csv")
    void shouldNameBeCorrectWhenCorrectNameGiven(String name) {
        senderForm.name().fill(name);

        (new MainPage(webDriver)).submit();

        assertFalse(senderForm.name().error());
    }

    @ParameterizedTest
    @EmptySource
    @CsvFileSource(resources = "/Email/invalidEmailData.csv")
    void shouldDisplayErrorWhenInvalidEmailGiven(String email) {
        senderForm.email().fill(email);

        (new MainPage(webDriver)).submit();

        assertTrue(senderForm.email().error());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Email/correctEmailData.csv")
    void shouldEmailBeCorrectWhenCorrectEmailGiven(String email) {
        senderForm.email().fill(email);

        (new MainPage(webDriver)).submit();

        assertFalse(senderForm.email().error());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/PhoneNumber/invalidPhoneNumberData.csv")
    void shouldDisplayErrorWhenInvalidPhoneNumberGiven(String phoneNumber) {
        senderForm.phoneNumber().fill(phoneNumber);

        (new MainPage(webDriver)).submit();

        assertTrue(senderForm.phoneNumber().error());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/PhoneNumber/correctPhoneNumberData.csv")
    void shouldPhoneNumberBeCorrectWhenCorrectPhoneNumberGiven(String phoneNumber) {
        senderForm.phoneNumber().fill(phoneNumber);

        (new MainPage(webDriver)).submit();

        assertFalse(senderForm.phoneNumber().error());
    }

}
