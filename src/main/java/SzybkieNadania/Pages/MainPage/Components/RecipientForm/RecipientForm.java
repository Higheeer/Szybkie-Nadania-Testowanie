package SzybkieNadania.Pages.MainPage.Components.RecipientForm;

import SzybkieNadania.Utils.PageAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class RecipientForm {
    protected WebDriver webDriver;

    @FindBy(name = "targetAddress.name")
    protected WebElement nameInput;
    @FindBy(id = "error-targetAddress.name")
    protected WebElement nameError;

    @FindBy(name = "addresseeEmail")
    protected WebElement emailInput;
    @FindBy(id = "error-addresseeEmail")
    protected WebElement emailError;

    @FindBy(name = "phoneNumber")
    protected WebElement phoneNumberInput;
    @FindBy(id = "error-phoneNumber")
    protected WebElement errorPhoneNumber;

    public RecipientForm(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    public abstract void fill(String... values);

    protected void enterName(String name) {
        PageAction.waitUntilClickableAndSendKeys(nameInput, name, webDriver);
    }

    protected String getName() {
        return nameInput.getAttribute("value");
    }

    protected void enterEmail(String email) {
        PageAction.waitUntilClickableAndSendKeys(emailInput, email, webDriver);
    }

    protected String getEmail() {
        return emailInput.getAttribute("value");
    }

    protected void enterPhoneNumber(String phoneNumber) {
        PageAction.waitUntilClickableAndSendKeys(phoneNumberInput, phoneNumber, webDriver);
    }

    protected String getPhoneNumber() {
        return phoneNumberInput.getAttribute("value");
    }
}
