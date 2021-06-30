package SzybkieNadania.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PageAction {

    public static void closeCookies(WebDriver webDriver) {
        closeMiddleCookies(webDriver);
        closeBottomCookies(webDriver);
    }

    public static void closeMiddleCookies(WebDriver webDriver) {
        (new WebDriverWait(webDriver, Duration.ofSeconds(5))).until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-accept-btn-handler"))).click();
    }

    public static void closeBottomCookies(WebDriver webDriver) {
        (new WebDriverWait(webDriver, Duration.ofSeconds(5))).until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-cookie-trigger"))).click();
    }

    public static void checkCheckBox(String name, WebDriver webDriver) {
        List<WebElement> checkBoxes = webDriver.findElements(By.className("checkbox"));
        for (WebElement checkBox : checkBoxes) {
            if (checkBox.getText().contains(name)) {
                waitUntilIsVisibleAndClick(checkBox, webDriver);
                break;
            }
        }
    }

    public static boolean isCheckedCheckBox(String name, WebDriver webDriver) {
        List<WebElement> checkBoxes = webDriver.findElements(By.className("checkbox"));
        for (WebElement checkBox : checkBoxes) {
            if (checkBox.getText().contains(name)) {
                return checkBox.findElement(By.id(checkBox.getAttribute("for"))).isSelected();
            }
        }
        return false;
    }

    public static void waitUntilClickable(WebElement webElement, WebDriver webDriver) {
        (new WebDriverWait(webDriver, Duration.ofSeconds(5))).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void waitUntilClickableAndClick(WebElement webElement, WebDriver webDriver) {
        (new WebDriverWait(webDriver, Duration.ofSeconds(5))).until(ExpectedConditions.elementToBeClickable(webElement)).click();
    }

    public static void waitUntilClickableAndSendKeys(WebElement webElement, String value, WebDriver webDriver) {
        (new WebDriverWait(webDriver, Duration.ofSeconds(5))).until(ExpectedConditions.elementToBeClickable(webElement)).sendKeys(value);
    }

    public static void waitUntilIsVisibleAndClick(WebElement webElement, WebDriver webDriver) {
        (new WebDriverWait(webDriver, Duration.ofSeconds(5))).until(ExpectedConditions.visibilityOf(webElement)).click();
    }

    public static void wait(int interval) {
        try {
            TimeUnit.MILLISECONDS.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
