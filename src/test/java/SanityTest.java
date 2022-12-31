import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

@Test
public class SanityTest {
    public String baseUrl = "https://app.liveolympiad.org/";
    String driverPath = "src/test/resources/chromedriver";
    public WebDriver driver;
    public String expected = null;
    public String actual = null;
    @BeforeTest
    public void launchBrowser() {
        System.out.println("launching chrome browser");
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }

    @BeforeMethod
    public void verifyHomepageTitle() {
        String expectedTitle = "LiveOlympiad";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }
    @Test(priority = 0)
    public void loginNoPhoneNumberEntered(){
        driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[6]/button")).click();
        expected = "Please enter valid phone number";
        WebElement elem = driver.findElement(By.xpath(""));
        String actual = elem.getText();
        Assert.assertEquals(actual, expected, "Error: Please enter valid phone number");
    }

    @Test(priority = 0)
    public void login(){
        driver.findElement(By.xpath(
                "/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[3]/div/div/div/input"))
                .sendKeys("9958895489");
        driver.findElement(By.xpath(
                "/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[4]/div/div/div/input"))
                .sendKeys("123456");
        driver.findElement(
                By.xpath(
                        "/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[6]/button"))
                .click();
        waitForElement("//*[@id=\"root\"]/div/div[1]/div/div[3]/div/button");
    }

    private void waitForElement(String elemStr) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By logoutButton = By.xpath(elemStr);

        // wait till the logout button is visible
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(logoutButton)));
    }
    /*@Test (priority = 1)
    public void support() {
        driver.findElement(By.linkText("SUPPORT")).click() ;
        expected = "Under Construction: Mercury Tours";
        actual = driver.getTitle();
        Assert.assertEquals(actual, expected);
    }*/
    /*@AfterMethod
    public void goBackToHomepage ( ) {
        driver.findElement(By.linkText("Home")).click() ;
    }*/

    @AfterTest
    public void terminateBrowser(){
        if (driver != null) {
            driver.close();
        }
    }
}