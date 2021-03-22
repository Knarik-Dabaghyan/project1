import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class seleniumTest {


    private WebDriver driver;

    @Test
    public void testSelenium() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        this.driver = driver;
        driver.manage().window().maximize();
        driver.get("https://www.selenium.dev/");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@href='/downloads']")).click();
        Thread.sleep(3000);
        WebElement versionElement = driver.findElement(By.xpath("//a[@href='https://selenium-release.storage.googleapis.com/3.141/selenium-server-standalone-3.141.59.jar']"));
        String actualVersion = versionElement.getText();
        String expectedVersion = "3.141.59";
        Assert.assertEquals(actualVersion, expectedVersion);
        System.out.println("Version Test pass");
        WebElement elem = driver.findElement(By.xpath("//input[@name='search']"));
        String expectedSearch = "selenium webdriver";
        elem.sendKeys(expectedSearch + Keys.ENTER);
        Thread.sleep(3000);

        ArrayList<String> list = new ArrayList<String>();
        list.add("//a[@href='https://www.seleniumhq.org/']");
        list.add("//a[@href='http://www.seleniumhq.org/projects/ide/' ]");
        list.add("//a[@href='https://www.seleniumhq.org/downloads']");
        list.add("//a[@href='https://www.seleniumhq.org/selenium-ide/']");
        list.add("//a[@href='https://www.seleniumhq.org/about/license.jsp']");
        list.add("//a[@href='http://www.seleniumhq.org/docs/']");
        list.add("//a[@href='https://www.seleniumhq.org/selenium-ide/docs/en/introduction/code-export']");
        list.add("//a[@href='https://www.seleniumhq.org/about/history.jsp']");
        list.add("//a[@href='https://www.seleniumhq.org/ecosystem/']");
        list.add("//a[@href='https://www.seleniumhq.org/selenium-ide/docs/en/introduction/command-line-runner/']");
        boolean b = false;
        for (int i = 0; i < list.size(); i++) {
            String LinkText = driver.findElement(By.xpath(list.get(i))).getText().toLowerCase();
            if (LinkText.contains(expectedSearch)) {
                b = true;
                break;
            }
        }
        Assert.assertTrue(b);
        System.out.println("Find link that contains 'selenium webdriver'");
        driver.quit();
    }
}








