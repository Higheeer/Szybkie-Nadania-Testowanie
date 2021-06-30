package SzybkieNadania.Pages.MainPage.Components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeliveryMethod {
    private final WebDriver webDriver;

    @FindBy(css = "label[for='deliveryTypeboxmachine']")
    private WebElement p2pOption;

    @FindBy(css = "label[for='deliveryTypeaddress']")
    private WebElement p2hOption;

    public DeliveryMethod(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void choose(Type type) {
        switch (type) {
            case P2P: {
                p2pOption.click();
                break;
            }
            case P2H: {
                p2hOption.click();
                break;
            }
        }
    }

    public boolean isSelectedP2P() {
        return p2pOption.findElement(By.xpath("//preceding-sibling::input[@id='deliveryTypeboxmachine']")).isSelected();
    }

    public boolean isSelectedP2H() {
        return p2hOption.findElement(By.xpath("//preceding-sibling::input[@id='deliveryTypeaddress']")).isSelected();
    }

    public enum Type {
        P2P,
        P2H
    }
}
