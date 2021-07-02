package SzybkieNadania.Pages.SummaryPage.Components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ParcelLockerDetails {
    @FindAll({
            @FindBy(xpath = "(//*[contains(@class, 'third-column') and not(contains(@class, 'summary-column'))])[1]/div/div")
    })
    List<WebElement> details;

    public ParcelLockerDetails(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public String getName() {
        return details.get(0).getText();
    }

    public String getAddress() {
        return details.get(1).getText();
    }

    public String getTown() {
        return details.get(2).getText();
    }

    public String getHint() {
        return details.get(3).getText();
    }

}
