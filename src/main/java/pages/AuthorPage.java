package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.LinkedList;
import java.util.List;

public class AuthorPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//span[@id='formatSelectorHeader']")
    WebElement authorPageTextLoc;

    @FindBy(xpath = "//span[@id='sortBySelectors']")
    WebElement sortedByElemLoc;

    @FindBy(xpath = "//a[@class='a-dropdown-link' and contains(text() ,'Price: Low to High')]")
    WebElement lowToHighElemLoc;

    @FindBy(xpath = "//span[@class='a-size-base a-color-price authorPageCarouselText']")
    List<WebElement> priceElementLoc;

    public AuthorPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void clickOnFilterElement() {
        sortedByElemLoc.click();
        lowToHighElemLoc.click();
    }

    public String getElementText() {
        String actualText = authorPageTextLoc.getText().toLowerCase();
        return actualText;
    }

    public boolean isLowToHighPrice() {
        boolean isLowToHigh = false;
        LinkedList<Double> priceList = new LinkedList<>();
        List<WebElement> priceTextList = priceElementLoc;
        for (int i = 0; i < priceTextList.size(); i++) {
            String priceProduct = priceTextList.get(i).getText().replaceAll("[^.0-9]", "");
            double actualPrice = Double.parseDouble(priceProduct);
            priceList.add(actualPrice);
        }
        for (int i = 0; i < priceList.size() - 1; i++) {
            if (priceList.get(i) <= priceList.get(i + 1)) {
                isLowToHigh = true;

            }
        }

        return isLowToHigh;
    }

    public void waitUntilPageLoads() {
        wait.until(ExpectedConditions.visibilityOf(authorPageTextLoc));
        wait.until(ExpectedConditions.visibilityOf(sortedByElemLoc));

    }
}
