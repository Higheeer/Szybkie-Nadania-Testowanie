package SzybkieNadania.Pages.SummaryPage.Components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReceiverDetails {
    @FindBy(xpath = "(//*[contains(@class, 'first-column') and not(contains(@class, 'summary-column'))])[1]/div[1]")
    private WebElement name;
    @FindBy(xpath = "(//*[contains(@class, 'first-column') and not(contains(@class, 'summary-column'))])[1]/div[2]")
    private WebElement phoneNumber;
    @FindBy(xpath = "(//*[contains(@class, 'first-column') and not(contains(@class, 'summary-column'))])[1]/div[3]")
    private WebElement email;
    @FindBy(xpath = "(//*[contains(@class, 'first-column') and not(contains(@class, 'summary-column'))])[1]/div[4]")
    private WebElement town;
    @FindBy(xpath = "(//*[contains(@class, 'first-column') and not(contains(@class, 'summary-column'))])[1]/div[5]")
    private WebElement address;

    public ReceiverDetails(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    public String getName(){
        return name.getText();
    }
    public String getPhoneNumber(){
        return phoneNumber.getText().replaceAll(" ", "");
    }
    public String getEmail(){
        return email.getText();
    }

    public String getTown(){
        return town.getText();
    }

    public String getAddress(){
        return address.getText();
    }
}

