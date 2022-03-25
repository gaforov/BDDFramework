package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigsReader;
import utils.Constants;

import java.time.Duration;

import static base.PageInitializer.initialize;

public class BaseClass {
    public static WebDriver driver;

    public static WebDriver setUp() {
        ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);

        switch (ConfigsReader.getProperty("browser").toLowerCase()) {
            case "chrome" -> {
//                System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH); // <-- replace with WebDriverManager
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            default -> throw new RuntimeException("Browser is not supported");
        }

//        driver.manage().window().maximize();
        driver.manage().window().fullscreen();
        driver.get(ConfigsReader.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT_TIME));  // wait time can be changes in Constants class

        initialize(); // <-- initialize all page objects as part of setup to reuse their methods without creating their objects again everytime.
        return driver;
    }


    public static void tearDown() {
        if (driver != null) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }

}
