package com.abn.testNGTest;

import com.abn.testNGTest.common.CommonHelper;
import com.abn.testNGTest.common.DriverManager;
import com.abn.testNGTest.pagez.HomePage;
import org.testng.annotations.Test;


/**
 * Created by aswathyn on 09/01/17.
 */
public class FirstTest {

    @Test()
    public void aTest() throws Exception {

        HomePage pg = new HomePage(DriverManager.getDriver());

        pg.addnew().goToSummary()
                .goToAuthenticate().signIn("byju@mailinator.com","Cisco@1234").goToShipping().goToPayment()
                .selectPaymentMode("payByWire").confrimOrder().backToORder();
    }

    @Test()
    public void bTest() throws Exception {

        HomePage pg = new HomePage(DriverManager.getDriver());

        pg.navToContactUs().submitQuery(CommonHelper.getPropData("name")).verifyContactUsSuccessMessage();
    }

}