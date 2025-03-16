package com.homework.test.automation.tests.ui;

import com.homework.test.automation.config.SpringConfig;
import com.homework.test.automation.factory.DriverFactory;
import com.homework.test.automation.pageobjects.saucedemo.CartPage;
import com.homework.test.automation.pageobjects.saucedemo.CheckoutPage;
import com.homework.test.automation.pageobjects.saucedemo.ProductsPage;
import com.homework.test.automation.pageobjects.saucedemo.SauceLoginPage;
import com.homework.test.automation.util.UserManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Slf4j
@ContextConfiguration(classes = SpringConfig.class)
public class SauceDemoHomePageTest extends AbstractTestNGSpringContextTests {
    /**
     * I choose to write these tests without Cucumber for an easier showcase of fluent page objects.
     * If you'd like to see some of my Cucumber test examples, see this repo: https://github.com/PietroSassone/selenium-ta-demo
     */
    @Autowired
    private DriverFactory driverFactory;

    @Autowired
    private UserManager userManager;

    private WebDriver driver;

    private SauceLoginPage sauceLoginPage;

    @BeforeClass
    public void setup() {
        userManager.initUserManager();
        driver = driverFactory.createAndGetWebDriver();

        final CheckoutPage checkoutPage = new CheckoutPage(driver);
        final CartPage cartPage = new CartPage(driver, checkoutPage);
        final ProductsPage productsPage = new ProductsPage(driver, cartPage);

        sauceLoginPage = new SauceLoginPage(driver, productsPage);
    }

    @AfterClass
    public void tearDown() {
        log.info("Shutting down the driver.");
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void testLoginProcess() {
        // Given - When - Then
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
                .validateConfirmationMessage("Thank you for your order!")
                .logOut();
    }

    @Test(priority = 2)
    public void testLoginErrorMessagesAndFooter() {
        // Given - When - Then
        sauceLoginPage
                .openPage()
                .clickLoginWithoutCredentials()
                .validateLoginError("Epic sadface: Username is required")
                .login(userManager.getUserByName("standard_user"))
                .scrollToTheBottom()
                .assertFooterText("2025", "Terms of Service");
    }
}
