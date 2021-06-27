package Pages.MainPage.Components.SenderForm.Invoice;

import Pages.MainPage.Utils.PageAction;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForeignCompanyInvoice implements Invoice {
    private final WebDriver webDriver;

    @FindBy(css = "app-complex-select > ng-select > div > div > div.ng-input > input[type=text]")
    private WebElement nipPrefixInput;

    @FindBy(name = "invoice.foreignCompany.nip")
    private WebElement nipInput;

    @FindBy(name = "invoice.foreignCompany.companyName")
    private WebElement companyNameInput;

    @FindBy(css = "#parcelForm > div > div.col-md-6.col-lg-7.col-xl-8 > app-dynamic-form > form > app-section:nth-child(22) > div > app-input > div > div > div > app-complex-select > ng-select > div > div > div.ng-input > input[type=text]")
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

        enterNipPrefix(values[0]);
        enterNip(values[1]);
        enterCompanyName(values[2]);
        enterCountry(values[3]);
        PageAction.wait(200);
        enterZipCode(values[4]);
        enterTown(values[5]);
        enterStreet(values[6]);
        enterBuildingNumber(values[7]);
        enterFlatNumber(values[8]);
        enterEmail(values[9]);
    }

    protected void enterNipPrefix(String prefix) {
        nipPrefixInput.sendKeys(prefix);
        nipPrefixInput.sendKeys(Keys.ENTER);
    }

    protected void enterNip(String nip) {
        nipInput.sendKeys(nip);
    }

    protected void enterCompanyName(String name) {
        companyNameInput.sendKeys(name);
    }

    protected void enterCountry(String country) {
        countryInput.sendKeys(country);
        countryInput.sendKeys(Keys.ENTER);
    }

    protected void enterZipCode(String zipCode) {
        zipCodeInput.sendKeys(zipCode);
    }

    protected void enterTown(String town) {
        townInput.sendKeys(town);
    }

    protected void enterStreet(String street) {
        streetInput.sendKeys(street);
    }

    protected void enterBuildingNumber(String number) {
        buildingNumberInput.sendKeys(number);
    }

    protected void enterFlatNumber(String number) {
        flatNumberInput.sendKeys(number);
    }

    protected void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

}
