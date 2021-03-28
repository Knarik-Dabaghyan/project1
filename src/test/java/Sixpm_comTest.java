import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class Sixpm_comTest {
    @Test
    public void checkSixpm_com(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.6pm.com/");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/c/accessories']")));
        WebElement accsessoriesElem = driver.findElement(By.xpath("//a[@href='/c/accessories']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(accsessoriesElem).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Aviators']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='searchPage']//article")));
        Random random = new Random();
        List<WebElement> productElems = driver.findElements(By.xpath("//div[@id='searchPage']//article"));
        int size = productElems.size();
        int randomNumber = random.nextInt(size);
        productElems.get(randomNumber).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add to Shopping Bag']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='Xk-z']")));
        String expectedModel = driver.findElement(By.xpath("//span[@class='Xk-z']")).getText();
        WebElement productAmount = driver.findElement(By.xpath("//em[@class='yh-z Bh-z']"));
        String amountOfProduct = productAmount.getText().replaceAll("[^\\,0-9]", "").replaceAll(",", ".");
        double expectedAmount = Double.parseDouble(amountOfProduct);
        driver.findElement(By.xpath("//button[@class='iz-z']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='ra-z']"))).click();
        String actualModel = driver.findElement(By.xpath("//span[@class='Xk-z']")).getText();
        WebElement amount = driver.findElement(By.xpath("//em[@class='yh-z Bh-z']"));
        String amountProd = amount.getText().replaceAll("[^\\,0-9]", "").replaceAll(",", ".");
        double actualAmount = Double.parseDouble(amountProd);
        Assert.assertEquals(expectedModel, actualModel, "expected model not equal actual model");
        Assert.assertEquals(expectedAmount, actualAmount, "expected amount not equal actual amount");
        driver.findElement(By.xpath("//select[@name='quantity']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[text()='Remove']"))).click();
        driver.quit();
    }
}
