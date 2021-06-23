package Pages.MainPage.Components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeliveryMethod {
    private final WebDriver webDriver;

    @FindBy(css = "label[for='deliveryTypeboxmachine']")
    private WebElement p2pOption;

    @FindBy(css = "input[id = 'deliveryTypeboxmachine']")
    private WebElement p2pCheckBox;

    @FindBy(css = "label[for='deliveryTypeaddress']")
    private WebElement p2hOption;

    @FindBy(css = "input[id='deliveryTypeaddress']")
    private WebElement p2hCheckBox;

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
        return p2pCheckBox.isSelected();
    }

    public boolean isSelectedP2H() {
        return p2hCheckBox.isSelected();
    }

    public enum Type {
        P2P,
        P2H
    }
}
