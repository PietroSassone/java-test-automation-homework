package com.homework.test.automation.drivercreators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FirefoxDriverCreator {
    private static final Logger LOGGER = LoggerFactory.getLogger(FirefoxDriverCreator.class);

    @Value("${headless:false}")
    private Boolean headless;

    public WebDriver createFirefoxDriver() {
        LOGGER.info("Creating Firefox driver.");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (headless) {
            firefoxOptions.addArguments("--headless");
        }
        return new FirefoxDriver(firefoxOptions);
    }
}
