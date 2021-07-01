package SzybkieNadania.Pages.MainPage.Components.SenderForm;

import SzybkieNadania.Pages.MainPage.Components.DeliveryMethod;
import SzybkieNadania.Pages.MainPage.MainPage;
import SzybkieNadania.Utils.Base;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SenderFormTest extends Base {
    private static SenderForm senderForm;

    @BeforeAll
    static void staticSetup() {
        senderForm = new SenderForm(webDriver);
        (new DeliveryMethod(webDriver)).choose(DeliveryMethod.Type.APM);
    }

    @ParameterizedTest
    @EmptySource
    @CsvFileSource(resources = "/Name/invalidNameData.csv")
    void shouldDisplayErrorWhenInvalidNameGiven(String name) {
        senderForm.clearName();
        senderForm.enterName(name);

        (new MainPage(webDriver)).submit();

        assertTrue(senderForm.getNameError());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Name/correctNameData.csv")
    void shouldNameBeCorrectWhenCorrectNameGiven(String name) {
        senderForm.clearName();
        senderForm.enterName(name);

        (new MainPage(webDriver)).submit();

        assertThrows(NoSuchElementException.class, () -> senderForm.getNameError());
    }

    @ParameterizedTest
    @EmptySource
    @CsvFileSource(resources = "/Email/invalidEmailData.csv")
    void shouldDisplayErrorWhenInvalidEmailGiven(String email) {
        senderForm.clearEmail();
        senderForm.enterEmail(email);

        (new MainPage(webDriver)).submit();

        assertTrue(senderForm.getEmailError());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Email/correctEmailData.csv")
    void shouldEmailBeCorrectWhenCorrectEmailGiven(String email) {
        senderForm.clearEmail();
        senderForm.enterEmail(email);

        (new MainPage(webDriver)).submit();

        assertThrows(NoSuchElementException.class, () -> senderForm.getEmailError());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/PhoneNumber/invalidPhoneNumberData.csv")
    void shouldDisplayErrorWhenInvalidPhoneNumberGiven(String phoneNumber) {
        senderForm.clearPhoneNumber();
        senderForm.enterPhoneNumber(phoneNumber);

        (new MainPage(webDriver)).submit();

        assertTrue(senderForm.getPhoneNumberError());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/PhoneNumber/correctPhoneNumberData.csv")
    void shouldPhoneNumberBeCorrectWhenCorrectPhoneNumberGiven(String phoneNumber) {
        senderForm.clearPhoneNumber();
        senderForm.enterPhoneNumber(phoneNumber);

        (new MainPage(webDriver)).submit();

        assertThrows(NoSuchElementException.class, () -> senderForm.getPhoneNumberError());
    }

}
