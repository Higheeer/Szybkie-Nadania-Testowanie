package SzybkieNadania.Pages.MainPage.Components.Forms.Sender;

import SzybkieNadania.Pages.MainPage.Components.Forms.Sender.Invoice.CompanyInvoice;
import SzybkieNadania.Pages.MainPage.Components.Forms.Sender.Invoice.ForeignCompanyInvoice;
import SzybkieNadania.Pages.MainPage.Components.Forms.Sender.Invoice.IndividualInvoice;
import SzybkieNadania.Pages.MainPage.Components.Forms.Sender.Invoice.Invoice;
import SzybkieNadania.Utils.ElementWrappers.InputField;
import SzybkieNadania.Utils.PageAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SenderForm {
    private final WebDriver webDriver;

    @FindBy(name = "senderAddress.name")
    private WebElement nameInput;

    @FindBy(name = "senderAddress.email")
    private WebElement emailInput;

    @FindBy(name = "senderAddress.phoneNum")
    private WebElement phoneNumberInput;

    private Invoice invoice;

    public SenderForm(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    public void fill(String name, String email, String phoneNumber) {
        name().sendKeys(name);
        email().sendKeys(email);
        phoneNumber().sendKeys(phoneNumber);
    }

    public void chooseInvoice(InvoiceType type) {
        PageAction.checkCheckBox("Chcę otrzymać fakturę", webDriver);

        switch (type) {
            case COMPANY: {
                invoice = new CompanyInvoice(webDriver);
                PageAction.checkCheckBox("Firma w Polsce", webDriver);
                break;
            }
            case INDIVIDUAL: {
                invoice = new IndividualInvoice(webDriver);
                PageAction.checkCheckBox("Osoba prywatna", webDriver);
                break;
            }
            case FOREIGN_COMPANY: {
                invoice = new ForeignCompanyInvoice(webDriver);
                PageAction.checkCheckBox("Firma za granicą", webDriver);
                break;
            }
        }
    }

    public void fillInvoice(String... values) {
        invoice.fill(values);
    }

    protected InputField name() {
        return new InputField(nameInput);
    }

    protected InputField email() {
        return new InputField(emailInput);
    }

    protected InputField phoneNumber() {
        return new InputField(phoneNumberInput);
    }

    public enum InvoiceType {
        COMPANY,
        INDIVIDUAL,
        FOREIGN_COMPANY
    }

}
