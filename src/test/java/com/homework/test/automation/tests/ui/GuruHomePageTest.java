package com.homework.test.automation.tests.ui;

import com.homework.test.automation.config.SpringConfig;
import com.homework.test.automation.factory.DriverFactory;
import com.homework.test.automation.listener.TestListener;
import com.homework.test.automation.pageobjects.guru.GuruHomePage;
import com.homework.test.automation.pageobjects.guru.TooltipPage;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Slf4j
@Listeners(TestListener.class)
@ContextConfiguration(classes = SpringConfig.class)
public class GuruHomePageTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DriverFactory driverFactory;

    private WebDriver driver;

    private GuruHomePage guruHomePage;

    @BeforeClass
    public void setup(ITestContext context) {
        driver = driverFactory.createAndGetWebDriver();

        final TooltipPage tooltipPage = new TooltipPage(driver);
        guruHomePage = new GuruHomePage(driver, tooltipPage);

        context.setAttribute("driver", driver);
    }

    @AfterClass
    public void tearDown() {
        log.info("Shutting down the driver.");
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testIFrameAndTabHandling() {
        // Given - When - Then
        guruHomePage.openPage()
                .switchToIframe()
                .clickImageInsideIframe()
                .handleNewTab()
                .fillEmailAndSubmit("test@example.com")
                .handlePopup()
                .clickSeleniumDropdown()
                .clickTooltipDropdown()
                .downloadNowButtonShouldBePresent();
    }
}
