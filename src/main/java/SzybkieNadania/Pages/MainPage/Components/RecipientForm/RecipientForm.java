package SzybkieNadania.Pages.MainPage.Components.RecipientForm;

import SzybkieNadania.Utils.PageAction;
import org.openqa.selenium.By;
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
        PageAction.waitUntilClickableAndSendKeys(nameInput, name, webDriver);
    }

    protected void enterEmail(String email) {
        PageAction.waitUntilClickableAndSendKeys(emailInput, email, webDriver);
    }

    protected void enterPhoneNumber(String phoneNumber) {
        PageAction.waitUntilClickableAndSendKeys(phoneNumberInput, phoneNumber, webDriver);
    }

    protected String getName() {
        return nameInput.getAttribute("value");
    }

    protected String getEmail() {
        return emailInput.getAttribute("value");
    }


    protected String getPhoneNumber() {
        return phoneNumberInput.getAttribute("value");
    }

    protected boolean getNameError() {
        return webDriver.findElement(By.xpath("//*[@id='error-targetAddress.name']/following-sibling::small")).isDisplayed();
    }

    protected boolean getEmailError() {
        return webDriver.findElement(By.xpath("//*[@id='error-addresseeEmail']/following-sibling::small")).isDisplayed();
    }

    protected boolean getPhoneNumberError() {
        return webDriver.findElement(By.xpath("//*[@id='error-phoneNumber']/following-sibling::small")).isDisplayed();
    }

    protected void clearName() {
        nameInput.clear();
    }

    protected void clearEmail() {
        emailInput.clear();
    }

    protected void clearPhoneNumber() {
        phoneNumberInput.clear();
    }
}
