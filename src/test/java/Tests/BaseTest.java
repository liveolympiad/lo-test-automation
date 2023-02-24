package Tests;

import Utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Browser;

public class BaseTest {
    protected Browser browser;
    //private final String baseUrl = "https://app-uat.liveolympiad.org/";
    protected final String baseUrl = "https://app.liveolympiad.org/";
    protected String driverPath = "src/test/resources/chromedriver"; // mac
    protected WebDriver driver;
    protected Utils utils;

    BaseTest() {
        String os = System.getProperty("os.name");
        if (os.equalsIgnoreCase("Windows 10")) {
            driverPath = "src\\test\\resources\\chromedriver.exe";
        } else if (os.equalsIgnoreCase("Linux")) {
            driverPath = "src/test/resources/linux/chromedriver";
        }
    }
}
