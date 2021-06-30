package SzybkieNadania.Pages.PaymentPage;

import SzybkieNadania.Pages.PaymentPage.Components.Payment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PaymentPage {
    private final WebDriver webDriver;

    @FindAll(@FindBy(className = "payment-method"))
    private List<WebElement> paymentMethods;

    public PaymentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        (new WebDriverWait(this.webDriver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".more-banks-action")))).click();
        PageFactory.initElements(this.webDriver, this);
    }

    public PaymentPage choosePaymentMethod(String value) {
        for (WebElement method : paymentMethods) {
            if (method.getAttribute("title").toLowerCase().contains(value.toLowerCase())) {
                method.click();
                return this;
            }
        }
        return this;
    }

    public PaymentPage finish(Payment status) {
        (new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".finish-button")))).click();

        switch (status){
            case APPROVED:{
                (new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[value ='1']")))).click();
                return this;
            }
            case DECLINED:{
                (new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[value ='2'")))).click();
                return this;
            }

            case PENDING:{
                (new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[value ='3'")))).click();
                return this;
            }
        }
        return this;
    }



}

