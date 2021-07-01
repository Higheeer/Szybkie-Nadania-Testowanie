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

    @FindBy(xpath = "//*[@name='targetAddress.town']/div/div/div[2]/input")
    private WebElement townInput;

    @FindBy(xpath = "//*[@name='targetAddress.street']/div/div/div[2]/input")
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
            enterExtraComment(values[0]);
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

    protected void enterTown(String town) {
        PageAction.waitUntilClickableAndSendKeys(townInput, town, webDriver);
        townInput.sendKeys(Keys.ENTER);
    }

    protected void enterStreet(String street) {
        PageAction.waitUntilClickableAndSendKeys(streetInput, street, webDriver);
        streetInput.sendKeys(Keys.ENTER);
    }

    protected void enterBuildingNumber(String number) {
        PageAction.waitUntilClickableAndSendKeys(buildingNumberInput, number, webDriver);
    }

    protected void enterFlatNumber(String number) {
        PageAction.waitUntilClickableAndSendKeys(flatNumberInput, number, webDriver);
    }

    protected void enterExtraComment(String comment) {
        (new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.name("customerReference")))).sendKeys(comment);
    }

    protected String getZipCode() {
        return zipCodeInput.getAttribute("value");
    }

    protected String getTown() {
        return webDriver.findElement(By.xpath("//*[@name='targetAddress.town']/div/div/div[2]/span[2]")).getText();
    }

    protected String getStreet() {
        return webDriver.findElement(By.xpath("//*[@name='targetAddress.street']/div/div/div[2]/span[2]")).getText();
    }

    protected String getBuildingNumber() {
        return buildingNumberInput.getAttribute("value");
    }

    protected String getFlatNumber() {
        return flatNumberInput.getAttribute("value");
    }

    protected boolean getZipCodeError() {
        return webDriver.findElement(By.xpath("//*[@id='error-targetAddress.zipCode']/following-sibling::small")).isDisplayed();
    }

    protected boolean getTownError() {
        return webDriver.findElement(By.xpath("//*[@id='error-targetAddress.town']/following-sibling::small")).isDisplayed();
    }

    protected boolean getStreetError() {
        return webDriver.findElement(By.xpath("//*[@id='error-targetAddress.street']/following-sibling::small")).isDisplayed();
    }

    protected boolean getBuildingNumberError() {
        return webDriver.findElement(By.xpath("//*[@id='error-targetAddress.buildingNo']/following-sibling::small")).isDisplayed();
    }

    protected boolean getFlatNumberError() {
        return webDriver.findElement(By.xpath("//*[@id='error-targetAddress.flatNo']/following-sibling::small")).isDisplayed();
    }

    protected boolean getExtraCommentError() {
        return webDriver.findElement(By.xpath("//*[@id='error-customerReference']/following-sibling::small")).isDisplayed();
    }

    protected void clearZipCode() {
        PageAction.waitUntilClickable(zipCodeInput,webDriver).clear();
    }

    protected void clearTown() {
        PageAction.waitUntilClickable(townInput,webDriver).clear();
    }

    protected void clearStreet() {
        PageAction.waitUntilClickable(streetInput,webDriver).clear();
    }

    protected void clearBuildingNumber() {
        PageAction.waitUntilClickable(buildingNumberInput,webDriver).clear();
    }

    protected void clearFlatNumber() {
        PageAction.waitUntilClickable(flatNumberInput,webDriver).clear();
    }

    protected void clearExtraComment() {
        (new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.name("customerReference")))).clear();
    }
}
