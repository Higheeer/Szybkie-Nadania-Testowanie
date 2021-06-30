package SzybkieNadania.Pages.MainPage.Components.RecipientForm;

import SzybkieNadania.Utils.PageAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecipientD2D extends RecipientForm {
    @FindBy(name = "targetAddress.zipCode")
    private WebElement zipCodeInput;

    @FindBy(css = "#parcelForm > div > div.col-md-6.col-lg-7.col-xl-8 > app-dynamic-form > form > app-section:nth-child(8) > div > app-input > div > div > div > app-selectpicker > ng-select > div > div > div.ng-input > input[type=text]")
    private WebElement townInput;

    @FindBy(css = "#parcelForm > div > div.col-md-6.col-lg-7.col-xl-8 > app-dynamic-form > form > app-section:nth-child(9) > div > app-input > div > div > div > app-selectpicker > ng-select > div > div > div.ng-input > input[type=text]")
    private WebElement streetInput;

    @FindBy(name = "targetAddress.buildingNo")
    private WebElement buildingNumberInput;

    @FindBy(name = "targetAddress.flatNo")
    private WebElement flatNumberInput;

    public RecipientD2D(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    /**
     * @param values Name, Email, PhoneNumber, ZipCode, Town, Street, BuildingNumber, FlatNumber
     */
    @Override
    public void fill(String... values) {
        if (values.length == 1) {
            PageAction.checkCheckBox("Chcę podać dodatkowe informacje", webDriver);
            (new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.name("customerReference")))).sendKeys(values[0]);
        }

        if (values.length < 8)
            return;

        enterName(values[0]);
        enterEmail(values[1]);
        enterPhoneNumber(values[2]);
        enterZipCode(values[3]);
        enterTown(values[4]);
        enterStreet(values[5]);
        enterBuildingNumber(values[6]);
        enterFlatNumber(values[7]);
    }

    protected void enterZipCode(String zipCode) {
        PageAction.waitUntilClickableAndSendKeys(zipCodeInput, zipCode, webDriver);
    }

    protected String getZipCode() {
        return zipCodeInput.getAttribute("value");
    }

    protected void enterTown(String town) {
        PageAction.waitUntilClickableAndSendKeys(townInput, town, webDriver);
        townInput.sendKeys(Keys.ENTER);
    }

    protected String getTown() {
        return townInput.getAttribute("value");
    }

    protected void enterStreet(String street) {
        PageAction.waitUntilClickableAndSendKeys(streetInput, street, webDriver);
        streetInput.sendKeys(Keys.ENTER);
    }

    protected String getStreet() {
        return streetInput.getAttribute("value");
    }

    protected void enterBuildingNumber(String number) {
        PageAction.waitUntilClickableAndSendKeys(buildingNumberInput, number, webDriver);
    }

    protected String getBuildingNumber() {
        return buildingNumberInput.getAttribute("value");
    }

    protected void enterFlatNumber(String number) {
        PageAction.waitUntilClickableAndSendKeys(flatNumberInput, number, webDriver);
    }

    protected String getFlatNumber() {
        return flatNumberInput.getAttribute("value");
    }
}
