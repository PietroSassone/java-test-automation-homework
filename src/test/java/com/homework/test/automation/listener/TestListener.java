package com.homework.test.automation.listener;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class TestListener implements ITestListener {

    @Override
    public void onTestSuccess(final ITestResult result) {
        log.info("Test passed: {}!", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(final ITestResult result) {
        log.info("Test failed: {}!", result.getMethod().getMethodName());

        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");

        final File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        final String timestamp = new SimpleDateFormat("yyyy-MM-dd HHmmss").format(new Date());
        final File screenshotFile = new File("target/surefire-reports/failure-" + result.getMethod().getMethodName() + "-" + timestamp + ".png");

        try {
            log.info("Saving screenshot in the target/surefire-reports folder.");
            FileHandler.copy(screenshot, screenshotFile);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
