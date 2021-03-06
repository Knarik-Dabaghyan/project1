import groovy.json.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AmazonComTest {

    private WebDriver driver;

    @BeforeMethod
    public void testSetup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");
    }

    @Test(dataProvider = "amazonDataProvider", dataProviderClass = DataGenerator.class)
    public void testAmazonCom(String expectedSearch, String textExpected) {
        HomePage homePage = new HomePage(driver);
        homePage.waitUntilPageLoads();
        String expectedText = "deliver to" + "\n" + "armenia";
        String actualText = homePage.getElementText();
        Assert.assertEquals(actualText, expectedText, "Actual text is not equal expected text");
        homePage.clickOnElements();
        BooksPage booksPage = new BooksPage(driver);
        booksPage.waitUntilPageLoads();
        booksPage.searchElement();
        AuthorBooksPage authorBooksPage = new AuthorBooksPage(driver);
        authorBooksPage.waitUntilPageLoads();
        boolean isContain = authorBooksPage.containsTextAndClick(expectedSearch);
        Assert.assertTrue(isContain, "There is no book that Author is " + expectedSearch);
        AuthorPage authorPage = new AuthorPage(driver);
        authorPage.waitUntilPageLoads();
        String textActual = authorPage.getElementText();
        Assert.assertEquals(textActual, textExpected, "Expected text is: " + textExpected + "and not equal actual text that is :" + textActual);
        authorPage.clickOnSortedElements();
        boolean isLowToHigh = authorPage.isLowToHighPrice();
        Assert.assertTrue(isLowToHigh, "Prices are not sorted from low to high");
    }

    @AfterMethod
    public void driverQuit() {
        driver.quit();
    }

}

