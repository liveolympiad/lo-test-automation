package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateNewPasswordPage extends AbstractPage {

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
