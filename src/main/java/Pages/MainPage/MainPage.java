package Pages.MainPage;

import Pages.MainPage.Components.DeliveryMethod;
import Pages.MainPage.Components.ParcelSize;
import Pages.MainPage.Utils.PageAction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MainPage {
    private final WebDriver webDriver;

    private final DeliveryMethod deliveryMethod;
    private final ParcelSize parcelSize;

    @FindBy(css = "label[for = 'deliveryTypeboxmachine']")
    private WebElement deliveryP2P;

    @FindBy(css = "label[for = 'deliveryTypeaddress']")
    private WebElement deliveryP2D;

    public MainPage(WebDriver webDriver, DeliveryMethod deliveryMethod, ParcelSize parcelSize) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
        this.deliveryMethod = deliveryMethod;
        this.parcelSize = parcelSize;
    }

    public void chooseDeliveryType(DeliveryMethod.Type type) {
        deliveryMethod.choose(type);
    }

    public void chooseParcelSize(ParcelSize.Size size) {
        parcelSize.choose(size);
    }

    public boolean acceptTermsAndConditions() {
        String name = "Regulaminem świadczenia usług pocztowych";

        PageAction.checkCheckBox(name, webDriver);
        return PageAction.isCheckedCheckBox(name, webDriver);
    }

    public boolean acceptMarketingMessages() {
        String name = "informacji handlowych dotyczących produktów";

        PageAction.checkCheckBox(name, webDriver);
        return PageAction.isCheckedCheckBox(name, webDriver);
    }
}