package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends AbstractPage {

    public static void enterLogin(WebDriver driver, String phNum) {
        driver.findElement(By.xpath(
                        "/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[3]/div/div/div/input"))
                .clear();
        driver.findElement(By.xpath(
                        "/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[3]/div/div/div/input"))
                .sendKeys(phNum);

    }

    public static void enterPassword(WebDriver driver, String password) {
        driver.findElement(By.xpath(
                        "/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[4]/div/div/div/input"))
                .clear();
        driver.findElement(By.xpath(
                        "/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[4]/div/div/div/input"))
                .sendKeys(password);
    }

    public static void clickLogin(WebDriver driver) {
        driver.findElement(
                        By.xpath(
                                "/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[6]/button"))
                .click();
    }

    public static void clickClose(WebDriver driver){
        driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-colorInherit MuiIconButton-sizeSmall css-p4uye0']")).click();
    }

    public static void verifyPageDetails(WebDriver driver) {
        WebElement elem = driver.findElement(By.xpath("//*[@id=\":r0:\"]"));
        Assert.assertTrue(elem.isDisplayed(), "Error: phone number input box is not page is not enabled ");
        Assert.assertTrue(elem.isEnabled(), "Error: phone number input box is not page is not enabled ");
        elem = driver.findElement(By.xpath("//*[@id=\":r1:\"]"));
        Assert.assertTrue(elem.isDisplayed(), "Error: password input box is not page is not enabled ");
        Assert.assertTrue(elem.isEnabled(), "Error: password input box is not page is not enabled ");
        elem = driver.findElement(By.xpath("//*[@id=\"clicked\"]"));
        Assert.assertTrue(elem.isDisplayed(), "Error: password input box is not page is not enabled ");
        Assert.assertTrue(elem.isEnabled(), "Error: password input box is not page is not enabled ");
    }

    public static String logoutButton() {
        return "//*[@id=\"root\"]/div/div[1]/div/div[3]/div/button";
    }

    public static String errorPopup1(){
        return "//*[@id=\"simple-tabpanel-0\"]/div/div/div[2]/div/div[2]";
    }

    public static String errorPopup2(){
        return "//*[@id=\"simple-tabpanel-0\"]/div/div/div[2]/div/div[2]";
    }

    public static String errorPopup3(){
        return "//*[@id=\"simple-tabpanel-0\"]/div/div/div[2]/div/div[2]";
    }

    public static String signupTitle(){
        return "//div[@class=\"MuiBox-root css-10ay245\"]";
    }

    public static String addChildren(){
        return "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[5]";
    }

    public static void clickAddChild(WebDriver driver){
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[5]")).click();
    }

    public static void clickCardEnter(WebDriver driver){
        driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[4]/div[1]/div/div[2]/button")).click();
    }

}
