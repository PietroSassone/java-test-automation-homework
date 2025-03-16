package com.homework.test.automation.pageobjects.saucedemo;

import com.homework.test.automation.pageobjects.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class ProductsPage extends BasePageObject {

    private final CartPage cartPage;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartItemNumber;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartButton;

    @FindBy(className = "footer_copy")
    private WebElement footer;

    public ProductsPage(final WebDriver driver, final CartPage cartPage) {
        super(driver);
        this.cartPage = cartPage;
    }

    public ProductsPage addItemToCart(final String itemName) {
        waitForElementToBeVisible(driver.findElement(By.xpath("//button[contains(@id,'add-to-cart-" + itemName + "')]"))).click();
        return this;
    }

    public ProductsPage assertCartItemCount(final int expectedNumberOfCartItems) {
        Assert.assertEquals(Integer.parseInt(cartItemNumber.getText()), expectedNumberOfCartItems, "Incorrect number of items found in the cart.");
        return this;
    }

    public ProductsPage scrollToTheBottom() {
        scrollToWithJsScript(footer);
        return this;
    }

    public void assertFooterText(final String expectedYear, final String expectedSubText) {
        final String assertMessage = "Missing footer text.";
        final String actualFooterText = footer.getText();

        Assert.assertTrue(actualFooterText.contains(expectedYear), assertMessage);
        Assert.assertTrue(actualFooterText.contains(expectedSubText), assertMessage);
    }

    public CartPage goToCart() {
        cartButton.click();
        waitForPageToLoad();
        return cartPage;
    }
}
