package com.abn.testNGTest.common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;

import java.io.File;
import java.io.IOException;


/**
 * Created by aswathyn on 14/01/17.
 */
public class MethodListener implements IInvokedMethodListener,IClassListener,ISuiteListener{

    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest parent;

    protected static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodListener.class);


    @Override
    public void onStart(ISuite suite) {
        extent = ExtentManager.createInstance("extent.html");
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
        extent.attachReporter(htmlReporter);
    }

    @Override
    public void onFinish(ISuite suite) {

    }


    @Override
    public void onBeforeClass(ITestClass testClass) {
        LOGGER.info("Started execution of test class :" + testClass.getRealClass().getSimpleName()
                + " with Thread Id: " + Thread.currentThread().getId());
        LOGGER.info("testClass.getTestName():"+testClass.getTestMethods().getClass().getSimpleName());
        LOGGER.info("testClass.getTestMethods():"+testClass.getTestMethods());

        parent = extent.createTest(testClass.getRealClass().getSimpleName());
        parentTest.set(parent);
    }


    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        //parentTest.set(parent);
        String browserName =method.getTestMethod().getXmlTest().getLocalParameters().get("browser");
        driver = DriverFactory.getBrowserInstance(browserName);
        DriverManager.setWebDriver(driver);

        try {
            DriverManager.getDriver().get(CommonHelper.getPropData("url"));
        } catch (Exception ex) {
            LOGGER.error(ex+"WRONG URL!!");
        }
        LOGGER.info("parent:"+parent+"~"+method.getTestMethod().getMethodName());
        LOGGER.info("parentTest:"+parentTest.get()+"~"+method.getTestMethod().getMethodName());
        ExtentTest child = parentTest.get().createNode(method.getTestMethod().getMethodName()+"_"+browserName);
        test.set(child);
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {

            try {
                String screenshot = captureScreenshot(CommonHelper.timeStamp(testResult.getName() + "_"));
                test.get().fail(testResult.getThrowable()).addScreenCaptureFromPath(screenshot);
            } catch (IOException ex) {
                LOGGER.error(ex + "FAILED Capturing Screenshot");
            }
        }

        else if (testResult.getStatus() == ITestResult.SKIP) {
            test.get().skip(testResult.getThrowable());
        }

        else {
         test.get().pass("Test passed");
        }

        if (DriverManager.getDriver() != null) {
            LOGGER.info("Finished execution of test :" + method.getTestMethod().getMethodName()
                    + ":with Thread Id: " + Thread.currentThread().getId());
            DriverManager.getDriver().quit();
        }
        extent.flush();
    }

    @Override
    public void onAfterClass(ITestClass testClass) {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
        }
        DriverManager.cleanup();
    }

    public String captureScreenshot(String screenshotName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = "./ErrorScreenshot/" + screenshotName + ".png";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);

        return dest;
    }
}
