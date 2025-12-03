package org.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


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

        if(properties.get("browser").toString().equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            final Map<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            chromePrefs.put("profile.password_manager_leak_detection", false); // <======== This is the important one

            final ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--use-fake-ui-for-media-stream");
            chromeOptions.addArguments("--use-fake-device-for-media-stream");
            chromeOptions.setExperimentalOption("prefs", chromePrefs);
            System.out.println("Connected Successfully");
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
