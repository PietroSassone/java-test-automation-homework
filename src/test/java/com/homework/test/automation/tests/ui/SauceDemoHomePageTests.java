package com.homework.test.automation.tests.ui;

import com.homework.test.automation.config.SpringConfig;
import com.homework.test.automation.factory.DriverFactory;
import com.homework.test.automation.pageobjects.saucedemo.CartPage;
import com.homework.test.automation.pageobjects.saucedemo.CheckoutPage;
import com.homework.test.automation.pageobjects.saucedemo.ProductsPage;
import com.homework.test.automation.pageobjects.saucedemo.SauceLoginPage;
import com.homework.test.automation.util.UserManager;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(classes = SpringConfig.class)
public class SauceDemoHomePageTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private SauceLoginPage sauceLoginPage;

    @Autowired
    private ProductsPage productsPage;

    @Autowired
    private CartPage cartPage;

    @Autowired
    private CheckoutPage checkoutPage;

    @Autowired
    private DriverFactory driverFactory;

    @Autowired
    private UserManager userManager;

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        userManager.initUserManager();
        driver = sauceLoginPage.getDriver();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLoginProcess() {
        sauceLoginPage
                .openPage()
                .login(userManager.getUserByName("performance_glitch_user"))
                .addItemToCart("sauce-labs-backpack")
                .addItemToCart("sauce-labs-fleece-jacket")
                .assertCartItemCount(2)
                .goToCart()
                .clickCheckout()
                .fillDetails("Thomas", "Anderson", "12345")
                .clickContinue()
                .clickFinish()
                .validateConfirmationMessage("Thank you for your order!");
    }

    @Test
    public void testLoginErrorMessagesAndFooter() {
        sauceLoginPage
                .openPage()
                .clickLoginWithoutCredentials()
                .validateLoginError("Epic sadface: Username is required")
                .login(userManager.getUserByName("standard_user"))
                .scrollToTheBottom()
                .assertFooterText("2025", "Terms of Service");
    }
}
