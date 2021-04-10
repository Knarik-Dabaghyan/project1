package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriverWait wait;

    @FindBy(xpath = "//a[@id='nav-global-location-popover-link']")
    private WebElement locationElement;

    @FindBy(xpath = "//a[@id='nav-hamburger-menu']")
    private WebElement allElement;

    @FindBy(xpath = "//div[text()='Kindle E-readers & Books']")
    private WebElement kindleEReadersElement;

    @FindBy(xpath = "//a[text()='Kindle Books']")
    private WebElement kindleBooksElement;

    public HomePage(WebDriver driver) {
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public String getElementText() {
        String actualText = locationElement.getText().toLowerCase();
        return actualText;
    }

    public void clickOnElements() {
        allElement.click();
        kindleEReadersElement.click();
        kindleBooksElement.click();
    }

    public void waitUntilPageLoads() {
        wait.until(ExpectedConditions.visibilityOf(allElement));
    }
}
