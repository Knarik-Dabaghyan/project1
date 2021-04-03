package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private By locationElementLoc = By.xpath("//a[@id='nav-global-location-popover-link']");
    private By allElementLoc = By.xpath("//a[@id='nav-hamburger-menu']");
    private By kindleEReadersLoc = By.xpath("//div[text()='Kindle E-readers & Books']");
    private By kindleBooksLoc = By.xpath("//a[text()='Kindle Books']");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getElementText() {
        String actualText = driver.findElement(locationElementLoc).getText().toLowerCase();
        return actualText;
    }

    public void clickOnFilterElement() {
        driver.findElement(allElementLoc).click();
        driver.findElement(kindleEReadersLoc).click();
        driver.findElement(kindleBooksLoc).click();
    }

    public void waitUntilPageLoads() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(allElementLoc));
    }
}
