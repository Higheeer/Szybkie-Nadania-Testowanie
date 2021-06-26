package Pages.MainPage.Components.SenderForm.Invoice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForeignCompany implements Invoice {
    private final WebDriver webDriver;

    @FindBy(css = "div.ng-input:nth-child(3) > input:nth-child(1)")
    private WebElement nipPrefixInput;

    @FindBy(name = "invoice.foreignCompany.nip")
    private WebElement nipInput;

    @FindBy(name = "invoice.foreignCompany.companyName")
    private WebElement companyNameInput;

    @FindBy(css = "app-section.col-12:nth-child(22) > div:nth-child(1) > app-input:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > app-complex-select:nth-child(1) > ng-select:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > input:nth-child(1)")
    private WebElement countryInput;

    @FindBy(name = "invoice.foreignCompany.zipCode")
    private WebElement zipCodeInput;

    @FindBy(name = "invoice.foreignCompany.city")
    private WebElement cityInput;

    @FindBy(name = "invoice.foreignCompany.street")
    private WebElement streetInput;

    @FindBy(name = "invoice.foreignCompany.buildingNo")
    private WebElement buildingNumberInput;

    @FindBy(name = "invoice.foreignCompany.flatNo")
    private WebElement flatNumberInput;

    @FindBy(name = "invoice.foreignCompany.email")
    private WebElement emailInput;

    public ForeignCompany(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    /**
     * @param values NipPrefix, Nip, CompanyName, Country, ZipCode, City, Street, BuildingNumber, FlatNumber, Email
     */
    @Override
    public void fill(String... values) {
        if (values.length < 9)
            return;

        enterNipPrefix(values[0]);
        enterNip(values[1]);
        enterCompanyName(values[2]);
        enterCountry(values[3]);
        enterZipCode(values[4]);
        enterCity(values[5]);
        enterStreet(values[6]);
        enterBuildingNumber(values[7]);
        enterFlatNumber(values[8]);
        enterEmail(values[9]);
    }

    protected void enterNipPrefix(String prefix) {
        nipPrefixInput.sendKeys(prefix);
    }

    protected void enterNip(String nip) {
        nipInput.sendKeys(nip);
    }

    protected void enterCompanyName(String name) {
        companyNameInput.sendKeys(name);
    }

    protected void enterCountry(String country) {
        countryInput.sendKeys(country);
    }

    protected void enterZipCode(String zipCode) {
        zipCodeInput.sendKeys(zipCode);
    }

    protected void enterCity(String city) {
        cityInput.sendKeys(city);
    }

    protected void enterStreet(String street) {
        streetInput.sendKeys(street);
    }

    protected void enterBuildingNumber(String buildingNumber) {
        buildingNumberInput.sendKeys(buildingNumber);
    }

    protected void enterFlatNumber(String flatNumber) {
        flatNumberInput.sendKeys(flatNumber);
    }

    protected void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

}
