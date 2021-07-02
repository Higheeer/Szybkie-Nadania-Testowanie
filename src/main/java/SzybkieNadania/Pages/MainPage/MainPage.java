package SzybkieNadania.Pages.MainPage;

import SzybkieNadania.Pages.MainPage.Components.DeliveryMethod;
import SzybkieNadania.Pages.MainPage.Components.ParcelSize;
import SzybkieNadania.Pages.MainPage.Components.RecipientForm.RecipientAPM;
import SzybkieNadania.Pages.MainPage.Components.RecipientForm.RecipientD2D;
import SzybkieNadania.Pages.MainPage.Components.RecipientForm.RecipientForm;
import SzybkieNadania.Pages.MainPage.Components.SenderForm.SenderForm;
import SzybkieNadania.Utils.PageAction;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private final WebDriver webDriver;
    private final DeliveryMethod deliveryMethod;
    private final ParcelSize parcelSize;
    private final SenderForm senderForm;

    @FindBy(css = "#terms + div")
    private WebElement termsCheckBoxButton;
    @FindBy(css = "#parcelFormButton > button")
    private WebElement submitButton;
    @FindBy(css = "div.d-flex.justify-content-between.buttons > div:nth-child(2) > button")
    private WebElement payButton;
    private RecipientForm recipientForm;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);

        this.deliveryMethod = new DeliveryMethod(this.webDriver);
        this.parcelSize = new ParcelSize(this.webDriver);
        this.senderForm = new SenderForm(webDriver);
    }

    public void chooseDeliveryType(DeliveryMethod.Type type) {
        deliveryMethod.choose(type);
        switch (type) {
            case APM: {
                recipientForm = new RecipientAPM(webDriver);
                break;
            }
            case D2D: {
                recipientForm = new RecipientD2D(webDriver);
                break;
            }
        }
    }

    public void chooseParcelSize(ParcelSize.Size size) {
        parcelSize.choose(size);
    }

    public void fillRecipientForm(String... values) {
        recipientForm.fill(values);
    }

    public void fillSenderForm(String name, String email, String phoneNumber) {
        senderForm.fill(name, email, phoneNumber);
    }

    public void wantInvoice(SenderForm.InvoiceType type, String... values) {
        senderForm.chooseInvoice(type);
        senderForm.fillInvoice(values);
    }

    public void wantAddExtraCourierComment(String comment) {
        recipientForm.fill(comment);
    }

    public void acceptTermsAndConditions() {
        termsCheckBoxButton.click();
    }

    public boolean acceptMarketingMessages() {
        String name = "informacji handlowych dotyczących produktów";

        PageAction.checkCheckBox(name, webDriver);
        return PageAction.isCheckedCheckBox(name, webDriver);
    }

    public void submit() {
        PageAction.waitUntilClickable(submitButton, webDriver).click();
    }

    public void payFor() {
        try {
            PageAction.waitUntilIsVisible(payButton, webDriver).click();
        } catch (TimeoutException e) {
            submit();
            payFor();
        }

    }
}