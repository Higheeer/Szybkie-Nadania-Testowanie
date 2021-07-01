package SzybkieNadania.Pages.MainPage.Components.SenderForm;

import SzybkieNadania.Pages.MainPage.Components.DeliveryMethod;
import SzybkieNadania.Pages.MainPage.MainPage;
import SzybkieNadania.Utils.Base;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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
    @ValueSource(strings = {" ", "$$$$$$$$$", "mpgPd8xJRWyURRL63wp2nVVebUyWVxgZGSGNxRHv56L4vqR9rjrt8e23XbjxbCzLutb4zJKbHfmWSHBNbYNeBtZVikGLgKQacXtx5YgmQrJ46RAWvLGRMPLwhNxeDTEUU4KZD3Sqn2Lixtt3FTH53L973P7thhevAXC4pi96iiTN9ukyCDwWKGDBkSv2pHETLb9GzySNBUV8K84XGqUg4aPn9pEt796PDm8U4GFVQyU4BmNZnhHjhumArQ7"})
    void shouldDisplayErrorWhenInvalidNameGiven(String name) {
        senderForm.clearName();
        senderForm.enterName(name);

        (new MainPage(webDriver)).submit();

        assertTrue(senderForm.getNameError());
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "6nhhpMGp2ZfeKcrEHmSGqrfuD9FePNfnDhkhwUqN8JgXhxTQPRASu9pzg9k9hKVJa7givefFBGkaG4XTnLn8SC4Z5egzLKkvLzHf8YFWaFXWuT8F6JnN5HrgmhqyEEfr7GF56hmnV5m2kS6NncNDFTpNCYwL6XimTUiparXrWgXeuxBjhhK6S2Fnzgtncwf7KgaTnVh962mcjjxwEhkh8Tq32tqBgtb6PgERduEzSWgxba2gJuUehbAy5i"})
    void shouldNameBeCorrectWhenCorrectNameGiven(String name) {
        senderForm.clearName();
        senderForm.enterName(name);

        (new MainPage(webDriver)).submit();

        assertThrows(NoSuchElementException.class, () -> senderForm.getNameError());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "aas", "trzy", "@wp.pl", "JucDHGbB95xQN9YDfReufb25EZLccymLN7FGEGxUrC5Nh5XAWRekjkg7iDyPeKeRCWirpNiQek7J4LG89LNRSCqPXzqKZ5tmy9cwijquD2pyFfwT3fzvFf4x2KbuxE6H9qVjuPiAHqfPkHt2NHNcmiXrhZYtUG67YnAzuUKPhcvkkTKDZ6CxrcF87CE4gRVHkmKSdn6vBFbxyhKLVFqc6kLMX3SjBmjDBGMhZBxTCfaZrn2cRg9eWzAukTcCVgPrB", "JucDHGbB95xQN9YDfReufb25EZLccymLN7FGEGxUrC5Nh5XAWRekjkg7iDyPeKeRCWirpNiQek7J4LG89LNRSCqPXzqKZ5tmy9cwijquD2pyFfwT3fzvFf4x2KbuxE6H9qVjuPiAHqfPkHt2NHNcmiXrhZYtUG67YnAzuUKPhcvkkTKDZ6CxrcF87CE4gRVHkmKSdn6vBFbxyhKLVFqc6kLMX3SjBmjDBGMhZBxTCfaZrn2cRg9eWzAukTc@wp.pl"})
    void shouldDisplayErrorWhenInvalidEmailGiven(String email) {
        senderForm.clearEmail();
        senderForm.enterEmail(email);

        (new MainPage(webDriver)).submit();

        assertTrue(senderForm.getEmailError());
    }

    @ParameterizedTest
    @ValueSource(strings = {"o@wp.pl", "47KwM8GmRFZqb6ENk5WCpcjbDDbVZR2EvUiQbS5GEQ4Eu@wp.pl"})
    void shouldEmailBeCorrectWhenCorrectEmailGiven(String email) {
        senderForm.clearEmail();
        senderForm.enterEmail(email);

        (new MainPage(webDriver)).submit();

        assertThrows(NoSuchElementException.class, () -> senderForm.getEmailError());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "000000000", "0"})
    void shouldDisplayErrorWhenInvalidPhoneNumberGiven(String phoneNumber) {
        senderForm.clearPhoneNumber();
        senderForm.enterPhoneNumber(phoneNumber);

        (new MainPage(webDriver)).submit();

        assertTrue(senderForm.getPhoneNumberError());
    }

    @ParameterizedTest
    @ValueSource(strings = {"123123123", "321321321"})
    void shouldPhoneNumberBeCorrectWhenCorrectPhoneNumberGiven(String phoneNumber) {
        senderForm.clearPhoneNumber();
        senderForm.enterPhoneNumber(phoneNumber);

        (new MainPage(webDriver)).submit();

        assertThrows(NoSuchElementException.class, () -> senderForm.getPhoneNumberError());
    }

}
