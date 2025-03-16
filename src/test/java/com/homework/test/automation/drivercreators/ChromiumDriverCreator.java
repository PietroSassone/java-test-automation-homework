package com.homework.test.automation.drivercreators;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChromiumDriverCreator {
    private static final String HEADLESS_NEW = "--headless=new";

    @Value("${headless:true}")
    private Boolean headless;

    public WebDriver createEdgeDriver() {
        log.info("Creating MS Edge driver.");
        EdgeOptions edgeOptions = new EdgeOptions();
        if (headless) {
            edgeOptions.addArguments(HEADLESS_NEW);
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--no-proxy-server");
            options.addArguments("--disable-extensions");
            options.addArguments("--incognito");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-images");
            options.addArguments("--no-sandbox");
        }

        return new EdgeDriver(edgeOptions);
    }

    public WebDriver createChromeDriver() {
        log.info("Creating Google Chrome driver.");
        ChromeOptions chromeOptions = new ChromeOptions();
        if (headless) {
            chromeOptions.addArguments(HEADLESS_NEW);
        }
        return new ChromeDriver(chromeOptions);
    }

}
