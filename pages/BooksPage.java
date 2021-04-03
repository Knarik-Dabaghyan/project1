package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BooksPage {

    private WebDriver driver;

    private By searchElementLoc = By.xpath("//input[@aria-label='Search']");

    public BooksPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnFilterElement() {
        driver.findElement(searchElementLoc).click();

    }

    public void searchElement() {
        WebElement searchEditBox = driver.findElement(searchElementLoc);
        searchEditBox.sendKeys("Albert Woodfox" + Keys.ENTER);
    }

    public void waitUntilPageLoads() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchElementLoc));
    }
}
