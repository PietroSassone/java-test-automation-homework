package com.homework.test.automation.pageobjects.saucedemo;

import com.homework.test.automation.model.User;
import com.homework.test.automation.pageobjects.BasePageObject;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

@Slf4j
public class SauceLoginPage extends BasePageObject {
    private static final String LOGIN_URL = "https://www.saucedemo.com/inventory.html";

    private final ProductsPage productsPage;

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(className = "error-message-container")
    private WebElement loginErrorMessage;

    public SauceLoginPage(final WebDriver driver, final ProductsPage productsPage) {
        super(driver);
        this.productsPage = productsPage;
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
        log.info("Logging in with use {}.", user.username());
        usernameField.sendKeys(user.username());
        passwordField.sendKeys(user.password());
        loginButton.click();
        waitForPageToLoad();
        return productsPage;
    }
}
