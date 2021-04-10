package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AuthorBooksPage {

    private WebDriverWait wait;

    @FindBy(xpath = "//div[@class='s-main-slot s-result-list s-search-results sg-row']")
    private WebElement authorBooksPageContent;

    @FindBy(xpath = "//span[ text() = 'by ']/following-sibling::a")
    private List<WebElement> authorNameElement;

    public AuthorBooksPage(WebDriver driver) {
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public boolean ContainsTextAndClick(String expectedSearch) {
        List<WebElement> list = authorNameElement;
        boolean isContain = false;
        for (int i = 0; i < list.size(); i++) {
            String linkText = list.get(i).getText().toLowerCase();
            System.out.println(linkText);
            if (linkText.contains(expectedSearch)) {
                list.get(i).click();
                isContain = true;
                break;
            }
        }
        return isContain;
    }

    public void waitUntilPageLoads() {
        wait.until(ExpectedConditions.visibilityOf(authorBooksPageContent));
    }
}
