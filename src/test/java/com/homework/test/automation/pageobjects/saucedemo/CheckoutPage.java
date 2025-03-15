package com.homework.test.automation.pageobjects.saucedemo;

import com.homework.test.automation.factory.DriverFactory;
import com.homework.test.automation.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Component
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

    private WebDriver webDriver;

    public CheckoutPage(final DriverFactory driverFactory) {
        super(driverFactory);
        this.webDriver = driverFactory.createAndGetWebDriver();
    }

    public CheckoutPage fillDetails(final String firstName, final String lastName, final String postalCode) {
        firstNameInput.sendKeys(firstName);
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

    public void validateConfirmationMessage(final String expectedMessage) {
        Assert.assertEquals(confirmationMessage.getText(), expectedMessage);
    }
}
