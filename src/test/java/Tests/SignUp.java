package Tests;
import PageObjectModel.*;
import com.beust.ah.A;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.Browser.CHROME;

import Utils.Utils;
import org.testng.asserts.SoftAssert;

@Test
public class SignUp extends BaseTest {
    @BeforeTest
    public void launchBrowser() {
        browser = CHROME;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "window-size=1920x1080");  // TODO: don't forget to uncomment while doing git push
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
    public void terminateBrowser() {
        driver.close();
    }

    @Test
    public void verifySignUpButton() throws InterruptedException {
        WebElement signupbtn = driver.findElement(By.cssSelector("button#scrollable-auto-tab-1"));
        try {
            System.out.println("PASS: ELEMENT IS PRESENT");
        } catch (Exception e) {
            System.out.println("FAIL: ELEMENT NOT PRESENT");
        }

        Thread.sleep(2000);

        signupbtn.click();

        //driver.findElement(By.xpath("//div[@class=\"MuiBox-root css-10ay245\"]"));
        String actualTitle = driver.getTitle();
        String expectedTitle = "LiveOlympiad";
        Assert.assertEquals(expectedTitle,actualTitle);

        String su = signupbtn.getText();
        System.out.println(su);
        Thread.sleep(2000);
        //signupbtn.click();

        Thread.sleep(3000);
//        SignupPage.enterSignup(driver,"");
//        try {
//
//            System.out.println("PASS: ELEMENT IS PRESENT");
//        } catch (Exception e) {
//            System.out.println("FAIL: ELEMENT NOT PRESENT");
//        }
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id=':r2:']")).isEnabled(),"Verify Phone no field is enabled");
        Assert.assertTrue(driver.findElement(By.xpath("//button[@id='clicked']")).isEnabled(),"Verify signup button is enabled");

