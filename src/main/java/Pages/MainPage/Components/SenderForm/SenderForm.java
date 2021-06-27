package Pages.MainPage.Components.SenderForm;

import Pages.MainPage.Components.SenderForm.Invoice.CompanyInvoice;
import Pages.MainPage.Components.SenderForm.Invoice.ForeignCompanyInvoice;
import Pages.MainPage.Components.SenderForm.Invoice.IndividualInvoice;
import Pages.MainPage.Components.SenderForm.Invoice.Invoice;
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

    protected void enterName(String name) {
        nameInput.sendKeys(name);
    }

    protected void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    protected void enterPhoneNumber(String phoneNumber) {
        phoneNumberInput.sendKeys(phoneNumber);
    }

    public void fill(String name, String email, String phoneNumber) {
        enterName(name);
        enterEmail(email);
        enterPhoneNumber(phoneNumber);
    }

    public void chooseInvoice(InvoiceType type) {
        switch (type) {
            case COMPANY: {
                invoice = new CompanyInvoice(webDriver);
                break;
            }
            case INDIVIDUAL: {
                invoice = new IndividualInvoice(webDriver);
                break;
            }
            case FOREIGN_COMPANY: {
                invoice = new ForeignCompanyInvoice(webDriver);
                break;
            }
        }
    }

    public void fillInvoice(String... values) {
        invoice.fill(values);
    }

    public enum InvoiceType {
        COMPANY,
        INDIVIDUAL,
        FOREIGN_COMPANY
    }

}
