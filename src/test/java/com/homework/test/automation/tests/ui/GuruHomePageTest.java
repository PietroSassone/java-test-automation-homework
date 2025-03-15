package com.homework.test.automation.tests.ui;

import com.homework.test.automation.config.SpringConfig;
import com.homework.test.automation.factory.DriverFactory;
import com.homework.test.automation.pageobjects.guru.GuruHomePage;
import com.homework.test.automation.pageobjects.guru.TooltipPage;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(classes = SpringConfig.class)
public class GuruHomePageTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DriverFactory driverFactory;

    private WebDriver driver;

    private GuruHomePage guruHomePage;

    @BeforeClass
    public void setup() {
        driver = driverFactory.createAndGetWebDriver();

        final TooltipPage tooltipPage = new TooltipPage(driver);
        guruHomePage = new GuruHomePage(driver, tooltipPage);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

//    @Test
    public void testIFrameAndTabHandling() {
        // Given - When - Then
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
