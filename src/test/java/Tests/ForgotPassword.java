package Tests;

import PageObjectModel.CreateNewPasswordPage;
import PageObjectModel.ForgotPasswordPage;
import PageObjectModel.MobileVerification;
import Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.Browser.CHROME;

public class ForgotPassword extends BaseTest {
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

    @Test(priority = 1)
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

    @Test(priority = 2)
    public void forgotpwEmptyNo() throws InterruptedException {

        ForgotPasswordPage.forgotPasswordFields(driver, "");
        ForgotPasswordPage.clickNext(driver);

        String expected = "No User exists with given email or phone number";
        WebElement text = driver.findElement(By.xpath("//div[@class=\"MuiAlert-message css-1xsto0d\"]"));
        String actual = text.getText();
        Assert.assertEquals(actual, expected);

        ForgotPasswordPage.clickClosePopup(driver);

    }

    @Test(priority = 3)
    public void forgotpwUnregisteredNo() throws InterruptedException {

        ForgotPasswordPage.forgotPasswordFields(driver, "8014114915");
        ForgotPasswordPage.clickNext(driver);

        String expected = "No User exists with given email or phone number";
        WebElement text = driver.findElement(By.xpath("//div[@class='MuiAlert-message css-1xsto0d']"));
        String actual = text.getText();
        Assert.assertEquals(actual, expected);

        ForgotPasswordPage.clickClosePopup(driver);
    }

    @Test(priority = 4)
    public void forgotpwInvalidNo() throws InterruptedException {

        ForgotPasswordPage.forgotPasswordFields(driver, "123456");
        ForgotPasswordPage.clickNext(driver);

        String expected = "No User exists with given email or phone number";
        WebElement text = driver.findElement(By.xpath("//div[@class=\"MuiAlert-message css-1xsto0d\"]"));
        String actual = text.getText();
        Assert.assertEquals(actual, expected);

        ForgotPasswordPage.clickClosePopup(driver);
    }

    @Test(priority = 5)
    public void forgotpw() throws InterruptedException {

        ForgotPasswordPage.forgotPasswordFields(driver, "8794648306");
        ForgotPasswordPage.clickNext(driver);

        System.out.println("Mobile Verification: " + driver.getCurrentUrl());
        Thread.sleep(2000);

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
        resendbtn.click();
        String expectedtex = "Successfully Initiated OTP";
        WebElement resendtext = driver.findElement(By.xpath("//div[@class=\"MuiAlert-message css-1xsto0d\"]"));
        String actualtex = resendtext.getText();
        Assert.assertEquals(actualtex, expectedtex);
        Thread.sleep(5000);
//        verifybtn.click();
    }

    @Test(priority = 7)
    public void verifyIncorrectOTP() throws InterruptedException {

        MobileVerification.otpfields(driver, "123456");
        MobileVerification.clickVerify(driver);
        String expectedtext = "Invalid otp";
        WebElement elem = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[6]/div/div[2]"));
        String actualtext = elem.getText();
        Assert.assertEquals(actualtext, expectedtext);
        Thread.sleep(2000);
    }

    @Test(priority = 6)
    public void verifyEmptyOTP() throws InterruptedException {
        MobileVerification.otpfields(driver, "");
        MobileVerification.clickVerify(driver);
        String expectedtext = "Invalid otp";
        WebElement elem = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[6]/div/div[2]"));
        String actualtext = elem.getText();
        Assert.assertEquals(actualtext, expectedtext);
        Thread.sleep(1000);
    }

    @Test(priority = 8)
    public void verifyInvalidOTP() throws InterruptedException {
        MobileVerification.otpfields(driver, "12");
        MobileVerification.clickVerify(driver);
        String expectedtext = "Invalid otp";
        WebElement elem = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[6]/div/div[2]"));
        String actualtext = elem.getText();
        Assert.assertEquals(actualtext, expectedtext);
        Thread.sleep(1000);
//        MobileVerification.clickVerify(driver);

    }

    @Test(priority = 9) //OTP INSERTED MANUALLY FOR NOW
    public void verifyCorrectOTP() throws InterruptedException {
        MobileVerification.clickResend(driver);
        Thread.sleep(10000);
        MobileVerification.clickVerify(driver);
        Thread.sleep(2000);

//        String expectedtext = "OTP Verification Successful";
//        WebElement elem = driver.findElement(By.xpath
//                ("//div[@class=\"MuiAlert-message css-1xsto0d\"]"));
//        String actualtext = elem.getText();
//        Assert.assertEquals(actualtext, expectedtext);
        System.out.println("Create New Password : " + driver.getCurrentUrl());
    }

    @Test(priority = 10)
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

    @Test(priority = 11)
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

    @Test(priority = 12)
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

    @Test(priority = 13)
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

    @Test(priority = 14)
    public void verify6DigitPassValidation() throws InterruptedException {

        WebElement newP = driver.findElement(By.xpath("//input[@id=':r0:']"));
        String maxlength = newP.getAttribute("maxlength");
        System.out.println("Password maximum length is: " + maxlength);

        WebElement confirmP = driver.findElement(By.xpath("//input[@id=':r1:']"));
        String maxlength1 = confirmP.getAttribute("maxlength");
        System.out.println("Password maximum length is: " + maxlength1);
        Thread.sleep(2000);
    }

    @Test(priority = 15)
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

    @Test(priority = 16)
    public void enterPassword() throws InterruptedException {
        CreateNewPasswordPage.enterNewpass(driver, "123456");
        CreateNewPasswordPage.enterConfirmpass(driver, "123456");
        CreateNewPasswordPage.viewPassword(driver);
        CreateNewPasswordPage.clickConfirm(driver);
        Thread.sleep(2000);

        String expectedUrl = "https://app.liveolympiad.org/dashboard";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

    }

}
