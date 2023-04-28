package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage extends AbstractPage {

    public static void clickForgotPassword(WebDriver driver) throws InterruptedException {
        driver.findElement(By.linkText("Forgot Password?")).click();
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
