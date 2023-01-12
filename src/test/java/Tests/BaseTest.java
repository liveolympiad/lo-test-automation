package Tests;

import org.openqa.selenium.WebDriver;

import Utils.Utils;

public class BaseTest {
    //private final String baseUrl = "https://app-uat.liveolympiad.org/";
    protected final String baseUrl = "https://app.liveolympiad.org/";
    protected final String driverPath = "src/test/resources/chromedriver";
    protected WebDriver driver;

    protected Utils utils;


}
