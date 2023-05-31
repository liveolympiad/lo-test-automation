package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class MobileVerification extends AbstractPage {

    public static String mobileVtitle(){
        return "//div[@class=\"MuiBox-root css-1cs5gre\"]";
    }

    public static String verifyButton(){
        return "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[4]/div/button";
    }
    public static String successMsgOTP(){
        return "//div[@class=\"MuiAlert-message css-1xsto0d\"]";
    }

    public static String invalidOTP(){
        return "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[6]/div/div[2]";
    }
    public static void otpfields(WebDriver driver, String otp) throws InterruptedException {

        for (int i = 6; i >=1 ; i--) {
            WebElement elem = driver.findElement(By.xpath(
                    "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[3]/div/div/div[" + Integer.toString(i) + "]/input"));
            if (elem != null) {
                elem.sendKeys(Keys.BACK_SPACE);
            }
        }

        WebElement elem = driver.findElement(By.xpath(
                "//div[@class= 'MuiBox-root css-6tgr00'] //input[@type='tel']"));
        elem.sendKeys(otp);
    }
    public static void clickVerify(WebDriver driver) {
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[4]/div/button")).click();
    }
    public static void clickResend(WebDriver driver){
         driver.findElement(By.linkText("Resend")).click();
    }
    public static void clickClose(WebDriver driver){
        driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-colorInherit MuiIconButton-sizeSmall css-p4uye0']")).click();
    }

}
