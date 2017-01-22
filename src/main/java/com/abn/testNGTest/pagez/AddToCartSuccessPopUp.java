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
public class AddToCartSuccessPopUp extends BasePage<AddToCartSuccessPopUp> {

    public AddToCartSuccessPopUp(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForElement(getPageLoadCondition());
    }

    @FindBy(className = "icon-ok")
    private WebElement cartSuccessMessage ;

    @FindBy(xpath = "//a[@title='Proceed to checkout']")
    private WebElement checkoutStep1 ;


    @Override
    protected ExpectedCondition<?> getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(cartSuccessMessage);
    }

    @Override
    protected void instantiatePage(AddToCartSuccessPopUp page) {
        PageFactory.initElements(driver,page);
    }

    public CartSummaryPage goToSummary(){
        checkoutStep1.click();
        return new CartSummaryPage(driver);
    }
}
