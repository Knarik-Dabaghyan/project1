import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SixpmcomTest {
    private WebDriver driver;

    @BeforeMethod
    public void driver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void checkSixpmcom() {
        driver.get("https://www.6pm.com/");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/c/accessories']")));
        WebElement accsessoriesElem = driver.findElement(By.xpath("//a[@href='/c/accessories']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(accsessoriesElem).perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Aviators']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='searchPage']//article")));
        Random random = new Random();
        List<WebElement> productElems = driver.findElements(By.xpath("//div[@id='searchPage']//article"));
        int size = productElems.size();
        int randomNumber = random.nextInt(size);
        productElems.get(randomNumber).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add to Shopping Bag']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='Xk-z']")));
        String expectedModel = driver.findElement(By.xpath("//span[@class='Xk-z']")).getText();
        WebElement productPrice = driver.findElement(By.xpath("//em[@class='yh-z Bh-z']"));
        String priceOfProduct = productPrice.getText().replaceAll("[^.0-9]", "");
        double expectedPrice = Double.parseDouble(priceOfProduct);
        driver.findElement(By.xpath("//button[@class='iz-z']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='ra-z']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='sz-z']")));
        String actualModel = driver.findElement(By.xpath("//span[@class='Xk-z']")).getText();
        WebElement price = driver.findElement(By.xpath("//em[@class='yh-z Bh-z']"));
        String priceProduct = price.getText().replaceAll("[^.0-9]", "");
        double actualPrice = Double.parseDouble(priceProduct);
        Assert.assertEquals(expectedModel, actualModel, "Expected model not equal actual model");
        Assert.assertEquals(expectedPrice, actualPrice, "Expected amount not equal actual amount");
        driver.findElement(By.xpath("//select[@name='quantity']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[text()='Remove']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='NB-z']")));

    }

    @AfterMethod
    public void driverQuit() {
        driver.quit();
    }
}