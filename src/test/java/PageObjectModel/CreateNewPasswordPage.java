package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateNewPasswordPage extends AbstractPage {

    public static void enterNewpass(WebDriver driver, String newPass) {
        driver.findElement(By.xpath("//input[@id=':r0:']")).clear();
        driver.findElement(By.xpath("//input[@id=':r0:']")).sendKeys(newPass);
    }

    public static void enterConfirmpass(WebDriver driver, String confirmPass) {
        driver.findElement(By.xpath("//input[@id=':r1:']")).clear();
        driver.findElement(By.xpath("//input[@id=':r1:']")).sendKeys(confirmPass);
    }

    public static void clickConfirm(WebDriver driver) {
        driver.findElement(By.xpath("//button[@id='clicked']")).click();
    }

    public static void viewPassword(WebDriver driver){
        driver.findElement(By.xpath("//button[@aria-label='toggle password visibility']")).click();
    }
}
