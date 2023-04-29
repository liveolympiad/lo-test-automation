package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SchoolDetailsPage extends AbstractPage{
    public static void clickSchool(WebDriver driver) throws InterruptedException {
        driver.findElement(By.xpath(
                "//div[@class='MuiSelect-select MuiSelect-outlined MuiInputBase-input css-svj9x4' and @fdprocessedid='h5jepk']")).click();
    }

    public static void clickClass(WebDriver driver) throws InterruptedException{
        driver.findElement(By.xpath(
                "//div[@class='MuiSelect-select MuiSelect-outlined MuiInputBase-input css-svj9x4' and @fdprocessedid='x7xgjg']")).click();
    }

    public static void enterSection(WebDriver driver, String section) throws InterruptedException{
        WebElement elem = driver.findElement(By.xpath("//input[@id=':r2:']"));
        elem.sendKeys(Keys.CONTROL + "a");
        elem.sendKeys(Keys.DELETE);

        elem.sendKeys(section);
    }

    public static void clickNext(WebDriver driver) throws InterruptedException{
        driver.findElement(By.xpath("//button[@fdprocessedid='49e5zi']")).click();
    }
}