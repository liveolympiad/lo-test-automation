import PageObjectModel.LoginPage;
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
    //private final String baseUrl = "https://app-uat.liveolympiad.org/";
    private final String baseUrl = "https://app.liveolympiad.org/";
    private final String driverPath = "src/test/resources/chromedriver";
    private WebDriver driver;

    private WebElement waitForElementInternal(String elemStr, int sleepInmillis) throws InterruptedException {
        Thread.sleep(sleepInmillis);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By elemLocator = By.xpath(elemStr);
        WebElement elem = driver.findElement(elemLocator);
        // wait till the logout button is visible
        wait.until(ExpectedConditions.visibilityOf(elem));
        return elem;
    }

    private WebElement waitForElement(String elemStr) throws InterruptedException {
        return waitForElementInternal(elemStr, 10000);
    }

    private WebElement waitForElement(String elemStr, int sleepInmillis) throws InterruptedException {
        return waitForElementInternal(elemStr, sleepInmillis);
    }

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
        String expected = "Please enter valid phone number";
        WebElement elem = driver.findElement(By.xpath(""));
        String actual = elem.getText();
        Assert.assertEquals(actual, expected, "Error: Please enter valid phone number");
    }

    @Test(priority = 0)
    public void login() throws InterruptedException {
        LoginPage.enterLogin(driver, "9958895489");
        LoginPage.enterPassword(driver, "123456");
        driver.findElement(
                By.xpath(
                        "/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[6]/button"))
                .click();
        WebElement elem = waitForElement("//*[@id=\"root\"]/div/div[3]/div/div[1]/div[2]/div[2]/div[2]/button");
        elem.click();
        elem = waitForElement("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div[3]/div[3]/button");
        elem.click();
        waitForElement("//*[@id=\"root\"]/div[3]/div/div[5]/div/div[1]/div/div[1]/div", 15000);
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