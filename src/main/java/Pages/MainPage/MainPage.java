package Pages.MainPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private final WebDriver webDriver;

    @FindBy(id = "option_deliveryType_boxmachine")
    private WebElement deliveryP2P;

    @FindBy(id = "option_deliveryType_address")
    private WebElement deliveryP2D;

    @FindBy(id = "option_parcelSize_A")
    private WebElement smallParcelOption;

    @FindBy(id = "option_parcelSize_B")
    private WebElement mediumParcelOption;

    @FindBy(id = "option_parcelSize_C")
    private WebElement bigParcelOption;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    public void chooseParcelSize(ParcelSize size) {
        switch (size) {
            case SMALL: {
                smallParcelOption.click();
                break;
            }
            case MEDIUM: {
                mediumParcelOption.click();
                break;
            }
            case BIG: {
                bigParcelOption.click();
                break;
            }
        }
    }

    enum ParcelSize {
        SMALL,
        MEDIUM,
        BIG
    }

    enum DeliveryType {
        P2P,
        P2D
    }

}
