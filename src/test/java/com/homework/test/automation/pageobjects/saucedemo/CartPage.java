package com.homework.test.automation.pageobjects.saucedemo;

import com.homework.test.automation.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePageObject {

    private final CheckoutPage checkoutPage;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage(final WebDriver driver, final CheckoutPage checkoutPage) {
        super(driver);
        this.checkoutPage = checkoutPage;
    }

    public CheckoutPage clickCheckout() {
        checkoutButton.click();
        waitForPageToLoad();
        return checkoutPage;
    }
}
