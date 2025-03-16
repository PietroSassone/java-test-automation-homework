package com.homework.test.automation.pageobjects.editor;

import com.homework.test.automation.pageobjects.BasePageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class RichTextEditorPage extends BasePageObject {
    private static final String EDITOR_PAGE_URL = "https://onlinehtmleditor.dev";

    @FindBy(xpath = "//div[@role='textbox']")
    private WebElement editor;

    @FindBy(xpath = "//button[contains(., 'Bold')]")
    private WebElement boldButton;

    @FindBy(xpath = "//button[contains(., 'Underline')]")
    private WebElement underlineButton;

    public RichTextEditorPage(final WebDriver driver) {
        super(driver);
    }

    public RichTextEditorPage openPage() {
        navigateToUrl(EDITOR_PAGE_URL);
        return this;
    }

    public RichTextEditorPage enterText(final String text) {
        editor.sendKeys(text);
        return this;
    }

    public RichTextEditorPage toggleBold() {
        waitForElementToBeVisible(boldButton).click();
        return this;
    }

    public RichTextEditorPage toggleUnderline() {
        underlineButton.click();
        return this;
    }

    public RichTextEditorPage assertTypedTextContainsBold(final String expectedBoldTextPart) {
        Assert.assertTrue(getEditorText().contains(String.format("<strong>%s</strong>", expectedBoldTextPart)), "Bold text not found.");
        return this;
    }

    public RichTextEditorPage assertTypedTextContainsUnderlined(final String expectedUnderlinedTextPart) {
        Assert.assertTrue(getEditorText().contains(String.format("<u>%s</u>", expectedUnderlinedTextPart)), "Underlined text not found.");
        return this;
    }

    public void assertTypedTextContainsPlain(final String expectedPlainTextPart) {
        Assert.assertTrue(getEditorText().contains(expectedPlainTextPart), "Plain text not found.");
    }

    private String getEditorText() {
        final JavascriptExecutor executor = (JavascriptExecutor) driver;
        return (String) executor.executeScript("return arguments[0].innerHTML;", editor);
    }
}
