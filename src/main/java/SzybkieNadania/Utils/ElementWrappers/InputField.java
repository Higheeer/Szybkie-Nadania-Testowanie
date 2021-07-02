package SzybkieNadania.Utils.ElementWrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class InputField implements PageElement {
    private final WebElement inputField;

    public InputField(WebElement inputField) {
        this.inputField = inputField;
    }

    @Override
    public void fill(String value) {
        clear().sendKeys(value);
    }

    @Override
    public InputField sendKeys(String keys) {
        inputField.sendKeys(keys);
        return this;
    }

    @Override
    public String value() {
        return inputField.getAttribute("value");
    }

    @Override
    public InputField clear() {
        inputField.clear();
        return this;
    }

    @Override
    public boolean error() {
        try {
            return inputField.findElement(By.xpath("../../following-sibling::app-error//*[contains(@class, 'errors')]")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
