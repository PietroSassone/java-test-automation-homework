package com.homework.test.automation.tests.ui;

import com.homework.test.automation.config.SpringConfig;
import com.homework.test.automation.factory.DriverFactory;
import com.homework.test.automation.listener.TestListener;
import com.homework.test.automation.pageobjects.editor.RichTextEditorPage;
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
public class RichTextEditorPageTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DriverFactory driverFactory;

    private WebDriver driver;

    private RichTextEditorPage richTextEditorPage;

    @BeforeClass
    public void setup(final ITestContext context) {
        driver = driverFactory.createAndGetWebDriver();
        richTextEditorPage = new RichTextEditorPage(driver);

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
    public void testRichTextEditor() {
        // Given
        final String inputTextAutomation = "Automation ";
        final String inputTextTest = "Test";
        final String inputTextExample = " Example";

        // When - Then
        richTextEditorPage.openPage()
                .toggleBold()
                .enterText(inputTextAutomation)
                .toggleBold()
                .toggleUnderline()
                .enterText(inputTextTest)
                .toggleUnderline()
                .enterText(inputTextExample)
                .assertTypedTextContainsBold(inputTextAutomation)
                .assertTypedTextContainsUnderlined(inputTextTest)
                .assertTypedTextContainsPlain(inputTextExample);
    }
}
