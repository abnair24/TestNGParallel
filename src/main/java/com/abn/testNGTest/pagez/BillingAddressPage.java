package com.abn.testNGTest.pagez;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by aswathyn on 09/01/17.
 */
public class BillingAddressPage extends BasePage<BillingAddressPage> {

    public BillingAddressPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForElement(getPageLoadCondition());
    }

    @FindBy(id = "uniform-id_address_delivery")
    private WebElement addressSelect ;

    @FindBy(name = "processAddress")
    private WebElement checkoutStep3 ;

    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(addressSelect);
    }

    @Override
    protected void instantiatePage(BillingAddressPage page) {
        PageFactory.initElements(driver,page);
    }

    public ShippingPage goToShipping(){
        checkoutStep3.click();
        return new ShippingPage(driver);
    }
}
