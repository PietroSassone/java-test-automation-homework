package com.homework.test.automation.pageobjects.guru;

import com.homework.test.automation.factory.DriverFactory;
import com.homework.test.automation.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.testng.Assert;

@Component
public class TooltipPage extends BasePageObject {

    @FindBy(id = "download_now")
    private WebElement downloadNowButton;

    private WebDriver webDriver;

    public TooltipPage(DriverFactory driverFactory) {
        super(driverFactory);
        this.webDriver = driverFactory.createAndGetWebDriver();
    }

    public void downloadNowButtonShouldBePresent() {
        Assert.assertTrue(downloadNowButton.isDisplayed());
    }
}
