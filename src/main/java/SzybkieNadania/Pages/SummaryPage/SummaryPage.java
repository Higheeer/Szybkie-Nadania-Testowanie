package SzybkieNadania.Pages.SummaryPage;

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

    @FindBy(css = "#publicPage > div.container.full-height > app-summary > section:nth-child(2) > div > div:nth-child(2) > div > button > span")
    private WebElement refreshButton;

    public SummaryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
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


