package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;

    private By homepageContentLoc = By.xpath("//a[@id='nav-hamburger-menu']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnFilterElement(String filterText) {
        driver.findElement(By.xpath(filterText)).click();
    }

    public void waitUntilPageLoads() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(homepageContentLoc));
    }
}
