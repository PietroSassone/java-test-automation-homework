package com.homework.test.automation.pageobjects.saucedemo;

import com.homework.test.automation.factory.DriverFactory;
import com.homework.test.automation.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartPage extends BasePageObject {

    @Autowired
    private CheckoutPage checkoutPage;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    private WebDriver webDriver;

    public CartPage(final DriverFactory driverFactory) {
        super(driverFactory);
        this.webDriver = driverFactory.createAndGetWebDriver();
    }

    public CheckoutPage clickCheckout() {
        checkoutButton.click();
        waitForPageToLoad();
        return checkoutPage;
    }
}
