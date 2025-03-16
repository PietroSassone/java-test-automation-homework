package com.homework.test.automation.pageobjects;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

import static java.lang.String.valueOf;

@Slf4j
public class BasePageObject {
    public static final long PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS = 15;

    private static final String COMPLETE = "complete";
    private static final String RETURN_DOCUMENT_READY_STATE = "return document.readyState";
    private static final String SCROLL_INTO_VIEW_SCRIPT = "arguments[0].scrollIntoView();";

    protected final WebDriver driver;

    public BasePageObject(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void scrollToWithJsScript(final WebElement webElement) {
        log.info("Scrolling to element. {}", webElement);
        ((JavascriptExecutor) driver).executeScript(SCROLL_INTO_VIEW_SCRIPT, webElement);
    }

    protected void navigateToUrl(final String url) {
        log.info("WebDriver navigating to URL {}", url);
        driver.get(url);
        waitForPageToLoad();
    }

    protected void openPageWithRetry(final String url) {
        int retryCount = 0;
        while (retryCount < 3) {
            try {
                log.info("WebDriver navigating to URL with retry {}", url);
                driver.get(url);
                break;
            } catch (TimeoutException exception) {
                retryCount++;
                if (retryCount == 3) throw exception;
                log.info("Retrying to load the page {}", url);
            }
        }
    }

    protected WebDriverWait getWebDriverWait() {
        return new WebDriverWait(this.driver, Duration.ofSeconds(PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS));
    }

    protected WebElement waitForElementToBeVisible(final WebElement webElement) {
        log.info("Waiting for element to be fully visible: {}.", webElement);
        try {
            getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement));
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Element is not visible!");
        }
        return webElement;
    }

    protected WebElement waitForElementToBeClickable(final WebElement webElement) {
        log.info("Waiting before clicking on element {}.", webElement);
        try {
            getWebDriverWait().until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Element is not clickable!");
        }
        return webElement;
    }

    protected void waitForPageToLoad() {
        log.info("Waiting for the page to finish loading.");
        new WebDriverWait(this.driver, Duration.ofSeconds(PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS)).until(
                driver -> valueOf(((JavascriptExecutor) driver).executeScript(RETURN_DOCUMENT_READY_STATE)).equals(COMPLETE)
        );
    }
}
