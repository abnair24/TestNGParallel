package com.abn.testNGTest.common;

import org.openqa.selenium.WebDriver;

/**
 * Created by aswathyn on 14/01/17.
 */
public class DriverManager {

    private static InheritableThreadLocal<WebDriver> webDriver = new InheritableThreadLocal<WebDriver>();

    public static WebDriver getDriver() { return webDriver.get(); }

    public static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    public static void cleanup() {
        webDriver.remove();
    }
}
