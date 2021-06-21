package Pages.MainPage;

import Pages.MainPage.Components.ParcelSize;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage {
    private final WebDriver webDriver;

    private final ParcelSize parcelSize;

    @FindBy(css = "label[for = 'deliveryTypeboxmachine']")
    private WebElement deliveryP2P;

    @FindBy(css = "label[for = 'deliveryTypeaddress']")
    private WebElement deliveryP2D;

    public MainPage(WebDriver webDriver, ParcelSize parcelSize) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
        this.parcelSize = parcelSize;
    }

    public void chooseParcelSize(ParcelSize.Size size) {
        parcelSize.chooseParcelSize(size);
    }

    public void chooseDeliveryType(DeliveryType type) {
        switch (type) {
            case P2P: {
                deliveryP2P.click();
                break;
            }
            case P2D: {
                deliveryP2D.click();
                break;
            }
        }
    }

    public enum DeliveryType {
        P2P,
        P2D
    }

}