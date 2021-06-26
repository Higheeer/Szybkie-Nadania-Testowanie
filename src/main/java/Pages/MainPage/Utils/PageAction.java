package Pages.MainPage.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PageAction {
    public static void checkCheckBox(String name, WebDriver webDriver) {
        List<WebElement> checkBoxes = webDriver.findElements(By.className("checkbox"));
        for (WebElement checkBox : checkBoxes) {
            if (checkBox.getText().contains(name)) {
                checkBox.click();
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
        WebDriverWait waiter = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        waiter.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void waitUntilValueInserted(WebElement webElement, String value, WebDriver webDriver) {
        WebDriverWait waiter = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        waiter.until(ExpectedConditions.textToBePresentInElementValue(webElement, value));
    }

}
