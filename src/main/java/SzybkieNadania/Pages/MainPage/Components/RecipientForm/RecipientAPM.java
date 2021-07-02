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

        name().fill(values[0]);
        email().fill(values[1]);
        phoneNumber().fill(values[2]);
        enterParcelLocker(values[3]);
    }

    protected void enterParcelLocker(String parcelLocker) {
        PageAction.waitUntilClickable(parcelLockerInput, webDriver).sendKeys(parcelLocker.toUpperCase());
        new WebDriverWait(webDriver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOfElementLocated(By.id(parcelLocker)));
        parcelLockerInput.sendKeys(Keys.ENTER);
    }

    protected String getParcelLockerValue() {
        return parcelLockerInput.getAttribute("value");
    }

    protected boolean getParcelLockerError() {
        return parcelLockerInput.findElement(By.xpath("//*[@id='error-boxMachineName']/following-sibling::small")).isDisplayed();
    }

    protected void clearParcelLocker() {
        PageAction.waitUntilClickable(parcelLockerInput, webDriver).clear();
    }
}
