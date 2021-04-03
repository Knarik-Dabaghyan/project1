package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BooksPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[@aria-label='Search']")
    WebElement searchElementLoc;

    public BooksPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void clickOnFilterElement() {
        searchElementLoc.click();

    }

    public void searchElement() {
        searchElementLoc.sendKeys("Albert Woodfox" + Keys.ENTER);

    }

    public void waitUntilPageLoads() {
        wait.until(ExpectedConditions.visibilityOf(searchElementLoc));
    }
}
