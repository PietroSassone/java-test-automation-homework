package com.homework.test.automation.pageobjects.guru;

import com.homework.test.automation.pageobjects.BasePageObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class GuruHomePage extends BasePageObject {
    private static final String GURU_PAGE_URL = "https://demo.guru99.com/test/guru99home/";

    private TooltipPage tooltipPage;

    @FindBy(xpath = "//h3[contains(text(), 'iFrame will not show if you have adBlock extension enabled')]/following-sibling::iframe")
    private WebElement iFrame;

    @FindBy(xpath = "//img")
    private WebElement iFrameImage;

    @FindBy(id = "philadelphia-field-email")
    private WebElement emailField;

    @FindBy(id = "philadelphia-field-submit")
    private WebElement submitButton;

    @FindBy(xpath = "//li[@class='dropdown']/a[contains(.,'Selenium')]")
//    @FindBy(css = "li.dropdown > a:contains('Selenium')")
    private WebElement seleniumDropdown;

    public GuruHomePage(final WebDriver driver, final TooltipPage tooltipPage) {
        super(driver);
        this.tooltipPage = tooltipPage;
    }

    public GuruHomePage openPage() {
        navigateToUrl(GURU_PAGE_URL);
        return this;
    }

    public GuruHomePage switchToIframe() {
        waitForElementToBeVisible(iFrame);
        scrollTo(iFrame);
        driver.switchTo().frame(iFrame);
        return this;
    }

    public GuruHomePage clickImageInsideIframe() {
        iFrameImage.click();
        return this;
    }

    public GuruHomePage handleNewTab() {
        final String originalHandle = driver.getWindowHandle();

        driver.getWindowHandles().forEach(handle -> {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        });

        driver.switchTo().window(originalHandle);
        return this;
    }

    public GuruHomePage handlePopup() {
        final Alert popup = driver.switchTo().alert();
        Assert.assertTrue(popup.getText().contains("Successfully"));

        popup.dismiss();

        driver.switchTo().defaultContent();
        return this;
    }

    public GuruHomePage fillEmailAndSubmit(String email) {
        emailField.sendKeys(email);
        submitButton.click();
        return this;
    }

    public GuruHomePage clickSeleniumDropdown() {
        waitForPageToLoad();
        waitForElementToBeClickable(seleniumDropdown).click();
        return this;
    }

    public TooltipPage chooseFromDropDown(final String valueToSelect) {
        new Select(seleniumDropdown).selectByValue(valueToSelect);
        return tooltipPage;
    }
}
