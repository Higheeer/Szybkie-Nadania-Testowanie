package Pages.MainPage.Components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ParcelSize {
    private final WebDriver webDriver;

    @FindBy(css = "label[for = 'parcelSizeA']")
    private WebElement smallParcelOption;

    @FindBy(css = "input[id = 'parcelSizeA']")
    private WebElement smallParcelCheckBox;

    @FindBy(css = "label[for = 'parcelSizeB']")
    private WebElement mediumParcelOption;

    @FindBy(css = "input[id = 'parcelSizeB']")
    private WebElement mediumParcelCheckBox;

    @FindBy(css = "label[for = 'parcelSizeC']")
    private WebElement largeParcelOption;

    @FindBy(css = "input[id = 'parcelSizeC']")
    private WebElement largeParcelCheckBox;

    public ParcelSize(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(this.webDriver, this);
    }

    public void choose(Size size) {
        switch (size) {
            case SMALL: {
                smallParcelOption.click();
                break;
            }
            case MEDIUM: {
                mediumParcelOption.click();
                break;
            }
            case LARGE: {
                largeParcelOption.click();
                break;
            }
        }
    }

    public boolean isSelectedSmallParcel() {
        return smallParcelCheckBox.isSelected();
    }

    public boolean isSelectedMediumParcel() {
        return mediumParcelCheckBox.isSelected();
    }

    public boolean isSelectedLargeParcel() {
        return largeParcelCheckBox.isSelected();
    }

    public enum Size {
        SMALL,
        MEDIUM,
        LARGE
    }
}
