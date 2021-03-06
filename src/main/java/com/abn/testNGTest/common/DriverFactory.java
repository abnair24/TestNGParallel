package com.abn.testNGTest.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by aswathyn on 14/01/17.
 */
public class DriverFactory {

//   public static WebDriver getBrowserInstance(String browserName) {
//
//       WebDriver driver = null;
//
//        if (browserName.toLowerCase().contains("firefox")) {
//            System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
//            driver = new FirefoxDriver();
//            driver.manage().window().maximize();
//        }
//
//        if (browserName.toLowerCase().contains("chrome")) {
//            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
//            ChromeOptions chromeOptions = new ChromeOptions();
//
//            /*
//            support for headless in chrome
//             */
//            chromeOptions.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
//            /*
//            support for headless using chrome canary
//             */
//            //chromeOptions.setBinary("/Applications/Google Chrome Canary.app/Contents/MacOS/Google Chrome Canary");
//
//            chromeOptions.addArguments("--headless");
//            chromeOptions.addArguments("--disable-gpu");
//
//            driver = new ChromeDriver(chromeOptions);
//            Toolkit toolkit = Toolkit.getDefaultToolkit();
//            int Width = (int) toolkit.getScreenSize().getWidth();
//            int Height = (int) toolkit.getScreenSize().getHeight();
//            driver.manage().window().setSize(new org.openqa.selenium.Dimension(Width, Height));
//        }
//        return driver;
//    }



    private static final Map<String, Supplier<WebDriver>> driverMap = new HashMap<>();

    private static final Supplier<WebDriver> chromeDriverSupplier = () -> {

        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        /*
            support for headless in chrome
             */
            chromeOptions.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
            /*
            support for headless using chrome canary
             */
            //chromeOptions.setBinary("/Applications/Google Chrome Canary.app/Contents/MacOS/Google Chrome Canary");

            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--disable-gpu");

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            int Width = (int) toolkit.getScreenSize().getWidth();
            int Height = (int) toolkit.getScreenSize().getHeight();

            return new ChromeDriver(chromeOptions);
    };

    private static final Supplier<WebDriver> firefoxDriverSupplier = () -> {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        return new FirefoxDriver();
    };

    static{
        driverMap.put("chrome", chromeDriverSupplier);
        driverMap.put("firefox", firefoxDriverSupplier);
    }

    public static WebDriver getBrowserInstance(String browserName) {

        WebDriver driver = driverMap.get(browserName.toLowerCase()).get();

        driver.manage().window().maximize();
        return driver;
    }

}
