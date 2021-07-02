package SzybkieNadania.Pages.SummaryPage.Components;

import SzybkieNadania.Pages.MainPage.Components.Forms.Sender.SenderForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InvoiceDetails {
    @FindAll({
            @FindBy(xpath = "//app-parcel-form-whole-summary/div/ul/li[4]/div/div[3]/div/div")
    })
    List<WebElement> details;
    SenderForm.InvoiceType invoiceType;

    public InvoiceDetails(SenderForm.InvoiceType invoiceType, WebDriver webDriver) {
        this.invoiceType = invoiceType;
        PageFactory.initElements(webDriver, this);
    }

    public String getCompanyName() {
        return details.get(0).getText();
    }

    public String getNip() {
        return details.get(1).getText();
    }

    public String getTown() {
        if(invoiceType == SenderForm.InvoiceType.INDIVIDUAL){
            return details.get(1).getText();
        }
        return details.get(2).getText();
    }

    public String getAddress() {
        if(invoiceType == SenderForm.InvoiceType.INDIVIDUAL){
            return details.get(2).getText();
        }
        return details.get(3).getText();
    }
}
