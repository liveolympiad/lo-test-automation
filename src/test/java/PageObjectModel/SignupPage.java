package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignupPage extends AbstractPage {
    public static void enterSignup(WebDriver driver, String phoneNo) throws InterruptedException {
        WebElement elem = driver.findElement(By.xpath(
                        "//input[@id=':r2:']"));

        elem.sendKeys(Keys.CONTROL + "a");
        elem.sendKeys(Keys.DELETE);

        //elem.clear();
        Thread.sleep(1000);
        elem.sendKeys(phoneNo);

    }

    public static void clickSignup(WebDriver driver){

        driver.findElement(By.xpath("//button[@id='clicked']")).click();
    }
}
