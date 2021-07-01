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

public class RecipientAPM extends RecipientForm {
    @FindBy(css = "app-points-select > ng-select > div > div > div.ng-input > input[type=text]")
    private WebElement parcelLockerInput;
    @FindBy(id = "error-boxMachineName")
    private WebElement parcelLockerError;

    public RecipientAPM(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    /**
     * @param values Name, Email, PhoneNumber, ParcelLocker
     */
    @Override
    public void fill(String... values) {
        if (values.length < 4)
            return;

        enterName(values[0]);
        enterEmail(values[1]);
        enterPhoneNumber(values[2]);
        enterParcelLocker(values[3]);
    }

    protected void enterParcelLocker(String parcelLocker) {
        PageAction.waitUntilClickableAndSendKeys(parcelLockerInput, parcelLocker.toUpperCase(), webDriver);
        new WebDriverWait(webDriver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOfElementLocated(By.id(parcelLocker)));
        parcelLockerInput.sendKeys(Keys.ENTER);
    }

    protected String getParcelLocker() {
        return parcelLockerInput.getAttribute("value");
    }

    protected boolean getParcelLockerError() {
        return webDriver.findElement(By.xpath("//*[@id='error-boxMachineName']/following-sibling::small")).isDisplayed();
    }

    protected void clearParcelLocker() {
        parcelLockerInput.clear();
    }
}
