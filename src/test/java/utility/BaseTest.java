package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static utility.BaseTest.getDriver;

public class BaseTest {

    public WebDriver driver;
    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();

    public WebDriver initializeDriver() throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/GlobalData.properties");
        properties.load(fis);
        String browserName = properties.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            tdriver.set(driver);
        } else if (browserName.equalsIgnoreCase("firefox")) {
        //Provide the path for firefox
        }

        driver.manage().window().maximize();
        return getDriver();
    }

    public HomePage launchApplication() throws IOException {
        driver = initializeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        return new HomePage(driver);
    }

    public static synchronized WebDriver getDriver(){
        return tdriver.get();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }


}
