package com.abn.testNGTest;

import com.abn.testNGTest.common.CommonHelper;
import com.abn.testNGTest.common.DriverManager;
import com.abn.testNGTest.pagez.HomePage;
import org.testng.annotations.Test;

/**
 * Created by aswathyn on 26/01/17.
 */
public class ThirdTest {

    @Test()
    public void contactUsTest() throws Exception {

        HomePage pg = new HomePage(DriverManager.getDriver());

        pg.navToContactUs().submitQuery(CommonHelper.getPropData("name")).verifyContactUsSuccessMessage();
    }
}
