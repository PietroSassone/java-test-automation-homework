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
public class RichTextEditorPageTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private RichTextEditorPage richTextEditorPage;

    @Autowired
    private DriverFactory driverFactory;

    private WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = richTextEditorPage.getDriver();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testRichTextEditor() {
        final String inputTextAutomation = "Automation ";
        final String inputTextTest = "Test";
        final String inputTextExample = " Example";

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
