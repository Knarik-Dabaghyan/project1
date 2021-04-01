package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthorPage {
    private WebDriver driver;
    private By authorPageContentLoc = By.xpath("//span[@id='sortBySelectors']");
    private By authorPageTextLoc = By.xpath("//span[@id='formatSelectorHeader']");


    public AuthorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnFilterElement(String xpath) {
        driver.findElement(By.xpath(xpath)).click();

    }

    public void waitUntilPageLoads() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(authorPageContentLoc));
        wait.until(ExpectedConditions.visibilityOfElementLocated(authorPageTextLoc));

    }
}
