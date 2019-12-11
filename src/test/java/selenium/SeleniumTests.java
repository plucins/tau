package selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTests {

    private WebDriver driver;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    @Test
    public void testNavBar() throws InterruptedException {
        driver.get("http://automationpractice.com/");
        driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/a")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("id_category=3"));

        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("id_category=8"));
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[3]/a")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("id_category=5"));
    }

    @Test
    public void testSignInForm() throws InterruptedException {
        driver.get("http://automationpractice.com/");
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
        Thread.sleep(1500);

        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("testowy@email.com");
        driver.findElement(By.cssSelector("#SubmitLogin")).click();
        Thread.sleep(300);
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"center_column\"]/div[1]/ol/li")).getText(), "Password is required.");
    }

    @Test
    public void testRegisterForm() throws InterruptedException {
        driver.get("http://automationpractice.com/");
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"email_create\"]")).sendKeys("asdfasdafasd@asdaasfdasd.pl");
        driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]")).click();
        Thread.sleep(1500);

        driver.findElement(By.xpath("//*[@id=\"id_gender1\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"customer_firstname\"]")).sendKeys("Adam");
        driver.findElement(By.xpath("//*[@id=\"customer_lastname\"]")).sendKeys("Adamowicz");
        driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys("123456789!!@#$%");

        driver.findElement(By.xpath("//*[@id=\"days\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"days\"]")).sendKeys("3");
        driver.findElement(By.xpath("//*[@id=\"days\"]")).sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//*[@id=\"months\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"months\"]")).sendKeys("j");
        driver.findElement(By.xpath("//*[@id=\"months\"]")).sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//*[@id=\"years\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"years\"]")).sendKeys("1");
        driver.findElement(By.xpath("//*[@id=\"years\"]")).sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//*[@id=\"firstname\"]")).sendKeys("Adam");
        driver.findElement(By.xpath("//*[@id=\"lastname\"]")).sendKeys("Adamowicz");

        driver.findElement(By.xpath("//*[@id=\"address1\"]")).sendKeys("asdfasdasdasd");
        driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys("Gdańsk");

        driver.findElement(By.xpath("//*[@id=\"id_state\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"id_state\"]")).sendKeys("A");
        driver.findElement(By.xpath("//*[@id=\"id_state\"]")).sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//*[@id=\"postcode\"]")).sendKeys("00001");

        driver.findElement(By.xpath("//*[@id=\"phone_mobile\"]")).sendKeys("53071777");

        driver.findElement(By.xpath("//*[@id=\"submitAccount\"]")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a/span")).getText(), "Adam Adamowicz");
    }

    @After
    public void clean() {
        driver.close();
    }
}
