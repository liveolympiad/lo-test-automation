package Tests;

import PageObjectModel.CreateNewPasswordPage;
import PageObjectModel.ForgotPasswordPage;
import PageObjectModel.MobileVerification;
import PageObjectModel.OnboardingPage;
import Utils.Utils;
import org.checkerframework.checker.units.qual.C;
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

    @Test
    public void forgotPasswordPage() throws InterruptedException {
        WebElement verifyForgotpw = driver.findElement(By.linkText("Forgot Password?"));
        Assert.assertTrue(verifyForgotpw.isEnabled(), "Forgot Password is active");
        verifyForgotpw.click();
        System.out.println(driver.getCurrentUrl());

        //verify title
        String ForgotPwTitleLoc = ForgotPasswordPage.pageTitle();
        String expectedFtitle = "Forgot Password?";
        String actualFtitle = driver.findElement(By.xpath(ForgotPwTitleLoc)).getText();
        Assert.assertEquals(actualFtitle, expectedFtitle);
        Thread.sleep(6000);

        String textLoc = ForgotPasswordPage.textName();
        String expectedText = "No worries, You can reset your password by verifying your Phone Number with an OTP.";
        String actualText = driver.findElement(By.xpath(textLoc)).getText();
        Assert.assertEquals(actualText, expectedText);
        Thread.sleep(6000);

        String fieldLoc = ForgotPasswordPage.fieldForgotPw();
        WebElement phoneNo = driver.findElement(By.xpath(fieldLoc));
        //WebElement phoneNo = driver.findElement(By.xpath("//div[@class=\"MuiFormControl-root MuiTextField-root css-1wptnvt\"]"));
        Assert.assertTrue(phoneNo.isEnabled(), "Phone number field is enabled");

        String buttonLoc = ForgotPasswordPage.buttonPath();
        WebElement buttonF = driver.findElement(By.xpath(buttonLoc));
        Assert.assertTrue(buttonF.isEnabled(), "Next button is enabled");

    }

    @Test
    public void forgotpwEmptyNo() throws InterruptedException {
        ForgotPasswordPage.forgotPasswordFields(driver, "");
        ForgotPasswordPage.clickNext(driver);

        String errorLoc = ForgotPasswordPage.noUserExistPath();
        String expected = "No User exists with given email or phone number";
        String actualText = driver.findElement(By.xpath(errorLoc)).getText();
        Assert.assertEquals(actualText, expected);

        ForgotPasswordPage.clickClosePopup(driver);

    }

    @Test
    public void forgotpwUnregisteredNo() throws InterruptedException {
        ForgotPasswordPage.forgotPasswordFields(driver, "8014114915");
        ForgotPasswordPage.clickNext(driver);

        String errorLoc = ForgotPasswordPage.noUserExistPath();
        String expected = "No User exists with given email or phone number";
        String actualText = driver.findElement(By.xpath(errorLoc)).getText();
        Assert.assertEquals(actualText, expected);

        ForgotPasswordPage.clickClosePopup(driver);
    }

    @Test
    public void forgotpwInvalidNo() throws InterruptedException {
        ForgotPasswordPage.forgotPasswordFields(driver, "123456");
        ForgotPasswordPage.clickNext(driver);

        String errorLoc = ForgotPasswordPage.noUserExistPath();
        String expected = "No User exists with given email or phone number";
        String actualText = driver.findElement(By.xpath(errorLoc)).getText();
        Assert.assertEquals(actualText, expected);

        ForgotPasswordPage.clickClosePopup(driver);
    }

    @Test
    public void forgotpw() throws InterruptedException {
        ForgotPasswordPage.forgotPasswordFields(driver, "8787554083");
        ForgotPasswordPage.clickNext(driver);

        String expected = "Mobile Verification";
        String titleLoc = MobileVerification.mobileVtitle();
        //WebElement titleheading = driver.findElement(By.xpath("//div[@class=\"MuiBox-root css-1cs5gre\"]"));
        String actual = driver.findElement(By.xpath(titleLoc)).getText();
        Assert.assertEquals(actual, expected);

        System.out.println("Mobile Verification: " + driver.getCurrentUrl());
        Thread.sleep(2000);

        String buttonLoc = MobileVerification.verifyButton();
        WebElement verifybtn = driver.findElement(By.xpath(buttonLoc));
        Assert.assertTrue(verifybtn.isEnabled(), "Verify button is enabled");
        Thread.sleep(1000);
        WebElement resendbtn = driver.findElement(By.linkText("Resend"));
        try {
            System.out.println("PASS: RESEND BUTTON IS PRESENT");
        } catch (Exception e) {
            System.out.println("FAIL: RESEND BUTTON IS NOT PRESENT");
        }
        resendbtn.click();
        String msgLoc = MobileVerification.successMsgOTP();
        String expectedtex = "Successfully Initiated OTP";
        String actualtex = driver.findElement(By.xpath(msgLoc)).getText();
        Assert.assertEquals(actualtex, expectedtex);
        Thread.sleep(5000);
        // verifybtn.click();
    }

    @Test
    public void verifyIncorrectOTP() throws InterruptedException {
        MobileVerification.otpfields(driver, "123456");
        MobileVerification.clickVerify(driver);

        String invalidOtpLoc = MobileVerification.invalidOTP();
        String expectedtext = "Invalid otp";
        String actualtext = driver.findElement(By.xpath(invalidOtpLoc)).getText();
        Assert.assertEquals(actualtext, expectedtext);
        Thread.sleep(2000);
    }

    @Test
    public void verifyEmptyOTP() throws InterruptedException {
        MobileVerification.otpfields(driver, "");
        MobileVerification.clickVerify(driver);

        String invalidOtpLoc = MobileVerification.invalidOTP();
        String expectedtext = "Invalid otp";
        String actualtext = driver.findElement(By.xpath(invalidOtpLoc)).getText();
        Assert.assertEquals(actualtext, expectedtext);
        Thread.sleep(1000);
    }

    @Test
    public void verifyInvalidOTP() throws InterruptedException {
        MobileVerification.otpfields(driver, "12");
        MobileVerification.clickVerify(driver);

        String invalidOtpLoc = MobileVerification.invalidOTP();
        String expectedtext = "Invalid otp";
        String actualtext = driver.findElement(By.xpath(invalidOtpLoc)).getText();
        Assert.assertEquals(actualtext, expectedtext);
        Thread.sleep(1000);
//        MobileVerification.clickVerify(driver);

    }

    @Test //OTP INSERTED MANUALLY FOR NOW
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

    @Test
    public void verifyCreateNewPwPage() throws InterruptedException {
        String titleLoc = CreateNewPasswordPage.titlePage();
        String expectedtitle = "Create New Password";
        String actualtitle = driver.findElement(By.xpath(titleLoc)).getText();
        Assert.assertEquals(actualtitle, expectedtitle);

        String confimBtnLoc = CreateNewPasswordPage.confirmButtonPath();
        //WebElement confirmPass = driver.findElement(By.xpath("//button[@id='clicked']"));
        String expectedName = "Confirm Password";
        String actualName = driver.findElement(By.xpath(confimBtnLoc)).getText();
        Assert.assertEquals(actualName, expectedName);
        WebElement confirmPass = driver.findElement(By.xpath(confimBtnLoc));
        Assert.assertTrue(confirmPass.isEnabled(), "Verify button is enabled");

        String viewPwLoc = CreateNewPasswordPage.viewPw();
        WebElement viewPass = driver.findElement(By.xpath(viewPwLoc));
        Assert.assertTrue(viewPass.isDisplayed(), "View Password button is displayed");
        Assert.assertTrue(viewPass.isEnabled(), "View Password is enabled");

    }

    @Test
    public void verifyEmptyPassword() throws InterruptedException {
        CreateNewPasswordPage.enterNewpass(driver, "");
        CreateNewPasswordPage.enterConfirmpass(driver, "");
        CreateNewPasswordPage.clickConfirm(driver);

        String errorPopup1Loc = CreateNewPasswordPage.errorPopup1();
        String expected = "Please enter valid 6 digit password";
        String actual = driver.findElement(By.xpath(errorPopup1Loc)).getText();
        Assert.assertEquals(actual, expected);

        CreateNewPasswordPage.clickClosePopup(driver);
    }

    @Test
    public void verifyWrongConfirm() throws InterruptedException {
        CreateNewPasswordPage.enterNewpass(driver, "123456");
        CreateNewPasswordPage.enterConfirmpass(driver, "");
        CreateNewPasswordPage.viewPassword(driver);
        CreateNewPasswordPage.clickConfirm(driver);

        String errorPopup2Loc = CreateNewPasswordPage.errorPopup2();
        String expected = "Passwords do not match";
        String actual = driver.findElement(By.xpath(errorPopup2Loc)).getText();
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

        String errorPopup1Loc = CreateNewPasswordPage.errorPopup1();
        String expected = "Please enter valid 6 digit password";
        String actual = driver.findElement(By.xpath(errorPopup1Loc)).getText();
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

        String errorPopup1Loc = CreateNewPasswordPage.errorPopup1();
        String expected = "Please enter valid 6 digit password";
        String actual = driver.findElement(By.xpath(errorPopup1Loc)).getText();
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

        String expectedUrl = "https://app.liveolympiad.org/dashboard";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);
    }

}
