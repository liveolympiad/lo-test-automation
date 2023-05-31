package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage extends AbstractPage {

    public static String pageTitle(){
        return "driver.findElement(By.xpath(\"//div[@class=\\\"MuiBox-root css-1w1n69t\\\"]\"));";
    }

    public static String textName(){
        return "//div[@class=\"MuiBox-root css-18ailey\"]";
    }
    public static void clickForgotPassword(WebDriver driver) throws InterruptedException {
        driver.findElement(By.linkText("Forgot Password?")).click();
    }

    public static String fieldForgotPw(){
        return "//div[@class=\"MuiFormControl-root MuiTextField-root css-1wptnvt\"]";
    }

    public static String buttonPath(){
        return "//button[@id=\"clicked\"]";
    }

    public static String noUserExistPath(){
        return "//div[@class='MuiAlert-message css-1xsto0d']";
    }

    public static void forgotPasswordFields(WebDriver driver, String phoneno) throws InterruptedException {
        WebElement elem = driver.findElement(By.xpath(
                "//input[@id=\":r0:\"]"));
        elem.sendKeys(Keys.CONTROL + "a");
        elem.sendKeys(Keys.DELETE);
        elem.sendKeys(phoneno);


    }
    public static void clickClosePopup(WebDriver driver){
        driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
    }
    public static void clickNext(WebDriver driver){
        driver.findElement(By.xpath("//button[@id=\"clicked\"]")).click();
    }


}
