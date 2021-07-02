package SzybkieNadania.Pages.MainPage.Components.RecipientForm;

import SzybkieNadania.Utils.ElementWrappers.ComboBox;
import SzybkieNadania.Utils.ElementWrappers.InputField;
import SzybkieNadania.Utils.PageAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecipientD2D extends RecipientForm {
    @FindBy(name = "targetAddress.zipCode")
    private WebElement zipCodeInput;

    @FindBy(xpath = "//*[@name='targetAddress.town']/div/div")
    private WebElement townInput;

    @FindBy(xpath = "//*[@name='targetAddress.street']/div/div")
    private WebElement streetInput;

    @FindBy(name = "targetAddress.buildingNo")
    private WebElement buildingNumberInput;

    @FindBy(name = "targetAddress.flatNo")
    private WebElement flatNumberInput;

    public RecipientD2D(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    /**
     * @param values Name, Email, PhoneNumber, ZipCode, Town, Street, BuildingNumber, FlatNumber
     */
    @Override
    public void fill(String... values) {
        if (values.length == 1) {
            PageAction.checkCheckBox("Chcę podać dodatkowe informacje", webDriver);
            extraComment().fill(values[0]);
        }

        if (values.length < 8)
            return;

        name().fill(values[0]);
        email().fill(values[1]);
        phoneNumber().fill(values[2]);
        zipCode().fill(values[3]);
        town().fill(values[4]);
        street().fill(values[5]);
        buildingNumber().fill(values[6]);
        flatNumber().fill(values[7]);
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

    protected InputField extraComment() {
        return new InputField(PageAction.waitUntilIsVisible(By.name("customerReference"), webDriver));
    }

}
