package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.LinkedList;
import java.util.List;

public class AuthorPage {
    private WebDriverWait wait;

    @FindBy(xpath = "//span[@id='formatSelectorHeader']")
    private WebElement authorPageText;

    @FindBy(xpath = "//span[@id='sortBySelectors']")
    private WebElement sortedByElement;

    @FindBy(xpath = "//a[@class='a-dropdown-link' and contains(text() ,'Price: Low to High')]")
    private WebElement lowToHighElement;

    @FindBy(xpath = "//span[@class='a-size-base a-color-price authorPageCarouselText']")
    private List<WebElement> priceElement;

    public AuthorPage(WebDriver driver) {
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public void clickOnSortedElements() {
        sortedByElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(lowToHighElement));
        lowToHighElement.click();
    }

    public String getElementText() {
        String actualText = authorPageText.getText().toLowerCase();
        return actualText;
    }

    public boolean isLowToHighPrice() {
        boolean isLowToHigh = false;
        LinkedList<Double> priceList = new LinkedList<>();
        List<WebElement> priceTextList = priceElement;
        for (int i = 0; i < priceTextList.size(); i++) {
            String priceProduct = priceTextList.get(i).getText().replaceAll("[^.0-9]", "");
            double actualPrice = Double.parseDouble(priceProduct);
            priceList.add(actualPrice);
        }
        for (int i = 0; i < priceList.size() - 1; i++) {
            if (priceList.get(i) <= priceList.get(i + 1)) {
                isLowToHigh = true;
            } else {
                isLowToHigh = false;
                break;
            }
        }
        return isLowToHigh;
    }

    public void waitUntilPageLoads() {
        wait.until(ExpectedConditions.visibilityOf(authorPageText));
        wait.until(ExpectedConditions.visibilityOf(sortedByElement));


    }
}
