package com.homework.test.automation.drivercreators;

import com.homework.test.automation.client.JsonPlaceHolderClientOkHttp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChromiumDriverCreator {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChromiumDriverCreator.class);
    private static final String HEADLESS_NEW = "--headless=new";

    @Value("${headless:false}")
    private Boolean headless;

    public WebDriver createEdgeDriver() {
        LOGGER.info("Creating MS Edge driver.");
        EdgeOptions edgeOptions = new EdgeOptions();
        if (headless) {
            edgeOptions.addArguments(HEADLESS_NEW);
        }

        return new EdgeDriver(edgeOptions);
    }

    public WebDriver createChromeDriver() {
        LOGGER.info("Creating Google Chrome driver.");
        ChromeOptions chromeOptions = new ChromeOptions();
        if (headless) {
            chromeOptions.addArguments(HEADLESS_NEW);
        }
        return new ChromeDriver(chromeOptions);
    }

}
