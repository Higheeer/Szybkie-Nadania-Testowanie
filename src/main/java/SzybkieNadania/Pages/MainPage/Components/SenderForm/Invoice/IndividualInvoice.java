package SzybkieNadania.Pages.MainPage.Components.SenderForm.Invoice;

import SzybkieNadania.Utils.PageAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndividualInvoice implements Invoice {
    private final WebDriver webDriver;

    @FindBy(name = "invoice.individual.companyName")
    private WebElement nameInput;

    @FindBy(name = "invoice.individual.email")
    private WebElement emailInput;

    @FindBy(name = "invoice.individual.zipCode")
    private WebElement zipCodeInput;

    @FindBy(xpath = "//*[@name='invoice.individual.town']/div/div/div[2]/input")
    private WebElement townInput;

    @FindBy(xpath = "//*[@name='invoice.individual.street']/div/div/div[2]/input")
    private WebElement streetInput;

    @FindBy(name = "invoice.individual.buildingNo")
    private WebElement buildingNumberInput;

    @FindBy(name = "invoice.individual.flatNo")
    private WebElement flatNumberInput;

    @FindBy(tagName = "app-form-button")
    private WebElement copySenderDataButton;

    public IndividualInvoice(WebDriver webDriver) {
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

    public boolean copySenderData() {
        copySenderDataButton.click();
        return nameInput.getAttribute("value").equals(webDriver.findElement(By.name("senderAddress.name")).getAttribute("value")) &&
                emailInput.getAttribute("value").equals(webDriver.findElement(By.name("senderAddress.email")).getAttribute("value"));

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

    protected void enterBuildingNumber(String number) {
        buildingNumberInput.sendKeys(number);
    }

    protected void enterFlatNumber(String number) {
        flatNumberInput.sendKeys(number);
    }

    protected String getName() {
        return nameInput.getAttribute("value");
    }

    protected String getEmail() {
        return emailInput.getAttribute("value");
    }

    protected String getZipCode() {
        return zipCodeInput.getAttribute("value");
    }

    protected String getTown() {
        return webDriver.findElement(By.xpath("//*[@name='invoice.individual.town']/div/div/div[2]/span[2]")).getText();
    }

    protected String getStreet() {
        return webDriver.findElement(By.xpath("//*[@name='invoice.individual.street']/div/div/div[2]/span[2]")).getText();
    }

    protected String getBuildingNumber() {
        return buildingNumberInput.getAttribute("value");
    }

    protected String getFlatNumber() {
        return flatNumberInput.getAttribute("value");
    }

    protected boolean getNameError() {
        return webDriver.findElement(By.xpath("//*[@id='error-invoice.individual.companyName']/following-sibling::small")).isDisplayed();
    }
    
    protected boolean getEmailError() {
        return webDriver.findElement(By.xpath("//*[@id='error-invoice.individual.email']/following-sibling::small")).isDisplayed();
    }
    
    protected boolean getZipCodeError() {
        return webDriver.findElement(By.xpath("//*[@id='error-invoice.individual.zipCode']/following-sibling::small")).isDisplayed();
    }

    protected boolean getTownError() {
        return webDriver.findElement(By.xpath("//*[@id='error-invoice.individual.town']/following-sibling::small")).isDisplayed();
    }

    protected boolean getStreetError() {
        return webDriver.findElement(By.xpath("//*[@id='error-invoice.individual.street']/following-sibling::small")).isDisplayed();
    }

    protected boolean getBuildingNumberError() {
        return webDriver.findElement(By.xpath("//*[@id='error-invoice.individual.buildingNo']/following-sibling::small")).isDisplayed();
    }

    protected boolean getFlatNumberError() {
        return webDriver.findElement(By.xpath("//*[@id='error-invoice.individual.flatNo']/following-sibling::small")).isDisplayed();
    }

    protected void clearName() {
        PageAction.waitUntilClickable(nameInput, webDriver).clear();
    }

    protected void clearEmail() {
        PageAction.waitUntilClickable(emailInput, webDriver).clear();
    }

    protected void clearZipCode() {
        PageAction.waitUntilClickable(zipCodeInput, webDriver).clear();
    }

    protected void clearTown() {
        PageAction.waitUntilClickable(townInput, webDriver).clear();
    }

    protected void clearStreet() {
        PageAction.waitUntilClickable(streetInput, webDriver).clear();
    }

    protected void clearBuildingNumber() {
        PageAction.waitUntilClickable(buildingNumberInput, webDriver).clear();
    }

    protected void clearFlatNumber() {
        PageAction.waitUntilClickable(flatNumberInput, webDriver).clear();
    }
}
