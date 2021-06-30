package SzybkieNadania.Pages.MainPage.Components.SenderForm.Invoice;

import SzybkieNadania.Utils.PageAction;
import org.openqa.selenium.Keys;
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

    @FindBy(css = "app-selectpicker:nth-child(1) > ng-select:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
    private WebElement townInput;

    @FindBy(css = "app-selectpicker:nth-child(1) > ng-select:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
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
     * @param values Nip, CompanyName, ZipCode, Town, Street, BuildingNumber, FlatNumber, Email
     */
    @Override
    public void fill(String... values) {
        if (values.length == 1) {
            enterNip(values[0]);
        }

        if (values.length < 8)
            return;

        enterNip(values[0]);
        enterCompanyName(values[1]);
        enterZipCode(values[2]);
        enterTown(values[3]);
        enterStreet(values[4]);
        enterBuildingNumber(values[5]);
        enterFlatNumber(values[6]);
        enterEmail(values[7]);
    }

    protected void enterNip(String nip) {
        nipInput.sendKeys(nip);
    }

    protected void enterCompanyName(String companyName) {
        companyNameInput.sendKeys(companyName);
    }

    protected void enterZipCode(String zipCode) {
        zipCodeInput.sendKeys(zipCode);
    }

    protected void enterTown(String town) {
        PageAction.waitUntilClickable(townInput, webDriver);
        townInput.sendKeys(town);
        townInput.sendKeys(Keys.ENTER);
    }

    protected void enterStreet(String street) {
        PageAction.waitUntilClickable(streetInput, webDriver);
        streetInput.sendKeys(street);
        streetInput.sendKeys(Keys.ENTER);
    }

    protected void enterBuildingNumber(String number) {
        buildingNumberInput.sendKeys(number);
    }

    protected void enterFlatNumber(String number) {
        flatNumberInput.sendKeys(number);
    }

    protected void enterEmail(String email) {
        PageAction.waitUntilClickable(emailInput, webDriver);
        emailInput.sendKeys(email);
    }

}
