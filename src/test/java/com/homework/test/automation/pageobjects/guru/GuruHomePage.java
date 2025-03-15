package com.homework.test.automation.pageobjects.guru;

import com.homework.test.automation.factory.DriverFactory;
import com.homework.test.automation.pageobjects.BasePageObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Component
public class GuruHomePage extends BasePageObject {
    private static final String GURU_PAGE_URL = "https://demo.guru99.com/test/guru99home/";

    @Autowired
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

    private WebDriver webDriver;

    public GuruHomePage(final DriverFactory driverFactory) {
        super(driverFactory);
        this.webDriver = driverFactory.createAndGetWebDriver();
    }

    public GuruHomePage openPage() {
        navigateToUrl(GURU_PAGE_URL);
        return this;
    }

    public GuruHomePage switchToIframe() {
        waitForElementToBeVisible(iFrame);
        scrollTo(iFrame);
        webDriver.switchTo().frame(iFrame);
        return this;
    }

    public GuruHomePage clickImageInsideIframe() {
        iFrameImage.click();
        return this;
    }

    public GuruHomePage handleNewTab() {
        String originalHandle = webDriver.getWindowHandle();
        for (String handle : webDriver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                webDriver.switchTo().window(handle);
                webDriver.close();
            }
        }
        webDriver.switchTo().window(originalHandle);
        return this;
    }

    public GuruHomePage handlePopup() {
        final Alert popup = webDriver.switchTo().alert();
        Assert.assertTrue(popup.getText().contains("Successfully"));

        popup.dismiss();

        webDriver.switchTo().defaultContent();;
        return this;
    }

    public GuruHomePage fillEmailAndSubmit(String email) {
        emailField.sendKeys(email);
        submitButton.click();
        return this;
    }

    public GuruHomePage clickSeleniumDropdown() {
        waitForElementToBeClickable(seleniumDropdown).click();
        return this;
    }

    public TooltipPage chooseFromDropDown(final String valueToSelect) {
        new Select(seleniumDropdown).selectByValue(valueToSelect);
        return tooltipPage;
    }
}
