package org.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public static WebDriver driver;
    public static Properties properties = new Properties();

    public Base(){
        try{
            File file = new File("src/main/java/org/example/properities/config.properities");
            FileInputStream fis = new FileInputStream(file);
            properties.load(fis);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void lanuchBrowser()  {

        if(properties.get("browser").equals("chrome")){
            WebDriverManager.chromedriver().setup();
            final Map<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            chromePrefs.put("profile.password_manager_leak_detection", false); // <======== This is the important one

            final ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("prefs", chromePrefs);
            driver = new ChromeDriver(chromeOptions);

        } else if (properties.get("browser").equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else
            throw new Error("browser not supported");

        driver.manage().window().maximize();
        driver.get(properties.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

}
