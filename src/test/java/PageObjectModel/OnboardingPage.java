package PageObjectModel;

        import org.openqa.selenium.By;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;

public class OnboardingPage extends AbstractPage{
    public static String schoolTitle(){
        return "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[1]/h3/div";
    }

    public static String schoolDropdown(){
        return "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[4]/div/div/div";
    }

    public static void clickSchoolOpt(WebDriver driver){
        driver.findElement(By.xpath("//*[@id=\"menu-\"]/div[3]/ul/li[1]"));
    }

    public static String classDropDown(){
        return "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[5]/div[1]/div/div/div/div";
    }

    public static void clickClassOpt(WebDriver driver){
        driver.findElement(By.xpath("//*[@id=\"menu-\"]/div[3]/ul/li[6]"));
    }

    public static String sectionField(){
        return ":r11:";
    }
    public static void clickNext(WebDriver driver){
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[6]/div/button")).click();
    }

    public static String personalTitle(){
        return "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[1]/h3/div";
    }

    public static String nameField(){
        return "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[3]/div[1]/div/div/div";
    }
    public static void nameFieldInput(WebDriver driver, String Name) {
        WebElement elem = driver.findElement(By.xpath(
                        "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[3]/div[1]/div/div/div"));

        elem.clear();
        elem.sendKeys(Name);
    }

    public static String genderField(){
        return "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[3]/div[2]/div/div/div/div";
    }

    public static String rollNoField(){
        return "//*[@id=\":r5:\"]";
    }

    public static void clickNext2(WebDriver driver){
        driver.findElement(By.xpath("//*[@id=\"clicked\"]")).click();
    }

    public static void clickSkip(WebDriver driver){
        driver.findElement(By.xpath("" +
                "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[5]/div[2]/p/div/a")).click();
    }
}