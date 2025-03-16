package com.homework.test.automation.drivercreators;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FirefoxDriverCreator {

    @Value("${headless:true}")
    private Boolean headless;

    public WebDriver createFirefoxDriver() {
        log.info("Creating Firefox driver.");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        if (headless) {
            firefoxOptions.addArguments("--headless");
        }
        return new FirefoxDriver(firefoxOptions);
    }
}
