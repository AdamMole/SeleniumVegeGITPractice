package utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;

    public WebDriver initializeDriver() throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/GlobalData.properties");
        properties.load(fis);
        String browserName = properties.getProperty("browser");

        if(browserName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
        //Provide the path for firefox
        }

        driver.manage().window().maximize();
        return driver;
    }

    public HomePage launchApplication() throws IOException {
        driver = initializeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        return new HomePage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }


}
