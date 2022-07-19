package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.ConfigsUtility;
import utils.Constants;

import java.time.Duration;

import static base.PageInitializer.initialize;

public class BaseClass {
    public static WebDriver driver;

    public static WebDriver setUp() {
        ConfigsUtility.readProperties(Constants.CONFIGURATION_FILEPATH); // <-- should be first line, reads configuration.properties file
        String headless = ConfigsUtility.getProperty("headless");

        switch (ConfigsUtility.getProperty("browser").toLowerCase()) {
            case "chrome" -> {
//                System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_PATH); // <-- replace with WebDriverManager
                // AS of WDM 5.x version, below line will both create & initialize driver, but we don't use it because of headless at this moment
//                driver = WebDriverManager.chromedriver().create();
                WebDriverManager.chromedriver().setup();
                if (headless.equalsIgnoreCase("true")) {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless"); // optional: "--window-size=1920,1200", "--no-sandbox", "disable-gpu"
                    driver = new ChromeDriver(chromeOptions);
                } else {
                    driver = new ChromeDriver();
                }

            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                if (headless.equalsIgnoreCase("true")) {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    driver = new FirefoxDriver(firefoxOptions);
                } else {
                    driver = new FirefoxDriver();
                }
            }
            default -> throw new RuntimeException("Browser is not supported");
        }

//        driver.manage().window().maximize();
        driver.manage().window().fullscreen();
        driver.get(ConfigsUtility.getProperty("url"));
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
