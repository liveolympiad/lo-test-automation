package Tests;

import PageObjectModel.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.Browser.CHROME;

import PageObjectModel.LoginPage;
import Utils.Utils;

@Test
public class SanityTest extends BaseTest {
    @BeforeTest
    public void launchBrowser() {
        browser = CHROME;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // TODO: don't forget to uncomment while doing git push
        //options.addArguments("window-size=1920x1080");
        if (this.browser.equals(CHROME)) {
            System.out.println("launching chrome browser");
            System.setProperty("webdriver.chrome.driver", driverPath);
            driver = new ChromeDriver(options);
        }
        driver.get(baseUrl); // TODO: Learn get() vs navigate().to()
        //driver.navigate().to(baseUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        utils = new Utils(driver);
    }

    @AfterTest
    public void terminateBrowser(){
        if (driver != null) {
            driver.close();
        }
    }

    @AfterMethod
    public void logout() throws InterruptedException {
        if (driver != null) {
            driver.get(baseUrl + "dashboard");
            driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[3]/div/button")).click();
            Thread.sleep(5000);
        }
    }

    //Verify Login Screen (Phone number textbox, Password Textbox & Login button)
//    @BeforeMethod
//    public void verifyHomepageTitle() {
//        String expectedTitle = "LiveOlympiad";
//        String actualTitle = driver.getTitle();
//        Assert.assertEquals(actualTitle, expectedTitle);
//        LoginPage.verifyPageDetails(driver);
//    }

    @Test(priority = 0)
    public void loginNoPhoneNumberEntered() throws InterruptedException {
        //driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[6]/button")).click();
        LoginPage.enterLogin(driver, "");
        LoginPage.enterPassword(driver, "123456");
        LoginPage.clickLogin(driver);
        //Thread.sleep(2000);
        WebElement elem = utils.waitForElement(
                "//*[@id=\"clicked\"]");

        elem.click();
        //Thread.sleep(5000);
        String expected = "Please enter valid phone number";
        //WebElement
        elem = driver.findElement(By.xpath("//*[@id=\"simple-tabpanel-0\"]/div/div/div[2]/div/div[2]"));
        //elem = driver.findElement(By.className(".MuiAlert-icon.css-1l54tgj"));
        String actual = elem.getText();
        Assert.assertEquals(actual, expected, "Error: Please enter valid phone number");
    }

    @Test(priority = 0)
    public void loginIncorrectPasswordEntered() throws InterruptedException {
        //driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[6]/button")).click();
        LoginPage.enterLogin(driver, "9958895489");
        LoginPage.enterPassword(driver, "123455");
        LoginPage.clickLogin(driver);
        //Thread.sleep(2000);
        WebElement elem = utils.waitForElement(
                "//*[@id=\"clicked\"]");
        elem.click();
        //Thread.sleep(5000);
        String expected = "Incorrect password";
        elem = driver.findElement(By.xpath("//*[@id=\"simple-tabpanel-0\"]/div/div/div[2]/div/div[2]"));
        //WebElement elem = driver.findElement(By.xpath("//*[@id="simple-tabpanel-0"]/div/div/div[2]/div/div[2]"));
        String actual = elem.getText();
        Assert.assertEquals(actual, expected, "Error: Incorrect password");
    }

    @Test(priority = 0)
    public void loginUnregisteredNumberEntered() throws InterruptedException {
        //driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[6]/button")).click();
        LoginPage.enterLogin(driver, "9958895499");
        LoginPage.enterPassword(driver, "123456");
        LoginPage.clickLogin(driver);
        Thread.sleep(2000);
        WebElement elem = utils.waitForElement(
                "//*[@id=\"clicked\"]");
        elem.click();
        Thread.sleep(5000);
        //String expected = "Please enter valid phone number";
        //WebElement elem = driver.findElement(By.xpath(""));
        String actual = elem.getText();
        //Assert.assertEquals(actual, expected, "Error: Please enter valid phone number");
    }

    @Test(priority = 0)
    public void loginEmptyDataEntered() throws InterruptedException {
        //driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[6]/button")).click();
        LoginPage.enterLogin(driver, "");
        LoginPage.enterPassword(driver, "");
        LoginPage.clickLogin(driver);
        //Thread.sleep(2000);
        WebElement elem = utils.waitForElement(
                "//*[@id=\"clicked\"]");
        elem.click();
        //Thread.sleep(5000);
        String expected = "Please enter valid phone number";
        elem = driver.findElement(By.xpath("//*[@id=\"simple-tabpanel-0\"]/div/div/div[2]/div/div[2]"));
        String actual = elem.getText();
        Assert.assertEquals(actual, expected, "Error: Please enter valid phone number");
    }

