package com.abn.testNGTest;

import com.abn.testNGTest.common.CommonHelper;
import com.abn.testNGTest.common.DriverManager;
import com.abn.testNGTest.pagez.HomePage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by aswathyn on 09/01/17.
 */
public class SignUpTest  {

    @Test()
    public void pTest() throws Exception {
        HomePage pg = new HomePage(DriverManager.getDriver());

        pg.addnew().goToSummary()
                .goToAuthenticate().signUp(CommonHelper.getUniqueEmail("chandra")) ;
        //ExtentTestManager.endTest();
    }
}
