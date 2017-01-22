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
public class PaymentConfirmPage extends BasePage<PaymentConfirmPage> {

    public PaymentConfirmPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForElement(getPageLoadCondition());
    }

    @FindBy(id = "cart_navigation")
    private WebElement confirmPaymentFooter ;

    @FindBy(xpath = "//span[text()='I confirm my order']//parent::button")
    private WebElement confirmOrder ;

    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(confirmPaymentFooter);
    }

    @Override
    protected void instantiatePage(PaymentConfirmPage page) {
        PageFactory.initElements(driver,page);

    }

    public OrderConfirmationPage confrimOrder(){
        confirmOrder.click();
        return new OrderConfirmationPage(driver);
    }

}
