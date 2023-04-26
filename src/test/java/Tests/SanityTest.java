package Tests;

import PageObjectModel.ForgotPasswordPage;
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
        //options.addArguments("--headless", "--disable-gpu", "window-size=1920x1080");  // TODO: don't forget to uncomment while doing git push
        //options.addArguments("window-size=1920x1080");
        options.addArguments("--remote-allow-origins=*");

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
            String elemLoc = "//*[@id=\"root\"]/div/div[1]/div/div[3]/div/button";
            if (driver.findElements(By.xpath(elemLoc)).size() != 0) {
                driver.findElement(By.xpath(elemLoc)).click();
                Thread.sleep(5000);
                //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                WebElement elem = utils.waitForElement(elemLoc);
//                elem.click();
            }
        }
    }


    @Test(priority = 0)
    public void loginNoPhoneNumberEntered() throws InterruptedException {
        //driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[6]/button")).click();
        LoginPage.enterLogin(driver, "");
        LoginPage.enterPassword(driver, "123455");
        LoginPage.clickLogin(driver);
        //Thread.sleep(2000);
//        WebElement elem = utils.waitForElement(
//                "//*[@id=\"clicked\"]");
//
//        elem.click();
        //Thread.sleep(5000);
        String expected = "Please enter valid phone number";
        //WebElement
        WebElement elem = driver.findElement(By.xpath("//*[@id=\"simple-tabpanel-0\"]/div/div/div[2]/div/div[2]"));
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
//        //Thread.sleep(2000);
//        WebElement elem = utils.waitForElement(
//                "//*[@id=\"clicked\"]");
//        elem.click();
        //Thread.sleep(5000);
        String expected = "Incorrect password";
        WebElement elem = driver.findElement(By.xpath("//*[@id=\"simple-tabpanel-0\"]/div/div/div[2]/div/div[2]"));
        String actual = elem.getText();
        Assert.assertEquals(actual, expected, "Error: Incorrect password");

    }

    @Test(priority = 0)
    public void loginUnregisteredNumberEntered() throws InterruptedException {
        //driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[6]/button")).click();
        LoginPage.enterLogin(driver, "9953895499");
        LoginPage.enterPassword(driver, "123456");
        LoginPage.clickLogin(driver);
        Thread.sleep(2000);
        String expected = "Sign Up";
        WebElement titleheading = driver.findElement(By.xpath("//div[@class=\"MuiBox-root css-10ay245\"]"));
        String actual = titleheading.getText();
        Assert.assertEquals(actual, expected);
        System.out.println(titleheading);
    }

    @Test(priority = 0)
    public void loginEmptyDataEntered() throws InterruptedException {
        //driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[6]/button")).click();
        LoginPage.enterLogin(driver, "");
        LoginPage.enterPassword(driver, "");
        LoginPage.clickLogin(driver);
        //Thread.sleep(2000);
//        WebElement elem = utils.waitForElement(
//                "//*[@id=\"clicked\"]");
//        elem.click();
        //Thread.sleep(5000);
        String expected = "Please enter valid phone number";
        WebElement elem = driver.findElement(By.xpath("//*[@id=\"simple-tabpanel-0\"]/div/div/div[2]/div/div[2]"));
        String actual = elem.getText();
        Assert.assertEquals(actual, expected, "Error: Please enter valid phone number");

    }

    @Test(priority = 0)
    public void loginInvalidPasswordEntered() throws InterruptedException {
        //driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[6]/button")).click();
        LoginPage.enterLogin(driver, "9958895489");
        LoginPage.enterPassword(driver, "123");
        LoginPage.clickLogin(driver);
        String expected = "Please enter valid 6 digit password";
        WebElement elem = driver.findElement(By.xpath("//*[@id=\"simple-tabpanel-0\"]/div/div/div[2]/div/div[2]"));
        String actual = elem.getText();
        Assert.assertEquals(actual, expected, "Error: Please enter valid 6 digit password");

    }


    @Test(priority = 0)
    public void loginNoPasswordEntered() throws InterruptedException {
        LoginPage.enterLogin(driver, "9958895489");
        LoginPage.enterPassword(driver, "");
        LoginPage.clickLogin(driver);

        String expected = "Please enter valid 6 digit password";
        WebElement elem = driver.findElement(By.xpath("//*[@id=\"simple-tabpanel-0\"]/div/div/div[2]/div/div[2]"));
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
        //WebElement elem = utils.waitForElement("//*[@id=\"clicked\"]");
        //WebElement elem = driver.findElement(By.className("MuiTouchRipple-root css-w0pj6f"));
        //elem.click();
        Thread.sleep(2000);

        WebElement elem = utils.waitForElement(
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
    public void forgotPasswordPage() throws InterruptedException {
        WebElement verifyForgotpw = driver.findElement(By.linkText("Forgot Password?"));
        Assert.assertTrue(verifyForgotpw.isEnabled(), "Forgot Password is active");
        verifyForgotpw.click();
        System.out.println(driver.getCurrentUrl());

        //verify title
        String expected = "Forgot Password?";
        WebElement titleheading = driver.findElement(By.xpath("//div[@class=\"MuiBox-root css-1w1n69t\"]"));
        String actual = titleheading.getText();
        Assert.assertEquals(actual, expected);
        Thread.sleep(6000);

        String expectedtext = "No worries, You can reset your password by verifying your Phone Number with an OTP.";
        WebElement text = driver.findElement(By.xpath("//div[@class=\"MuiBox-root css-18ailey\"]"));
        String actualText = text.getText();
        Assert.assertEquals(actualText, expectedtext);

        WebElement phoneNo = driver.findElement(By.xpath("//div[@class=\"MuiFormControl-root MuiTextField-root css-1wptnvt\"]"));
        Assert.assertTrue(phoneNo.isEnabled(), "Phone number field is enabled");

        WebElement button = driver.findElement(By.xpath("//button[@id=\"clicked\"]"));
        Assert.assertTrue(button.isEnabled(), "Next button is enabled");

    }

    @Test(priority = 0)
    public void forgotpwEmptyNo() throws InterruptedException {

        ForgotPasswordPage.clickForgotPassword(driver);

        ForgotPasswordPage.forgotPasswordFields(driver, "");
        ForgotPasswordPage.clickNext(driver);

        String expected = "No User exists with given email or phone number";
        WebElement text = driver.findElement(By.xpath("//div[@class=\"MuiAlert-message css-1xsto0d\"]"));
        String actual = text.getText();
        Assert.assertEquals(actual, expected);

    }

    @Test(priority = 0)
    public void forgotpwUnregisteredNo() throws InterruptedException {
        ForgotPasswordPage.clickForgotPassword(driver);

        ForgotPasswordPage.forgotPasswordFields(driver, "8729933699");
        ForgotPasswordPage.clickNext(driver);

        String expected = "No User exists with given email or phone number";
        WebElement text = driver.findElement(By.xpath("//div[@class=\"MuiAlert-message css-1xsto0d\"]"));
        String actual = text.getText();
        Assert.assertEquals(actual, expected);

    }

    @Test(priority = 0)
    public void forgotpwInvalidNo() throws InterruptedException {
        ForgotPasswordPage.clickForgotPassword(driver);

        ForgotPasswordPage.forgotPasswordFields(driver, "123456");
        ForgotPasswordPage.clickNext(driver);

        String expected = "No User exists with given email or phone number";
        WebElement text = driver.findElement(By.xpath("//div[@class=\"MuiAlert-message css-1xsto0d\"]"));
        String actual = text.getText();
        Assert.assertEquals(actual, expected);

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