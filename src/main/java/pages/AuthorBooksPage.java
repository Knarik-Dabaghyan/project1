package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorBooksPage {

    private WebDriver driver;
    private By authorBooksPageContentLoc = By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']");

    public AuthorBooksPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnFilterElement(String xpath) {
        driver.findElement(By.xpath(xpath)).click();

    }

    public void waitUntilPageLoads() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(authorBooksPageContentLoc));
    }
}
