package com.homework.test.automation.pageobjects.saucedemo;

import com.homework.test.automation.factory.DriverFactory;
import com.homework.test.automation.model.User;
import com.homework.test.automation.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Component
public class SauceLoginPage extends BasePageObject {
    private static final String LOGIN_URL = "https://www.saucedemo.com/inventory.html";

    @Autowired
    private ProductsPage productsPage;

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = "error-message-container")
    private WebElement loginErrorMessage;

    private WebDriver webDriver;

    public SauceLoginPage(final DriverFactory driverFactory) {
        super(driverFactory);
        this.webDriver = driverFactory.createAndGetWebDriver();
    }

    public SauceLoginPage openPage() {
        navigateToUrl(LOGIN_URL);
        return this;
    }

    public SauceLoginPage clickLoginWithoutCredentials() {
        loginButton.click();
        return this;
    }

    public SauceLoginPage validateLoginError(final String expectedError) {
        Assert.assertEquals(loginErrorMessage.getText(), expectedError);
        return this;
    }

    public ProductsPage login(final User user) {
        usernameField.sendKeys(user.username());
        passwordField.sendKeys(user.password());
        loginButton.click();
        waitForPageToLoad();
        return productsPage;
    }
}
