package SzybkieNadania.Pages.MainPage.Components.SenderForm.Invoice;

import SzybkieNadania.Utils.ElementWrappers.ComboBox;
import SzybkieNadania.Utils.ElementWrappers.InputField;
import SzybkieNadania.Utils.PageAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompanyInvoice implements Invoice {
    private final WebDriver webDriver;

    @FindBy(name = "invoice.company.nip")
    private WebElement nipInput;

    @FindBy(name = "invoice.company.companyName")
    private WebElement companyNameInput;

    @FindBy(name = "invoice.company.zipCode")
    private WebElement zipCodeInput;

    @FindBy(xpath = "//*[@name='invoice.company.town']/div/div")
    private WebElement townInput;

    @FindBy(xpath = "//*[@name='invoice.company.street']/div/div")
    private WebElement streetInput;

    @FindBy(name = "invoice.company.buildingNo")
    private WebElement buildingNumberInput;

    @FindBy(name = "invoice.company.flatNo")
    private WebElement flatNumberInput;

    @FindBy(name = "invoice.company.email")
    private WebElement emailInput;

    public CompanyInvoice(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    /**
     * @param values Nip
     */
    @Override
    public void fill(String... values) {
        if (values.length == 1) {
            nip().fill(values[0]);
            PageAction.wait(3000);
        }
    }

    protected InputField nip() {
        return new InputField(nipInput);
    }

    protected InputField companyName() {
        return new InputField(companyNameInput);
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

    protected InputField email() {
        return new InputField(emailInput);
    }
}
