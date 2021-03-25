import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class SeleniumTest {


    @Test
    public void testSelenium() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.selenium.dev/");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//nav[ @id='navbar']//a[@href='/downloads' ]")).click();
        Thread.sleep(3000);
        WebElement versionElement = driver.findElement(By.xpath("//p[text()='Latest stable version ']//a[@href='https://selenium-release.storage.googleapis.com/3.141/selenium-server-standalone-3.141.59.jar'] "));
        String actualVersion = versionElement.getText();
        String expectedVersion = "3.141.59";
        Assert.assertEquals(actualVersion, expectedVersion);
        System.out.println("Version Test pass");
        WebElement searchEditBox = driver.findElement(By.xpath("//input[@name='search']"));
        String expectedSearch = "selenium webdriver";
        searchEditBox.sendKeys(expectedSearch + Keys.ENTER);
        Thread.sleep(3000);
        LinkedList<String> list = new LinkedList<>();
        list.add("//div[@class='gs-title']//a[@href='https://www.seleniumhq.org/']");
        list.add("//div[@class='gs-title']//a[@href='http://www.seleniumhq.org/projects/ide/' ]");
        list.add("//div[@class='gs-title']//a[@href='https://www.seleniumhq.org/downloads']");
        list.add("//div[@class='gs-title']//a[@href='https://www.seleniumhq.org/selenium-ide/']");
        list.add("//div[@class='gs-title']//a[@href='https://www.seleniumhq.org/about/license.jsp']");
        list.add("//div[@class='gs-title']//a[@href='http://www.seleniumhq.org/docs/']");
        list.add("//div[@class='gs-title']//a[@href='https://www.seleniumhq.org/selenium-ide/docs/en/introduction/code-export']");
        list.add("//div[@class='gs-title']//a[@href='https://www.seleniumhq.org/about/history.jsp']");
        list.add("//div[@class='gs-title']//a[@href='https://www.seleniumhq.org/ecosystem/']");
        list.add("//div[@class='gs-title']//a[@href='https://www.seleniumhq.org/selenium-ide/docs/en/introduction/command-line-runner/']");
        boolean isContain = false;
        for (int i = 0; i < list.size(); i++) {
            String linkText = driver.findElement(By.xpath(list.get(i))).getText().toLowerCase();
            if (linkText.contains(expectedSearch)) {
                isContain = true;
                break;
            }
        }
        if (isContain) {
            System.out.println("Find link that contains 'selenium webdriver'");
        } else {
            System.out.println("there is no link that contains 'selenium webdriver'");
        }
        Assert.assertTrue(isContain);
        driver.quit();
    }
}








