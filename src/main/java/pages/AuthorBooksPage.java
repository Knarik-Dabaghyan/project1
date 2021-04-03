package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AuthorBooksPage {

    private WebDriver driver;
    private By authorBooksPageContentLoc = By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']");
    private By authorNameLoc = By.xpath("//span[ text() = 'by ']/following-sibling::a");

    public AuthorBooksPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isContainText(String expectedSearch) {
        List<WebElement> list = driver.findElements(authorNameLoc);
        boolean isContain = false;
        for (int i = 0; i < list.size(); i++) {
            String linkText = list.get(i).getText().toLowerCase();
            if (linkText.contains(expectedSearch)) {
                isContain = true;
                list.get(i).click();
                break;
            }
        }
        return isContain;
    }

    public void waitUntilPageLoads() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(authorBooksPageContentLoc));
    }
}
