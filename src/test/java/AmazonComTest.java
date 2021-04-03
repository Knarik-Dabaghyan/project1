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
    public void testAmazonCom(){
        HomePage homePage = new HomePage(driver);
        homePage.waitUntilPageLoads();
        String expectedText = "deliver to" + "\n" + "armenia";
        String actualText = homePage.getElementText();
        Assert.assertEquals(actualText, expectedText, "Actual text is not equal expected text");
        homePage.clickOnFilterElement();
        BooksPage booksPage = new BooksPage(driver);
        booksPage.waitUntilPageLoads();
        booksPage.clickOnFilterElement();
        booksPage.searchElement();
        AuthorBooksPage authorBooksPage = new AuthorBooksPage(driver);
        authorBooksPage.waitUntilPageLoads();
        String expectedSearch = "albert woodfox";
        boolean isContain = authorBooksPage.isContainText(expectedSearch);
        Assert.assertTrue(isContain, "There is no book that Author is " + expectedSearch);
        AuthorPage authorPage = new AuthorPage(driver);
        authorPage.waitUntilPageLoads();
        String textExpected = "books by albert woodfox";
        String textActual = authorPage.getElementText();
        Assert.assertEquals(textActual, textExpected, "Expected text not equal actual text");
        authorPage.clickOnFilterElement();
        boolean isLowToHigh = authorPage.isLowToHighPrice();
        Assert.assertTrue(isLowToHigh, "Prices are not sorted from low to high");
    }

    @AfterMethod
    public void driverQuit() {
        driver.quit();
    }

}

