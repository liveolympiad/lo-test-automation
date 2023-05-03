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

}
