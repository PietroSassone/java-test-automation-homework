package com.homework.test.automation.pageobjects.guru;

import com.homework.test.automation.pageobjects.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class TooltipPage extends BasePageObject {

    @FindBy(id = "download_now")
    private WebElement downloadNowButton;

    public TooltipPage(final WebDriver driver) {
        super(driver);
    }

    public void downloadNowButtonShouldBePresent() {
        Assert.assertTrue(downloadNowButton.isDisplayed());
    }
}
