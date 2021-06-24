package Pages.MainPage.Components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ParcelSize {
    private final WebDriver webDriver;

    @FindBy(css = "label[for = 'parcelSizeA']")
    private WebElement smallParcelOption;

    @FindBy(css = "label[for = 'parcelSizeB']")
    private WebElement mediumParcelOption;

    @FindBy(css = "label[for = 'parcelSizeC']")
    private WebElement largeParcelOption;

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
        return smallParcelOption.findElement(By.xpath("//preceding-sibling::input[@id='parcelSizeA']")).isSelected();
    }

    public boolean isSelectedMediumParcel() {
        return mediumParcelOption.findElement(By.xpath("//preceding-sibling::input[@id='parcelSizeB']")).isSelected();
    }

    public boolean isSelectedLargeParcel() {
        return largeParcelOption.findElement(By.xpath("//preceding-sibling::input[@id='parcelSizeC']")).isSelected();
    }

    public enum Size {
        SMALL,
        MEDIUM,
        LARGE
    }
}
