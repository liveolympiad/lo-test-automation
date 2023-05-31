package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateNewPasswordPage extends AbstractPage {

    public static String titlePage(){
        return "//div[@class='MuiBox-root css-1m4el6g']";
    }

    public static String confirmButtonPath(){
        return "//button[@id='clicked']";
    }

    public static String viewPw(){
        return "//button[@aria-label='toggle password visibility']";
    }

    public static String errorPopup1(){
        return "//div[@class='MuiAlert-message css-1xsto0d']";
    }

    public static String errorPopup2(){
        return "//div[@class='MuiAlert-message css-1xsto0d']";
    }
    public static void enterNewpass(WebDriver driver, String newPass) throws InterruptedException{
        WebElement elem = driver.findElement(By.xpath(
                "//input[@id=':r0:']"));

        elem.sendKeys(Keys.CONTROL + "a");
        elem.sendKeys(Keys.DELETE);
        elem.sendKeys(newPass);
    }

    public static void enterConfirmpass(WebDriver driver, String confirmPass) {
        WebElement elem = driver.findElement(By.xpath(
                "//input[@id=':r1:']"));
        elem.sendKeys(Keys.CONTROL + "a");
        elem.sendKeys(Keys.DELETE);
        elem.sendKeys(confirmPass);
    }

    public static void clickConfirm(WebDriver driver) {
        driver.findElement(By.xpath("//button[@id='clicked']")).click();
    }

    public static void viewPassword(WebDriver driver){
        driver.findElement(By.xpath("//button[@aria-label='toggle password visibility']")).click();
    }

    public static void clickClosePopup(WebDriver driver){
        driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
    }
}
