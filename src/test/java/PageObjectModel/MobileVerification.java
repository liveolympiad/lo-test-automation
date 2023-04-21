package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class MobileVerification extends AbstractPage {
public static void otpfields(WebDriver driver, String otp) throws InterruptedException {
    WebElement elem = driver.findElement(By.xpath(
            "//div[@class= 'MuiBox-root css-6tgr00'] //input[@type='tel']"));
    elem.sendKeys(Keys.BACK_SPACE);
    elem.sendKeys(Keys.BACK_SPACE);
    elem.sendKeys(Keys.BACK_SPACE);
    elem.sendKeys(Keys.BACK_SPACE);
    elem.sendKeys(Keys.BACK_SPACE);
    elem.sendKeys(Keys.BACK_SPACE);
    elem.sendKeys(otp);
}
    public static void clickVerify(WebDriver driver) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[4]/div/button")).click();
    }
}
