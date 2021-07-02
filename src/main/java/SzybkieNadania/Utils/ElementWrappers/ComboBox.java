package SzybkieNadania.Utils.ElementWrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ComboBox implements PageElement {
    private final WebElement comboBox;
    private final WebDriver webDriver;

    public ComboBox(WebElement comboBox, WebDriver webDriver) {
        this.comboBox = comboBox;
        this.webDriver = webDriver;
    }

    @Override
    public void fill(String value) {
        waitUntilClickable().clear().sendKeys(value).submit();
    }

    @Override
    public ComboBox sendKeys(String keys) {
        comboBox.findElement(By.xpath(".//*/input")).sendKeys(keys);
        return this;
    }

    public ComboBox submit() {
        comboBox.findElement(By.xpath(".//*/input")).sendKeys(Keys.ENTER);
        return this;
    }

    public ComboBox waitUntilClickable() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(comboBox.findElement(By.xpath(".//*/input"))));
        return this;
    }

    @Override
    public String value() {
        return comboBox.findElement(By.xpath("*[contains(@class, 'ng-value')]")).getText();
    }

    @Override
    public ComboBox clear() {
        comboBox.findElement(By.xpath(".//*/input")).clear();
        return this;
    }

    @Override
    public boolean error() {
        try {
            return comboBox.findElement(By.xpath("../../../../following-sibling::app-error//*[contains(@class, 'errors')]")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
