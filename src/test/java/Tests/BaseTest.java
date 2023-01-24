package Tests;

import com.gargoylesoftware.htmlunit.WebClient;
import com.sun.xml.internal.bind.v2.model.core.EnumConstant;
import org.openqa.selenium.WebDriver;

import Utils.Utils;
import org.openqa.selenium.remote.Browser;

public class BaseTest {
    protected Browser browser;

    //private final String baseUrl = "https://app-uat.liveolympiad.org/";
    protected final String baseUrl = "https://app.liveolympiad.org/";
    protected final String driverPath = "src/test/resources/chromedriver";
    protected WebDriver driver;

    protected Utils utils;


}