    @Test(priority = 0)
    public void loginInvalidPasswordEntered() throws InterruptedException {
        //driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[6]/button")).click();
        LoginPage.enterLogin(driver, "9958895489");
        LoginPage.enterPassword(driver, "123");
        LoginPage.clickLogin(driver);
        //Thread.sleep(2000);
//        WebElement elem = utils.waitForElement(
//                "//*[@id='root']/div/div[3]/div/div[1]/div[2]/div[2]/div[2]/button");
        WebElement elem = utils.waitForElement(
                "//*[@id=\"clicked\"]");
        elem.click();
        //Thread.sleep(5000);
        String expected = "Please enter valid 6 digit password";
        elem = driver.findElement(By.xpath("//*[@id=\"simple-tabpanel-0\"]/div/div/div[2]/div/div[2]"));
        String actual = elem.getText();
        Assert.assertEquals(actual, expected, "Error: Please enter valid 6 digit password");
    }


    @Test(priority = 0)
    public void loginNoPasswordEntered() throws InterruptedException {
        //driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[6]/button")).click();
        LoginPage.enterLogin(driver, "9958895489");
        LoginPage.enterPassword(driver, "");
        LoginPage.clickLogin(driver);
        //Thread.sleep(2000);
        WebElement elem = utils.waitForElement(
                "//*[@id=\"clicked\"]");
        elem.click();
        //Thread.sleep(5000);
        String expected = "Please enter valid 6 digit password";
        elem = driver.findElement(By.xpath("//*[@id=\"simple-tabpanel-0\"]/div/div/div[2]/div/div[2]"));
        String actual = elem.getText();
        Assert.assertEquals(actual, expected, "Error: Please enter valid 6 digit password");
    }
   // @Test(priority = 0)
//    public void login() throws InterruptedException {
//        LoginPage.enterLogin(driver, "9958895489");
//        LoginPage.enterPassword(driver, "123456");
//        LoginPage.clickLogin(driver);
//        Thread.sleep(2000);
//        WebElement elem = utils.waitForElement(
//                "//*[@id='root']/div/div[3]/div/div[1]/div[2]/div[2]/div[2]/button");
//        elem.click();
//        Thread.sleep(5000);
//        elem = utils.waitForElement(
//                "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div[2]/div[3]/button");
//        elem.click();
//        Thread.sleep(7000);
//        /*utils.waitForElement("//*[@id=\"root\"]/div[3]/div/div[5]/div/div[1]/div/div[1]/div", 5000);*/
//
//    }

    @Test(priority = 0)
    public void login() throws InterruptedException {
        LoginPage.enterLogin(driver, "9958895489");
        LoginPage.enterPassword(driver, "123456");
        LoginPage.clickLogin(driver);
        Thread.sleep(2000);
        WebElement elem = utils.waitForElement("//*[@id=\"root\"]/div/div[2]/div/div/div/div/button[1]/div/div/div");
        elem.click();
        Thread.sleep(2000);

        elem = utils.waitForElement(
                "//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div[1]/div/div/div/div[2]/span[1]");
        elem.click();
        Thread.sleep(2000);
        elem = utils.waitForElement(
                "//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div[1]/div/div/div/div[3]/span[1]");
        elem.click();
        Thread.sleep(2000);
        elem = utils.waitForElement(
                "//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div[1]/div/div/div/div[4]/span[1]");
        elem.click();
        Thread.sleep(2000);
        /*utils.waitForElement("//*[@id=\"root\"]/div[3]/div/div[5]/div/div[1]/div/div[1]/div", 5000);*/
        elem = utils.waitForElement(
                "//*[@id=\"root\"]/div/div[2]/div/div/div/div/button[2]/div/div/div");
        elem.click();
        Thread.sleep(2000);

        //verify button is enabled or not
        elem = utils.waitForElement(
                "//*[@id=\"root\"]/div/div[3]/div/div[2]/div/div[9]/button");
        elem.click();
        Thread.sleep(5000);
        String expected = "Profile submitted successfully";
        //WebElement elem = driver.findElement(By.xpath(""));
        String actual = elem.getText();

        //verify textbox fields in profile
        ProfilePage.enterFullname(driver, "John");
        Thread.sleep(2000);
        ProfilePage.enterRollno(driver, "12");
        Thread.sleep(2000);
//        EnterProfileDetails.enterSection(driver, "D");   //help
//        Thread.sleep(2000);
        ProfilePage.enterEmail(driver, "D@gmail.com");
        Thread.sleep(2000);

        //going back to Practice page
        elem = utils.waitForElement(
                "//*[@id=\"root\"]/div/div[2]/div/div/div/div/button[1]/div/div");
        elem.click();
        Thread.sleep(5000);
        elem = utils.waitForElement(
                "//*[@id='root']/div/div[3]/div/div[1]/div[2]/div[2]/div[2]/button");
        elem.click();
        Thread.sleep(5000);
        elem = utils.waitForElement(
                "//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div[2]/div[3]/button");
        elem.click();
        Thread.sleep(7000);

    }

    @Test(priority = 0)
    public void verifyElements() throws InterruptedException
    {
        boolean phoneNo = driver.findElement(By.xpath("//*[@id=\":r0:\"]")).isDisplayed();
        Thread.sleep(2000);

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


}