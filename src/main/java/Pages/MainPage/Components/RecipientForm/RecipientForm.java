package Pages.MainPage.Components.RecipientForm;

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

    protected void enterName(String name) {
        nameInput.sendKeys(name);
    }

    protected void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    protected void enterPhoneNumber(String phoneNumber) {
        phoneNumberInput.sendKeys(phoneNumber);
    }
}
