package Tests;

import PageObjectModel.DashboardPracticePage;
import PageObjectModel.LoginPage;
import PageObjectModel.OnboardingPage;
import PageObjectModel.ProfilePage;
import Utils.Utils;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.Browser.CHROME;

@Test
public class SanityTest extends BaseTest {
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
            String logoutButtonLoc = LoginPage.logoutButton();
            if (driver.findElements(By.xpath(logoutButtonLoc)).size() != 0) {
                driver.findElement(By.xpath(logoutButtonLoc)).click();
                Thread.sleep(2000);
                //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                WebElement elem = utils.waitForElement(logoutButtonLoc);
//                elem.click();
            }
        }
    }

    @Test
    public void loginNoPhoneNumberEntered() throws InterruptedException {
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
        String errorPopupLoc = LoginPage.errorPopup1();
        String actual = driver.findElement(By.xpath(errorPopupLoc)).getText();

        //WebElement elem = driver.findElement(By.xpath("//*[@id=\"simple-tabpanel-0\"]/div/div/div[2]/div/div[2]"));
        //String actual = elem.getText();
        Assert.assertEquals(actual, expected, "Error: Please enter valid phone number");
        LoginPage.clickClose(driver);
    }

    @Test
    public void loginIncorrectPasswordEntered() throws InterruptedException {
        LoginPage.enterLogin(driver, "9958895489");
        LoginPage.enterPassword(driver, "123455");
        LoginPage.clickLogin(driver);

        String expected = "Incorrect password";
        String errorPopup2Loc = LoginPage.errorPopup2();
        String actual = driver.findElement(By.xpath(errorPopup2Loc)).getText();
        //WebElement elem = driver.findElement(By.xpath("//*[@id=\"simple-tabpanel-0\"]/div/div/div[2]/div/div[2]"));
        //String actual = elem.getText();
        Assert.assertEquals(actual, expected, "Error: Incorrect password");
        LoginPage.clickClose(driver);
    }

    @Test
    public void loginUnregisteredNumberEntered() throws InterruptedException {
        LoginPage.enterLogin(driver, "9953895499");
        LoginPage.enterPassword(driver, "123456");
        LoginPage.clickLogin(driver);
        //Thread.sleep(2000);
        String expected = "Sign Up";
        //WebElement titleheading = driver.findElement(By.xpath("//div[@class=\"MuiBox-root css-10ay245\"]"));
        //String actual = titleheading.getText();
        String signupTitleLoc = LoginPage.signupTitle();
        String actual = driver.findElement(By.xpath(signupTitleLoc)).getText();
        Assert.assertEquals(actual, expected);
        System.out.println(actual);
    }

    @Test
    public void loginEmptyDataEntered() throws InterruptedException {
        LoginPage.enterLogin(driver, "");
        LoginPage.enterPassword(driver, "");
        LoginPage.clickLogin(driver);

        String expected = "Please enter valid phone number";
        String errorPopupLoc = LoginPage.errorPopup1();
        String actual = driver.findElement(By.xpath(errorPopupLoc)).getText();

//        WebElement elem = driver.findElement(By.xpath("//*[@id=\"simple-tabpanel-0\"]/div/div/div[2]/div/div[2]"));
//        String actual = elem.getText();
        Assert.assertEquals(actual, expected, "Error: Please enter valid phone number");
        LoginPage.clickClose(driver);
    }

    @Test
    public void loginInvalidPasswordEntered() throws InterruptedException {
        LoginPage.enterLogin(driver, "9958895489");
        LoginPage.enterPassword(driver, "123");
        LoginPage.clickLogin(driver);

        String expected = "Please enter valid 6 digit password";
        String errorPopup3Loc = LoginPage.errorPopup3();
        String actual = driver.findElement(By.xpath(errorPopup3Loc)).getText();

//        WebElement elem = driver.findElement(By.xpath("//*[@id=\"simple-tabpanel-0\"]/div/div/div[2]/div/div[2]"));
//        String actual = elem.getText();
        Assert.assertEquals(actual, expected, "Error: Please enter valid 6 digit password");
        LoginPage.clickClose(driver);
    }

    @Test
    public void loginNoPasswordEntered() throws InterruptedException {
        LoginPage.enterLogin(driver, "9958895489");
        LoginPage.enterPassword(driver, "");
        LoginPage.clickLogin(driver);

        String expected = "Please enter valid 6 digit password";
        String errorPopup3Loc = LoginPage.errorPopup3();
        String actual = driver.findElement(By.xpath(errorPopup3Loc)).getText();

//        WebElement elem = driver.findElement(By.xpath("//*[@id=\"simple-tabpanel-0\"]/div/div/div[2]/div/div[2]"));
//        String actual = elem.getText();
        Assert.assertEquals(actual, expected, "Error: Please enter valid 6 digit password");
        LoginPage.clickClose(driver);
    }

    @Test
    public void login() throws InterruptedException {
        LoginPage.enterLogin(driver, "8787554083");
        LoginPage.enterPassword(driver, "123456");
        LoginPage.clickLogin(driver);
        Thread.sleep(2000);
        String expectedUrl = "https://uat.liveolympiad.org/parent";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        String addChildLoc = LoginPage.addChildren();
        String expectedName = "Add Children";
        String actualName = driver.findElement(By.xpath(addChildLoc)).getText();
        Assert.assertEquals(actualName, expectedName);
        LoginPage.clickAddChild(driver);
        Thread.sleep(2000);

        String schoolTitleLoc = OnboardingPage.schoolTitle();
        String expectedStitle = "School Details";
        String actualStitle = driver.findElement(By.xpath(schoolTitleLoc)).getText();
        Assert.assertEquals(actualStitle, expectedStitle);

        String schoolDropLoc = OnboardingPage.schoolDropdown();
        WebElement schoolDD = driver.findElement(By.xpath(schoolDropLoc));
        Assert.assertTrue(schoolDD.isEnabled(), "school Dropdown is enabled");
        schoolDD.click();
        OnboardingPage.clickSchoolOpt(driver);

        String classDropLoc = OnboardingPage.classDropDown();
        WebElement classDD = driver.findElement(By.xpath(classDropLoc));
        Assert.assertTrue(schoolDD.isEnabled(), "class Dropdown is enabled");
        classDD.click();
        OnboardingPage.clickClassOpt(driver);

        // TODO: <<<<<<<<school details will be added manually for now>>>>>>>>>>

        String sectionLoc = OnboardingPage.sectionField();
        WebElement sectionField = driver.findElement(By.id(sectionLoc));
        Assert.assertTrue(sectionField.isEnabled(), "section is enabled");
        OnboardingPage.sectionFieldInput(driver, "B");
        OnboardingPage.clickNext(driver);

        String personalDLoc = OnboardingPage.personalTitle();
        String expectedPTitle = "Personal Details";
        String actualPTitle = driver.findElement(By.xpath(personalDLoc)).getText();
        Assert.assertEquals(actualPTitle, expectedPTitle);

        // TODO: <<<<<<<< Personal details will be added manually for now >>>>>>>>>>
        String nameFieldLoc = OnboardingPage.nameField();
        WebElement nameField = driver.findElement(By.xpath(nameFieldLoc));
        Assert.assertTrue(nameField.isEnabled(), "Name field is enabled");
        OnboardingPage.nameFieldInput(driver, "testname");

        String genderFieldLoc = OnboardingPage.genderField();
        WebElement genderField = driver.findElement(By.xpath(genderFieldLoc));
        Assert.assertTrue(genderField.isEnabled(), "gender field is enabled");
        genderField.click();
        OnboardingPage.genderOpt(driver);
        Thread.sleep(5000);
        // TODO: <<<<<<<< gender will be selected manually for now >>>>>>>>>>

        String rollNoLoc = OnboardingPage.rollNoField();
        WebElement rollNoField = driver.findElement(By.xpath(rollNoLoc));
        Assert.assertTrue(rollNoField.isEnabled(), "gender field is enabled");
        OnboardingPage.rollNoInput(driver, "21");

        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy ");
        Date dateSet = new Date();

        String date1= dateFormat.format(dateSet);
        OnboardingPage.dateInput(driver, date1);

        //OnboardingPage.enterEmail(driver, "office@digishaala.com");

        OnboardingPage.clickNext2(driver);
        Thread.sleep(2000);
        OnboardingPage.clickSkip(driver);

        LoginPage.clickCardEnter(driver);
        Thread.sleep(2000);

        String expectedDashUrl = "https://uat.liveolympiad.org/dashboard";
        String actualDashUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualDashUrl, expectedDashUrl);

        DashboardPracticePage.clickParent(driver);
    }

    @Test(enabled = false)
    public void Oldlogin() throws InterruptedException {
        LoginPage.enterLogin(driver, "9958895489");
        LoginPage.enterPassword(driver, "123456");
        LoginPage.clickLogin(driver);
        Thread.sleep(2000);
        String expected1 = "Science";
        String scLoc = DashboardPracticePage.sc();
        String actual1 = driver.findElement(By.xpath(scLoc)).getText();
        Assert.assertEquals(actual1, expected1);
        DashboardPracticePage.clickScience(driver);

//        elem = utils.waitForElement(
//                "//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div[1]/div/div/div/div[3]/span[1]");
        String expected2 = "Mathematics";
        String mathLoc = DashboardPracticePage.math();
        String actual2 = driver.findElement(By.xpath(mathLoc)).getText();
        Assert.assertEquals(actual2, expected2);
        DashboardPracticePage.clickMathematics(driver);

//        elem = utils.waitForElement(
//                "//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div[1]/div/div/div/div[4]/span[1]");
        String expected3 = "English";
        String engLoc = DashboardPracticePage.eng();
        String actual3 = driver.findElement(By.xpath(engLoc)).getText();
        Assert.assertEquals(actual3, expected3);
        DashboardPracticePage.clickEnglish(driver);

        //check test details
        WebElement testDetails = driver.findElement(By.xpath("//div[@class=\"MuiBox-root css-14euyd\"]"));
        testDetails.getText();
        testDetails.isDisplayed();

        //DashboardPracticePage.clickFilterDropD(driver);
//        WebElement identifier = driver.findElement(By.xpath("//*[@id=\"dropmenu\"]/div[3]/ul/li[1]"));
//        Select select = new Select(identifier);
//        select.selectByIndex(1);
        //select.selectByVisibleText("Pending");
        //*utils.waitForElement("//*[@id=\"root\"]/div[3]/div/div[5]/div/div[1]/div/div[1]/div", 5000);*/
        DashboardPracticePage.clickAll(driver);
        String expectedTag = driver.findElement(By.xpath(
                "//div[@class='MuiChip-root MuiChip-filled MuiChip-sizeMedium MuiChip-colorPrimary MuiChip-filledPrimary brb_0 css-65el9p']"))
                .getText();

        DashboardPracticePage.clickEnter(driver);
        Thread.sleep(10000);
//        Alert a = driver.switchTo().alert();
//        System.out.println(a.getText());
//        a.dismiss();

        String actualTag = driver.findElement(By.xpath("//div[@class='MuiBox-root css-1mnln2d']")).getText();
        Assert.assertEquals(actualTag, expectedTag);

        String expectedText = "Test instructions:";
        String actualText = driver.findElement(By.xpath(
                "//h4[@class='MuiTypography-root MuiTypography-h4 css-zdpsb8']")).getText();
        Assert.assertEquals(actualText, expectedText);
        Thread.sleep(2000);

        driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div/button")).click();

        WebElement elems = utils.waitForElement(
                "//*[@id=\"root\"]/div/div[2]/div/div/div/div/button[2]/div/div/div");
        elems.click();

        String expectedTitle = "Edit Information";
        WebElement title = driver.findElement(By.xpath("//div[@class=\"MuiBox-root css-14dbvdd\"]"));
        String actualTitle = title.getText();
        Assert.assertEquals(actualTitle, expectedTitle);


        //verify button is enabled or not
        WebElement savebtn = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div[3]/div/div[2]/div/div[9]/button"));
        Assert.assertTrue(savebtn.isEnabled(), "Save button is enabled");

        //verify textbox fields in profile
        ProfilePage.enterFullname(driver, "John");
        Thread.sleep(1000);
        ProfilePage.enterRollno(driver, "12");
        Thread.sleep(1000);
//        EnterProfileDetails.enterSection(driver, "D");   //help
//        Thread.sleep(2000);
        ProfilePage.enterEmail(driver, "D@gmail.com");
        Thread.sleep(10000);

        savebtn.click();
        String expectedMessage = "Profile Submitted Successfully";
        WebElement succMess = driver.findElement(By.xpath("//div[@class='MuiAlert-message css-1xsto0d']"));
        String actualMessage = succMess.getText();
        Assert.assertEquals(actualMessage, expectedMessage);
    }
//    @Test(priority = 0)
//    public void verifyElements() throws InterruptedException
//    {
//        boolean phoneNo = driver.findElement(By.xpath("//*[@id=\":r0:\"]")).isDisplayed();
//        Thread.sleep(2000);
//
//    }
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