package SzybkieNadania.Pages.MainPage.Components.Forms.Recipient;

import SzybkieNadania.Utils.ElementWrappers.InputField;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class RecipientForm {
    protected WebDriver webDriver;

    @FindBy(name = "targetAddress.name")
    protected WebElement nameInput;

    @FindBy(name = "addresseeEmail")
    protected WebElement emailInput;

    @FindBy(name = "phoneNumber")
    protected WebElement phoneNumberInput;

    public RecipientForm(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    public abstract void fill(String... values);

    protected InputField name() {
        return new InputField(nameInput);
    }

    protected InputField email() {
        return new InputField(emailInput);
    }

    protected InputField phoneNumber() {
        return new InputField(phoneNumberInput);
    }
}
