package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage extends AbstractPage
{
    public static void enterFullname(WebDriver driver, String fullname)
    {

        driver.findElement(By.xpath(
                        "//*[@id=\":r0:\"]"))
                .sendKeys(fullname);
    }
    public static void enterRollno(WebDriver driver, String rollno)
    {
        WebElement elem = driver.findElement(By.xpath(
                        "//*[@id=\":r1:\"]"));
        elem.clear();
        elem.sendKeys(rollno);
    }
    public static void enterSection(WebDriver driver, String section)
    {
        driver.findElement(By.xpath(
                        "//*[@id=\"root\"]/div/div[3]/div/div[2]/div/div[4]/div[2]/div/div"))
                .clear();
        driver.findElement(By.xpath(
                        "//*[@id=\"root\"]/div/div[3]/div/div[2]/div/div[4]/div[2]/div/div"))
                .sendKeys(section);
    }
    public static void enterEmail(WebDriver driver, String email)
    {
        driver.findElement(By.xpath(
                        "//*[@id=\":r4:\"]"))
                .clear();
        driver.findElement(By.xpath(
                        "//*[@id=\":r4:\"]"))
                .sendKeys(email);
    }

}