        Thread.sleep(2000);
    }


    @Test

    public void verifySignUpRegisteredNo() throws InterruptedException {
        SignupPage.enterSignup(driver,"9958895489");
        SignupPage.clickSignup(driver);
        Thread.sleep(1000);

        String expected = "A user already exists with given email or phone number";
        WebElement elem = driver.findElement(By.xpath("//div[@class='MuiAlert-message css-1xsto0d']"));
        String actual = elem.getText();
        Assert.assertEquals(actual, expected, "Error: A user already exists with given email or phone number");
        MobileVerification.clickClose(driver);
        Thread.sleep(2000);
    }

    @Test

    public void verifySignUpInvalidNumber() throws InterruptedException {
        SignupPage.enterSignup(driver,"111");
        SignupPage.clickSignup(driver);
        Thread.sleep(1000);

        String expected = "Please enter valid phone number";
        WebElement elem = driver.findElement(By.xpath("//div[@class= 'MuiAlert-message css-1xsto0d']"));
        String actual = elem.getText();
        Assert.assertEquals(actual, expected, "Error: Please enter valid phone number");

        MobileVerification.clickClose(driver);
    }

    @Test
    public void verifySignUpCharPhoneNo() throws InterruptedException {
        SignupPage.enterSignup(driver,"8787eeeeee");
        SignupPage.clickSignup(driver);
        Thread.sleep(1000);


        String expected = "Please enter valid phone number";
        WebElement elem = driver.findElement(By.xpath("//div[@class= 'MuiAlert-message css-1xsto0d']"));
        String actual = elem.getText();
        Assert.assertEquals(actual, expected, "Error: Please enter valid phone number");

        MobileVerification.clickClose(driver);
    }

    @Test
    public void verifyEmptyNumber() throws InterruptedException {

        SignupPage.enterSignup(driver,"");
        SignupPage.clickSignup(driver);
        Thread.sleep(1000);



        String expected = "Please enter valid phone number";
        WebElement elem = driver.findElement(By.xpath("//div[@class= 'MuiAlert-message css-1xsto0d']"));
        String actual = elem.getText();
        Assert.assertEquals(actual, expected, "Error: Please enter valid phone number");

        MobileVerification.clickClose(driver);
    }

    @Test
    public void verifySignup() throws InterruptedException {

        SignupPage.enterSignup(driver, "8794648306");
        SignupPage.clickSignup(driver);
        Thread.sleep(2000);

    }
    @Test
    public void verifyMobileVerificationPage() throws InterruptedException {
        System.out.println("Mobile Verification: " + driver.getCurrentUrl());

        Thread.sleep(2000);

        //mob verification <<otp for now inserted manually>>

        String expected = "Mobile Verification";
        WebElement titleheading = driver.findElement(By.xpath("//div[@class=\"MuiBox-root css-1cs5gre\"]"));
        String actual = titleheading.getText();
        Assert.assertEquals(actual, expected);

        WebElement verifybtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[4]/div/button"));
        Assert.assertTrue(verifybtn.isEnabled(), "Verify button is enabled");
        Thread.sleep(1000);
        WebElement resendbtn = driver.findElement(By.linkText("Resend"));
        try {

            System.out.println("PASS: RESEND BUTTON IS PRESENT");

        } catch (Exception e) {
            System.out.println("FAIL: RESEND BUTTON IS NOT PRESENT");
        }
    }
    @Test
    public void verifyIncorrectOTP() throws InterruptedException {

        MobileVerification.otpfields(driver, "123456");
        MobileVerification.clickVerify(driver);

        String expectedtext = "Invalid otp";
        WebElement elem = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[6]/div/div[2]"));
        String actualtext = elem.getText();
        Assert.assertEquals(actualtext, expectedtext);

        MobileVerification.clickClose(driver);
        Thread.sleep(2000);
    }

    @Test
    public void verifyEmptyOTP() throws InterruptedException {
        MobileVerification.otpfields(driver, "");
        MobileVerification.clickVerify(driver);
        String expectedtext = "Invalid otp";
        WebElement elem = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[6]/div/div[2]"));
        String actualtext = elem.getText();
        Assert.assertEquals(actualtext, expectedtext);

        MobileVerification.clickClose(driver);
        Thread.sleep(1000);
    }

    @Test
    public void verifyInvalidOTP() throws InterruptedException {
        MobileVerification.otpfields(driver, "12");
        MobileVerification.clickVerify(driver);
        String expectedtext = "Invalid otp";
        WebElement elem = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[6]/div/div[2]"));
        String actualtext = elem.getText();
        Assert.assertEquals(actualtext, expectedtext);

        MobileVerification.clickClose(driver);
        Thread.sleep(1000);

        //System.out.println("Create New Password : " + driver.getCurrentUrl());
    }

    @Test //OTP INSERTED MANUALLY FOR NOW
    public void verifyCorrectOTP() throws InterruptedException {
        //MobileVerification.clickResend(driver);
        Thread.sleep(10000);
        MobileVerification.clickVerify(driver);
        System.out.println("Create New Password : " + driver.getCurrentUrl());

    }

    @Test
    public void verifyCreateNewPwPage() throws InterruptedException {
        String expectedtitle = "Create New Password";
        WebElement titleheading = driver.findElement(By.xpath
                ("//div[@class='MuiBox-root css-1m4el6g']"));
        String actualtitle = titleheading.getText();
        Assert.assertEquals(actualtitle, expectedtitle);

        WebElement confirmPass = driver.findElement(By.xpath("//button[@id='clicked']"));
        String expectedName = "Confirm Password";
        String actualName = confirmPass.getText();
        Assert.assertEquals(actualName, expectedName);
        Assert.assertTrue(confirmPass.isEnabled(), "Verify button is enabled");

        WebElement viewPass = driver.findElement(By.xpath("//button[@aria-label='toggle password visibility']"));
        Assert.assertTrue(confirmPass.isDisplayed(), "View Password button is displayed");
        Assert.assertTrue(confirmPass.isEnabled(), "View Password is enabled");

    }

    @Test
    public void verifyEmptyPassword() throws InterruptedException {
        CreateNewPasswordPage.enterNewpass(driver, "");
        CreateNewPasswordPage.enterConfirmpass(driver, "");
        CreateNewPasswordPage.clickConfirm(driver);

        WebElement error = driver.findElement(By.xpath("//div[@class='MuiAlert-message css-1xsto0d']"));
        String expected = "Please enter valid 6 digit password";
        String actual = error.getText();
        Assert.assertEquals(actual, expected);

        CreateNewPasswordPage.clickClosePopup(driver);
    }

    @Test
    public void verifyWrongConfirm() throws InterruptedException {
        CreateNewPasswordPage.enterNewpass(driver, "123456");
        CreateNewPasswordPage.enterConfirmpass(driver, "");
        CreateNewPasswordPage.viewPassword(driver);
        CreateNewPasswordPage.clickConfirm(driver);

        WebElement error = driver.findElement(By.xpath("//div[@class='MuiAlert-message css-1xsto0d']"));
        String expected = "Passwords do not match";
        String actual = error.getText();
        Assert.assertEquals(actual, expected);

        CreateNewPasswordPage.clickClosePopup(driver);
        Thread.sleep(2000);
    }

    @Test
    public void verifyCharPass() throws InterruptedException {
        CreateNewPasswordPage.enterNewpass(driver, "123aaa");
        CreateNewPasswordPage.enterConfirmpass(driver, "123aaa");
        CreateNewPasswordPage.viewPassword(driver);
        CreateNewPasswordPage.clickConfirm(driver);

        WebElement error = driver.findElement(By.xpath("//div[@class='MuiAlert-message css-1xsto0d']"));
        String expected = "Please enter valid 6 digit password";
        String actual = error.getText();
        Assert.assertEquals(actual, expected);

        CreateNewPasswordPage.clickClosePopup(driver);
        Thread.sleep(2000);
    }

    @Test
    public void verify6DigitPassValidation() throws InterruptedException {

        WebElement newP = driver.findElement(By.xpath("//input[@id=':r0:']"));
        String maxlength = newP.getAttribute("maxlength");
        System.out.println("Password maximum length is: " + maxlength);

        WebElement confirmP = driver.findElement(By.xpath("//input[@id=':r1:']"));
        String maxlength1 = confirmP.getAttribute("maxlength");
        System.out.println("Password maximum length is: " + maxlength1);
        Thread.sleep(2000);
    }

    @Test
    public void verifyInvalidPass() throws InterruptedException {
        CreateNewPasswordPage.enterNewpass(driver, "123");
        CreateNewPasswordPage.enterConfirmpass(driver, "123");
        CreateNewPasswordPage.viewPassword(driver);
        CreateNewPasswordPage.clickConfirm(driver);

        WebElement error = driver.findElement(By.xpath("//div[@class='MuiAlert-message css-1xsto0d']"));
        String expected = "Please enter valid 6 digit password";
        String actual = error.getText();
        Assert.assertEquals(actual, expected);
//        WebElement validation = driver.findElement(By.xpath("//div[@class='MuiBox-root css-0']"));
//        String expectedvalidation = "Password must be of 6 characters and combination of numbers only";
//        String actualvalidation = validation.getText();
//        Assert.assertEquals(actualvalidation, expectedvalidation);
        CreateNewPasswordPage.clickClosePopup(driver);
        Thread.sleep(2000);
    }

    @Test
    public void enterPassword() throws InterruptedException {
        CreateNewPasswordPage.enterNewpass(driver, "123456");
        CreateNewPasswordPage.enterConfirmpass(driver, "123456");
        CreateNewPasswordPage.viewPassword(driver);
        CreateNewPasswordPage.clickConfirm(driver);
        Thread.sleep(2000);

//        String expectedUrl = "https://app.liveolympiad.org/schooldetails";
//        String actualUrl = driver.getCurrentUrl();
//        Assert.assertEquals(actualUrl, expectedUrl);
    }
    @Test
    public void verifySchoolDetailsPage() throws InterruptedException {
        String expectedtitle = "School Details";
        WebElement title = driver.findElement(By.xpath("//div[@class='MuiBox-root css-axccz7']"));
        String actualtitle = title.getText();
        Assert.assertEquals(actualtitle, expectedtitle);

//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//        driver.getWindowHandles();
//
//        WebElement schoolDropdown = driver.findElement(By.cssSelector(
//                "[fdprocessedid = \"ywmvba\"]"));
//        try {
//
//            System.out.println("PASS: ELEMENT IS PRESENT");
//            Assert.assertTrue(schoolDropdown.isEnabled(), "schoolDropdown is enabled");
//
//        } catch (Exception e) {
//            System.out.println("FAIL: ELEMENT NOT PRESENT");
//        }
//
//        WebElement classDropdown = driver.findElement(By.cssSelector(
//                "[fdprocessedid = \"gudfjc\"]"));
//        try {
//
//            System.out.println("PASS: ELEMENT IS PRESENT");
//            Assert.assertTrue(classDropdown.isEnabled(), "classDropdown is enabled");
//
//        } catch (Exception e) {
//            System.out.println("FAIL: ELEMENT NOT PRESENT");
//        }
//
//        WebElement sectionField = driver.findElement(By.xpath("//input[@id=':r2:']"));
//        try {
//
//            System.out.println("PASS: ELEMENT IS PRESENT");
//            Assert.assertTrue(sectionField.isEnabled(), "sectionField is enabled");
//
//        } catch (Exception e) {
//            System.out.println("FAIL: ELEMENT NOT PRESENT");
//        }
//
//        WebElement nextBtn = driver.findElement(By.xpath("//button[@fdprocessedid='49e5zi']"));
//        try {
//
//            System.out.println("PASS: ELEMENT IS PRESENT");
//            Assert.assertTrue(nextBtn.isEnabled(), "Next Button is enabled");
//
//        } catch (Exception e) {
//            System.out.println("FAIL: ELEMENT NOT PRESENT");
//        }
//
//
    }

}
