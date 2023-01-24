package Tests;

import Utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Browser;

public class BaseTest {
    protected Browser browser;

    //private final String baseUrl = "https://app-uat.liveolympiad.org/";
    protected final String baseUrl = "https://app.liveolympiad.org/";
    protected final String driverPath = "src/test/resources/chromedriver";
    protected WebDriver driver;

    protected Utils utils;


}
