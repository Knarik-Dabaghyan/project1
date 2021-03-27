import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Greetz_nlTest {
    private WebDriver driver;

    @BeforeMethod
    public void login() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.greetz.nl/auth/login");
        Thread.sleep(3000);
        WebElement loginFormElem = driver.findElement(By.id("loginForm"));
        String email = "knar@knar.com";
        String pass = "Knar099@";
        loginFormElem.findElement(By.name("email")).sendKeys(email);
        loginFormElem.findElement(By.name("password")).sendKeys(pass);
        driver.findElement(By.id("login-cta")).click();
        Thread.sleep(6000);
    }

    @Test  (priority = 1)
    public void checkFavorite() throws InterruptedException {
        driver.get("https://www.greetz.nl/ballonnen/denken-aan");
        Thread.sleep(7000);
        driver.findElement(By.xpath("//div[@class = 'b-products-grid__item'][1]//div[@class ='b-favourite']")).click();
        Thread.sleep(7000);
        driver.findElement(By.xpath("//a[@class='page-header__navigation-item item-user'] ")).click();
        Thread.sleep(7000);
        driver.findElement(By.xpath("//span[text()='Favorieten']")).click();
        Thread.sleep(7000);
        driver.findElement(By.xpath("//img[@loader-class='b-loader']")).click();
        Thread.sleep(7000);
        String actualName = driver.findElement(By.xpath("//h1[@class='label-large giftdetails--title productdetails--summary--title']")).getText();
        String expectedName = "Ballon 'Just because you're awesome'";
        Assert.assertEquals(actualName, expectedName,"Not equal expected Name");
        double expectedAmount=12.99;
        WebElement productAmount = driver.findElement(By.xpath(" //span[@data-qa-ref='normal-price']"));
        String amountOfProduct = productAmount.getText().replaceAll("[^\\,0-9]", "").replaceAll(",", ".");
        double actualAmount = Double.parseDouble(amountOfProduct);
        Assert.assertEquals(actualAmount,expectedAmount,"Not equal expected Amount");
        driver.findElement(By.xpath("//div[@class='productdetails-favorite favorite_active']")).click();
    }
    @Test  (priority = 0)
    public void checkTotalAmount() throws InterruptedException{
        driver.get("https://www.greetz.nl/kaarten/denken-aan");
        Thread.sleep(6000);
        driver.findElement(By.xpath("//div[@class='b-products-grid__item'][4]")).click();
        Thread.sleep(6000);

        WebElement quantityEditBox = driver.findElement(By.xpath("//input[@data-qa-ref='product-amount']"));
        quantityEditBox.clear();
        quantityEditBox.sendKeys(Keys.NUMPAD2);
        Thread.sleep(6000);

        WebElement productAmount = driver.findElement(By.xpath(" //span[@data-qa-ref='normal-price']"));
        String amountOfProduct = productAmount.getText().replaceAll("[^\\,0-9]", "").replaceAll(",", ".");
        double amount = Double.parseDouble(amountOfProduct);

        WebElement productTotalAmount = driver.findElement(By.xpath("//div[@class='price-total' and contains(text(), '5,90')]"));
        String totalAmount = productTotalAmount.getText().replaceAll("[^\\,0-9]", "").replaceAll(",", ".");
        double amountTotal = Double.parseDouble(totalAmount);
        double total = amount * 2;
        Assert.assertEquals(total, amountTotal, "Not equal");


    }
    @AfterMethod
    public void quit() throws InterruptedException{
        driver.quit();
    }


}

