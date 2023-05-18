package PageObjectModel;

        import org.openqa.selenium.By;
        import org.openqa.selenium.Keys;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;

public class OnboardingPage extends AbstractPage{
    public static String schoolTitle(){
        return "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[1]/h3/div";
    }

    public static String schoolDropdown(){
        return "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[4]/div/div/div";
    }

    public static void clickSchoolOpt(WebDriver driver) throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"menu-\"]/div[3]/ul/li[1]")).click();
        Thread.sleep(100);
    }

    public static String classDropDown(){
        return "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[5]/div[1]/div/div/div/div";
    }

    public static void clickClassOpt(WebDriver driver){
        driver.findElement(By.xpath("//*[@id=\"menu-\"]/div[3]/ul/li[1]")).click();
    }
    public static String sectionField(){
        return ":r3:";
    }

    public static void sectionFieldInput(WebDriver driver, String sec)
    {
        WebElement elem = driver.findElement(By.id(":r3:"));
        elem.clear();
        elem.sendKeys(sec);
    }
    public static void clickNext(WebDriver driver){
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[6]/div/button")).click();
    }

    public static String personalTitle(){
        return "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[1]/h3/div";
    }

    public static String nameField(){
        return "//*[@id=\":r4:\"]";
    }
    public static void nameFieldInput(WebDriver driver, String Name) {
        WebElement elem = driver.findElement(By.xpath(
                        "//*[@id=\":r4:\"]"));

        elem.clear();
        elem.sendKeys(Name);
    }

    public static String genderField(){
        return "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[3]/div[2]/div/div/div/div";
    }

    public static void genderOpt(WebDriver driver){
        driver.findElement(By.xpath(
                "//*[@id=\"menu-\"]/div[3]/ul/li[1]")).click();
    }
    public static String rollNoField(){
        return "//*[@id=\":r5:\"]";
    }

    public static void rollNoInput(WebDriver driver, String roll)
    {
      WebElement elem = driver.findElement(By.xpath("//*[@id=\":r5:\"]"));
      elem.clear();
      elem.sendKeys(roll);
    }

    public static void dateInput(WebDriver driver, String dateIn){
        WebElement elem = driver.findElement(By.xpath("//*[@id=\"date\"]"));
        elem.sendKeys(dateIn);
        elem.sendKeys(Keys.TAB);
    }

    public static void enterEmail(WebDriver driver, String emailIn){
        WebElement elem = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[6]/div/div"));
        elem.clear();
        elem.sendKeys(emailIn);
    }

    public static void clickNext2(WebDriver driver){
        driver.findElement(By.xpath("//*[@id=\"clicked\"]")).click();
    }

    public static void clickSkip(WebDriver driver){
        driver.findElement(By.xpath("" +
                "//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[5]/div[2]/p/div/a")).click();
    }
}