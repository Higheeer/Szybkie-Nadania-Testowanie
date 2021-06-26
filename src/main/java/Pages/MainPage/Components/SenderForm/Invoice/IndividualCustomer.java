package Pages.MainPage.Components.SenderForm.Invoice;

import Pages.MainPage.Utils.PageAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndividualCustomer implements Invoice {
    private final WebDriver webDriver;

    @FindBy(name = "invoice.individual.companyName")
    private WebElement nameInput;

    @FindBy(name = "invoice.individual.email")
    private WebElement emailInput;

    @FindBy(name = "invoice.individual.zipCode")
    private WebElement zipCodeInput;

    @FindBy(css = "app-selectpicker:nth-child(1) > ng-select:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
    private WebElement townInput;

    @FindBy(css = "app-selectpicker:nth-child(1) > ng-select:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
    private WebElement streetInput;

    @FindBy(name = "invoice.individual.buildingNo")
    private WebElement buildingNumberInput;

    @FindBy(name = "invoice.individual.flatNo")
    private WebElement flatNumberInput;

    @FindBy(tagName = "app-form-button")
    private WebElement copySenderDataButton;

    public IndividualCustomer(WebDriver webDriver) {
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

        enterName(values[0]);
        enterEmail(values[1]);
        enterZipCode(values[2]);
        enterTown(values[3]);
        enterStreet(values[4]);
        enterBuildingNumber(values[5]);
        enterFlatNumber(values[6]);
    }

    protected void enterName(String name) {
        nameInput.sendKeys(name);
    }

    protected void enterEmail(String email) {
        emailInput.sendKeys(email);
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

    protected void enterBuildingNumber(String buildingNumber) {
        buildingNumberInput.sendKeys(buildingNumber);
    }

    protected void enterFlatNumber(String flatNumber) {
        flatNumberInput.sendKeys(flatNumber);
    }

    public boolean copySenderData() {
        copySenderDataButton.click();
        return nameInput.getAttribute("value").equals(webDriver.findElement(By.name("senderAddress.name")).getAttribute("value")) &&
                emailInput.getAttribute("value").equals(webDriver.findElement(By.name("senderAddress.email")).getAttribute("value"));

    }
}
