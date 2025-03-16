package com.homework.test.automation.tests.ui;

import com.homework.test.automation.config.SpringConfig;
import com.homework.test.automation.factory.DriverFactory;
import com.homework.test.automation.pageobjects.editor.RichTextEditorPage;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(classes = SpringConfig.class)
public class RichTextEditorPageTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private DriverFactory driverFactory;

    private WebDriver driver;

    private RichTextEditorPage richTextEditorPage;

    @BeforeClass
    public void setup() {
        driver = driverFactory.createAndGetWebDriver();
        richTextEditorPage = new RichTextEditorPage(driver);
    }

    @AfterClass
    public void tearDown() {
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
