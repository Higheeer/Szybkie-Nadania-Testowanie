package SzybkieNadania.Pages.SummaryPage;

import SzybkieNadania.Pages.MainPage.Components.Forms.Sender.SenderForm;
import SzybkieNadania.Pages.SummaryPage.Components.InvoiceDetails;
import SzybkieNadania.Pages.SummaryPage.Components.ParcelLockerDetails;
import SzybkieNadania.Pages.SummaryPage.Components.ReceiverDetails;
import SzybkieNadania.Pages.SummaryPage.Components.SenderDetails;
import SzybkieNadania.Utils.PageAction;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SummaryPage {
    private final WebDriver webDriver;
    private final SenderForm.InvoiceType invoiceType;

    @FindBy(xpath = "//*[@id='publicPage']/div[2]/app-summary/section[1]/div/div[2]/div/button/span")
    private WebElement refreshButton;

    public SummaryPage(SenderForm.InvoiceType invoiceType, WebDriver webDriver) {
        this.webDriver = webDriver;
        this.invoiceType = invoiceType;
        PageFactory.initElements(this.webDriver, this);
    }
    public SummaryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.invoiceType = null;
        PageFactory.initElements(this.webDriver, this);
    }

    public SenderDetails senderDetails() {
        return new SenderDetails(webDriver);
    }

    public ReceiverDetails receiverDetails() {
        return new ReceiverDetails(webDriver);
    }

    public ParcelLockerDetails parcelLockerDetails() {
        return new ParcelLockerDetails(webDriver);
    }

    public InvoiceDetails invoiceDetails() {
        return new InvoiceDetails(invoiceType,webDriver);
    }

    public SummaryPage refresh() {
        try {
            while (refreshButton.isDisplayed()) {
                refreshButton.click();
                PageAction.wait(1000);
            }

        } catch (NoSuchElementException e) {

        }

        return this;
    }

    public boolean isPaid() {
        return (new WebDriverWait(webDriver, Duration.ofSeconds(60)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.row.price.paid")))).isDisplayed();
    }
}


