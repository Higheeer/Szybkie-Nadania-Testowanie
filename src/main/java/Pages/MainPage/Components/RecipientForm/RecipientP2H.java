package Pages.MainPage.Components.RecipientForm;

import Pages.MainPage.Utils.PageAction;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecipientP2H extends RecipientForm {
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

    public RecipientP2H(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(this.webDriver, this);
    }

    /**
     * @param values Name, Email, PhoneNumber, ZipCode, Town, Street, BuildingNumber, FlatNumber
     */
    @Override
    public void fill(String... values) {
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
}
