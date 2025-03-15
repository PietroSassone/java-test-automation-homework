package com.homework.test.automation.pageobjects.saucedemo;

import com.homework.test.automation.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CheckoutPage extends BasePageObject {

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "complete-header")
    private WebElement confirmationMessage;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement sidebarMenu;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    public CheckoutPage(final WebDriver driver) {
        super(driver);
    }

    public CheckoutPage fillDetails(final String firstName, final String lastName, final String postalCode) {
        waitForElementToBeClickable(firstNameInput).sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        postalCodeField.sendKeys(postalCode);
        return this;
    }

    public CheckoutPage clickContinue() {
        continueButton.click();
        return this;
    }

    public CheckoutPage clickFinish() {
        finishButton.click();
        return this;
    }

    public CheckoutPage validateConfirmationMessage(final String expectedMessage) {
        Assert.assertEquals(confirmationMessage.getText(), expectedMessage);
        return this;
    }

    public void logOut() {
        waitForElementToBeClickable(sidebarMenu).click();
        waitForElementToBeClickable(logoutLink).click();
        waitForPageToLoad();
    }
}
