package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends AbstractPage {

    public static void enterLogin(WebDriver driver, String phNum) {
        driver.findElement(By.xpath(
                        "/html/body/div/div/div/div[3]/div/div/div/div[1]/div/div/div/div[3]/div/div/div/input"))
                .sendKeys(phNum);
    }

    public static void enterPassword(WebDriver driver, String password) {
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


}
