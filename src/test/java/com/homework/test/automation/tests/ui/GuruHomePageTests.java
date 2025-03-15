package com.homework.test.automation.tests.ui;

import com.homework.test.automation.config.SpringConfig;
import com.homework.test.automation.factory.DriverFactory;
import com.homework.test.automation.pageobjects.guru.GuruHomePage;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(classes = SpringConfig.class)
public class GuruHomePageTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private GuruHomePage guruHomePage;

    @Autowired
    private DriverFactory driverFactory;

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = guruHomePage.getDriver();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testIFrameAndTabHandling() {
        guruHomePage.openPage()
                .switchToIframe()
                .clickImageInsideIframe()
                .handleNewTab()
                .fillEmailAndSubmit("test@example.com")
                .handlePopup()
                .clickSeleniumDropdown()
                .chooseFromDropDown("Tooltip")
                .downloadNowButtonShouldBePresent();
    }
}
