package Pages.MainPage.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
}
