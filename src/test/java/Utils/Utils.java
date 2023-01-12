package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utils {
    private WebDriver driver;

    public WebElement waitForElementInternal(String elemStr, int sleepInmillis) throws InterruptedException {
        Thread.sleep(sleepInmillis);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By elemLocator = By.xpath(elemStr);
        WebElement elem = driver.findElement(elemLocator);
        // wait till the logout button is visible
        wait.until(ExpectedConditions.visibilityOf(elem));
        return elem;
    }

    public WebElement waitForElement(String elemStr) throws InterruptedException {
        return waitForElementInternal(elemStr, 10000);
    }

    public WebElement waitForElement(String elemStr, int sleepInmillis) throws InterruptedException {
        return waitForElementInternal(elemStr, sleepInmillis);
    }

    public Utils(WebDriver d) {
        this.driver = d;
    }
}
