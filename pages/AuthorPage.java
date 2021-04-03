package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.LinkedList;
import java.util.List;

public class AuthorPage {
    private WebDriver driver;
    private By authorPageContentLoc = By.xpath("//span[@id='sortBySelectors']");
    private By authorPageTextLoc = By.xpath("//span[@id='formatSelectorHeader']");
    private By sortedByElemLoc = By.xpath("//span[@id='sortBySelectors']");
    private By lowToHighElemLoc = By.xpath("//a[@class='a-dropdown-link' and contains(text() ,'Price: Low to High')]");


    public AuthorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnFilterElement() {
        driver.findElement(sortedByElemLoc).click();
        driver.findElement(lowToHighElemLoc).click();

    }

    public String getElementText() {
        String actualText = driver.findElement(authorPageTextLoc).getText().toLowerCase();
        return actualText;
    }

    public boolean isLowToHighPrice() {
        boolean isLowToHigh = false;
        LinkedList<Double> priceList = new LinkedList<>();
        List<WebElement> priceTextList = driver.findElements(By.xpath("//span[@class='a-size-base a-color-price authorPageCarouselText']"));
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
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(authorPageContentLoc));
        wait.until(ExpectedConditions.visibilityOfElementLocated(authorPageTextLoc));

    }
}
