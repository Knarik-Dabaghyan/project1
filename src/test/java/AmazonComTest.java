import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AmazonComTest {

    private WebDriver driver;

    @BeforeMethod
    public void testSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");
    }

    @Test
    public void testAmazonCom() {
        HomePage homePage = new HomePage(driver);
        homePage.waitUntilPageLoads();
        String expectedText = "deliver to" + "\n" + "armenia";
        String actualText = driver.findElement(By.xpath("//a[@id='nav-global-location-popover-link']")).getText().toLowerCase();
        Assert.assertEquals(actualText, expectedText, "Actual text is not equal expected text");
        homePage.clickOnFilterElement("//a[@id='nav-hamburger-menu']");
        homePage.clickOnFilterElement("//div[text()='Kindle E-readers & Books']");
        homePage.clickOnFilterElement("//a[text()='Kindle Books']");
        BooksPage booksPage = new BooksPage(driver);
        booksPage.waitUntilPageLoads();
        booksPage.clickOnFilterElement("//input[@aria-label='Search']");
        String expectedSearch = "albert woodfox";
        WebElement searchEditBox = driver.findElement(By.xpath("//input[@aria-label='Search']"));
        searchEditBox.sendKeys("Albert Woodfox" + Keys.ENTER);
        AuthorBooksPage authorBooksPage = new AuthorBooksPage(driver);
        authorBooksPage.waitUntilPageLoads();
        List<WebElement> list = driver.findElements(By.xpath("//span[ text() = 'by ']/following-sibling::a"));
        boolean isContain = false;
        for (int i = 0; i < list.size(); i++) {
            String linkText = list.get(i).getText().toLowerCase();
            if (linkText.contains(expectedSearch)) {
                isContain = true;
                list.get(i).click();
                break;
            }
        }
        Assert.assertTrue(isContain, "There is no that Author's book");
        AuthorPage authorPage = new AuthorPage(driver);
        authorPage.waitUntilPageLoads();
        String textActual = driver.findElement(By.xpath("//span[@id='formatSelectorHeader']")).getText().toLowerCase();
        String textExpected = "books by albert woodfox";
        Assert.assertEquals(textActual, textExpected, "Expected text not equal actual text");
        authorPage.clickOnFilterElement("//span[@id='sortBySelectors']");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='a-dropdown-link' and contains(text() ,'Price: Low to High')]")));
        authorPage.clickOnFilterElement("//a[@class='a-dropdown-link' and contains(text() ,'Price: Low to High')]");
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

        Assert.assertTrue(isLowToHigh, "Prices are not sorted from low to high");

    }

    @AfterMethod
    public void driverQuit() {
        //  driver.quit();
    }

}

