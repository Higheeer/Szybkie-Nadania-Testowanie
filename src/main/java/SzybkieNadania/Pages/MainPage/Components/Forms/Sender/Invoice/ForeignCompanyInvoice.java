package SzybkieNadania.Pages.MainPage.Components.Forms.Sender.Invoice;

import SzybkieNadania.Utils.ElementWrappers.ComboBox;
import SzybkieNadania.Utils.ElementWrappers.InputField;
import SzybkieNadania.Utils.PageAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForeignCompanyInvoice implements Invoice {
    private final WebDriver webDriver;

    @FindAll({
            @FindBy(xpath = "//app-section[24]/div/app-input/div/div/div/app-complex-select/ng-select/div/div"),
            @FindBy(xpath = "//app-section[19]/div/app-input/div/div/div/app-complex-select/ng-select/div/div")
    })
    private WebElement nipPrefixInput;

    @FindBy(name = "invoice.foreignCompany.nip")
    private WebElement nipInput;

    @FindBy(name = "invoice.foreignCompany.companyName")
    private WebElement companyNameInput;

    @FindAll({
            @FindBy(xpath = "//app-section[22]/div/app-input/div/div/div/app-complex-select/ng-select/div/div"),
            @FindBy(xpath = "//app-section[27]/div/app-input/div/div/div/app-complex-select/ng-select/div/div")
    })
    private WebElement countryInput;

    @FindBy(name = "invoice.foreignCompany.zipCode")
    private WebElement zipCodeInput;

    @FindBy(name = "invoice.foreignCompany.town")
    private WebElement townInput;

    @FindBy(name = "invoice.foreignCompany.street")
    private WebElement streetInput;

    @FindBy(name = "invoice.foreignCompany.buildingNo")
    private WebElement buildingNumberInput;

    @FindBy(name = "invoice.foreignCompany.flatNo")
    private WebElement flatNumberInput;

    @FindBy(name = "invoice.foreignCompany.email")
    private WebElement emailInput;

    public ForeignCompanyInvoice(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    /**
     * @param values NipPrefix, Nip, CompanyName, Country, ZipCode, Town, Street, BuildingNumber, FlatNumber, Email
     */
    @Override
    public void fill(String... values) {
        if (values.length < 9)
            return;

        nipPrefix().fill(values[0]);
        nip().fill(values[1]);
        companyName().fill(values[2]);
        country().fill(values[3]);
        PageAction.wait(500);
        zipCode().fill(values[4]);
        town().fill(values[5]);
        street().fill(values[6]);
        buildingNumber().fill(values[7]);
        flatNumber().fill(values[8]);
        email().fill(values[9]);
    }

    protected ComboBox nipPrefix() {
        return new ComboBox(nipPrefixInput, webDriver);
    }

    protected InputField nip() {
        return new InputField(nipInput);
    }

    protected InputField companyName() {
        return new InputField(companyNameInput);
    }

    protected ComboBox country() {
        return new ComboBox(countryInput, webDriver);
    }

    protected InputField zipCode() {
        return new InputField(zipCodeInput);
    }

    protected InputField town() {
        return new InputField(townInput);
    }

    protected InputField street() {
        return new InputField(streetInput);
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
