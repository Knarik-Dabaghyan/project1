package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//a[@id='nav-global-location-popover-link']")
    WebElement locationElementLoc;

    @FindBy(xpath = "//a[@id='nav-hamburger-menu']")
    WebElement allElementLoc;

    @FindBy(xpath = "//div[text()='Kindle E-readers & Books']")
    WebElement kindleEReadersLoc;

    @FindBy(xpath = "//a[text()='Kindle Books']")
    WebElement kindleBooksLoc;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public String getElementText() {
        String actualText = locationElementLoc.getText().toLowerCase();
        return actualText;
    }

    public void clickOnFilterElement() {
        allElementLoc.click();
        kindleEReadersLoc.click();
        kindleBooksLoc.click();
    }

    public void waitUntilPageLoads() {
        wait.until(ExpectedConditions.visibilityOf(allElementLoc));
    }
}
