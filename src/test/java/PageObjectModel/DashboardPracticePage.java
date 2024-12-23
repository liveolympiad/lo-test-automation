package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPracticePage extends AbstractPage {
    public static void clickScience(WebDriver driver){

        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div[1]/div/div/div/div[2]/span[1]")).click();
    }
    public static void clickMathematics(WebDriver driver) throws InterruptedException {

        driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div[1]/div/div/div/div[3]/span[1]")).click();
    }
    public static void clickEnglish(WebDriver driver) throws InterruptedException{

        driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div[1]/div/div/div/div[4]/span[1]")).click();
    }
    public static void clickAll(WebDriver driver) throws InterruptedException{
        driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div[1]/div/div/div/div[1]"
        )).click();
    }

    public static String sc(){
        return "//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div[1]/div/div/div/div[2]/span[1]";
    }

    public static String math(){
        return "//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div[1]/div/div/div/div[3]/span[1]";
    }

    public static String eng(){
        return "//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div[1]/div/div/div/div[4]/span[1]";
    }
    public static void clickFilterDropD(WebDriver driver) throws InterruptedException{

        driver.findElement(By.xpath(
                "//button[@id='dropbtn']")).click();
    }
    public static void clickEnter(WebDriver driver) throws InterruptedException{
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[1]/div[2]/div[2]/div[2]/button")).click();
    }

    public static void Subjects (WebDriver driver) throws InterruptedException {

        By subjectScience = By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div[1]/div/div/div/div[2]/span[1]");
    }

    public static void clickParent(WebDriver driver){
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[3]/div/button")).click();
    }
}
