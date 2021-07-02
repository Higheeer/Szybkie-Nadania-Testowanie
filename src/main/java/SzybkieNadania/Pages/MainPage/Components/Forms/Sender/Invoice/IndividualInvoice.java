package SzybkieNadania.Pages.MainPage.Components.Forms.Sender.Invoice;

import SzybkieNadania.Utils.ElementWrappers.ComboBox;
import SzybkieNadania.Utils.ElementWrappers.InputField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndividualInvoice implements Invoice {
    private final WebDriver webDriver;

    @FindBy(name = "invoice.individual.companyName")
    private WebElement nameInput;

    @FindBy(name = "invoice.individual.email")
    private WebElement emailInput;

    @FindBy(name = "invoice.individual.zipCode")
    private WebElement zipCodeInput;

    @FindBy(xpath = "//*[@name='invoice.individual.town']/div/div")
    private WebElement townInput;

    @FindBy(xpath = "//*[@name='invoice.individual.street']/div/div")
    private WebElement streetInput;

    @FindBy(name = "invoice.individual.buildingNo")
    private WebElement buildingNumberInput;

    @FindBy(name = "invoice.individual.flatNo")
    private WebElement flatNumberInput;

    @FindBy(tagName = "app-form-button")
    private WebElement copySenderDataButton;

    public IndividualInvoice(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    /**
     * @param values Name, Email ,ZipCode, Town, Street, BuildingNumber, FlatNumber
     */
    @Override
    public void fill(String... values) {
        if (values.length < 7)
            return;

        name().fill(values[0]);
        email().fill(values[1]);
        zipCode().fill(values[2]);
        town().fill(values[3]);
        street().fill(values[4]);
        buildingNumber().fill(values[5]);
        flatNumber().fill(values[6]);
    }

    public boolean copySenderData() {
        copySenderDataButton.click();
        return nameInput.getAttribute("value").equals(webDriver.findElement(By.name("senderAddress.name")).getAttribute("value")) &&
                emailInput.getAttribute("value").equals(webDriver.findElement(By.name("senderAddress.email")).getAttribute("value"));

    }

    protected InputField name() {
        return new InputField(nameInput);
    }

    protected InputField email() {
        return new InputField(emailInput);
    }

    protected InputField zipCode() {
        return new InputField(zipCodeInput);
    }

    protected ComboBox town() {
        return new ComboBox(townInput, webDriver);
    }

    protected ComboBox street() {
        return new ComboBox(streetInput, webDriver);
    }

    protected InputField buildingNumber() {
        return new InputField(buildingNumberInput);
    }

    protected InputField flatNumber() {
        return new InputField(flatNumberInput);
    }
}
