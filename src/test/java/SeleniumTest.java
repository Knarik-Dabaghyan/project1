import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SeleniumTest {


    private WebDriver driver;

    @Test
    public void testSelenium() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        this.driver = driver;
        driver.manage().window().maximize();
        driver.get("https://google.com");
        Thread.sleep(3000);
        WebElement element = driver.findElement(By.name("q"));
        String expectedName = "selenium.dev";
        element.sendKeys(expectedName + Keys.ENTER);
        driver.findElement(By.xpath("//a[@href='https://www.selenium.dev/']")).click();
        driver.findElement(By.xpath("//a[@href='/downloads']")).click();
        Thread.sleep(3000);
        WebElement versionElement = driver.findElement(By.xpath("//a[text()='3.141.59']"));
        String actualVersion = versionElement.getText();
        String expectedVersion = "3.141.59";
        Assert.assertEquals(actualVersion, expectedVersion);
        WebElement elem = driver.findElement(By.xpath("//input[@name='search']"));
        String expectedSearch = "selenium webdriver";
        elem.sendKeys(expectedSearch + Keys.ENTER);
        driver.findElement(By.partialLinkText("Selenium WebDriver"));
        Thread.sleep(3000);
        //  driver.quit();

    }

}