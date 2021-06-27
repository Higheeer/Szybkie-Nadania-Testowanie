package Pages.MainPage;

import Pages.MainPage.Components.DeliveryMethod;
import Pages.MainPage.Components.ParcelSize;
import Pages.MainPage.Components.RecipientForm.RecipientForm;
import Pages.MainPage.Components.RecipientForm.RecipientP2H;
import Pages.MainPage.Components.RecipientForm.RecipientP2P;
import Pages.MainPage.Components.SenderForm.SenderForm;
import Pages.MainPage.Utils.PageAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private final WebDriver webDriver;
    private final DeliveryMethod deliveryMethod;
    private final ParcelSize parcelSize;

    @FindBy(css = "div[id = 'parcelFormButton'] > button")
    private WebElement submitButton;

    private RecipientForm recipientForm;
    private SenderForm senderForm;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);

        this.deliveryMethod = new DeliveryMethod(this.webDriver);
        this.parcelSize = new ParcelSize(this.webDriver);
    }

    public void chooseDeliveryType(DeliveryMethod.Type type) {
        deliveryMethod.choose(type);
        switch (type) {
            case P2P: {
                recipientForm = new RecipientP2P(webDriver);
                break;
            }
            case P2H: {
                recipientForm = new RecipientP2H(webDriver);
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

    public boolean acceptTermsAndConditions() {
        String name = "Regulaminem świadczenia usług pocztowych";

        PageAction.checkCheckBox(name, webDriver);
        return PageAction.isCheckedCheckBox(name, webDriver);
    }

    public boolean acceptMarketingMessages() {
        String name = "informacji handlowych dotyczących produktów";

        PageAction.checkCheckBox(name, webDriver);
        return PageAction.isCheckedCheckBox(name, webDriver);
    }

    public void submit() {
        submitButton.click();
    }
}