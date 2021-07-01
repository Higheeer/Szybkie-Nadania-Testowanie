package SzybkieNadania.Pages.MainPage.Components.SenderForm.Invoice;

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

public class CompanyInvoice implements Invoice {
    private final WebDriver webDriver;

    @FindBy(name = "invoice.company.nip")
    private WebElement nipInput;

    @FindBy(name = "invoice.company.companyName")
    private WebElement companyNameInput;

    @FindBy(name = "invoice.company.zipCode")
    private WebElement zipCodeInput;

    @FindBy(xpath = "//*[@name='invoice.company.town']/div/div/div[2]/input")
    private WebElement townInput;

    @FindBy(xpath = "//*[@name='invoice.company.street']/div/div/div[2]/input")
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
     * @param values Nip
     */
    @Override
    public void fill(String... values) {
        if (values.length == 1) {
            enterNip(values[0]);
        }
    }

    protected void enterNip(String nip) {
        nipInput.sendKeys(nip);
        new WebDriverWait(webDriver, Duration.ofSeconds(15)).until(ExpectedConditions.attributeToBe(nipInput,"value", nip));
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

    protected String getNip() {
        return nipInput.getAttribute("value");
    }

    protected String getCompanyName() {
        return companyNameInput.getAttribute("value");
    }

    protected String getZipCode() {
        return zipCodeInput.getAttribute("value");
    }

    protected String getTown() {
        return webDriver.findElement(By.xpath("//*[@name='invoice.company.town']/div/div/div[2]/span[2]")).getText();
    }

    protected String getStreet() {
        return webDriver.findElement(By.xpath("//*[@name='invoice.company.street']/div/div/div[2]/span[2]")).getText();
    }

    protected String getBuildingNumber() {
        return buildingNumberInput.getAttribute("value");
    }

    protected String getFlatNumber() {
        return flatNumberInput.getAttribute("value");
    }

    protected String getEmail() {
        return emailInput.getAttribute("value");
    }

    protected boolean getNipError() {
        return webDriver.findElement(By.xpath("//*[@id='error-invoice.company.nip']/following-sibling::small")).isDisplayed();
    }

    protected boolean getCompanyNameError() {
        return webDriver.findElement(By.xpath("//*[@id='error-invoice.company.companyName']/following-sibling::small")).isDisplayed();
    }

    protected boolean getZipCodeError() {
        return webDriver.findElement(By.xpath("//*[@id='error-invoice.company.zipCode']/following-sibling::small")).isDisplayed();
    }

    protected boolean getTownError() {
        return webDriver.findElement(By.xpath("//*[@id='error-invoice.company.town']/following-sibling::small")).isDisplayed();
    }

    protected boolean getStreetError() {
        return webDriver.findElement(By.xpath("//*[@id='error-invoice.company.street']/following-sibling::small")).isDisplayed();
    }

    protected boolean getBuildingNumberError() {
        return webDriver.findElement(By.xpath("//*[@id='error-invoice.company.buildingNo']/following-sibling::small")).isDisplayed();
    }

    protected boolean getFlatNumberError() {
        return webDriver.findElement(By.xpath("//*[@id='error-invoice.company.flatNo']/following-sibling::small")).isDisplayed();
    }

    protected boolean getEmailError() {
        return webDriver.findElement(By.xpath("//*[@id='error-invoice.company.email']/following-sibling::small")).isDisplayed();
    }


    protected void clearNip() {
        PageAction.waitUntilClickable(nipInput, webDriver).clear();
    }

    protected void clearCompanyName() {
        PageAction.waitUntilClickable(companyNameInput, webDriver).clear();
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

    protected void clearEmail() {
        PageAction.waitUntilClickable(emailInput, webDriver).clear();
    }
}
