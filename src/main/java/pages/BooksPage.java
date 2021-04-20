package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BooksPage {

    private WebDriverWait wait;

    @FindBy(xpath = "//input[@aria-label='Search']")
    private WebElement searchElement;

    public BooksPage(WebDriver driver) {
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public void searchElement() {
        searchElement.sendKeys("Albert Woodfox" + Keys.ENTER);
    }

    public void waitUntilPageLoads() {
        wait.until(ExpectedConditions.visibilityOf(searchElement));
    }
}
