package com.homework.test.automation.factory;

import com.homework.test.automation.drivercreators.ChromiumDriverCreator;
import com.homework.test.automation.drivercreators.FirefoxDriverCreator;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.Callable;

@Slf4j
@Component
public class DriverFactory {
    private static final String CHROME_BROWSER_NAME = "chrome";
    private static final String FIREFOX_BROWSER_NAME = "firefox";
    private static final String EDGE_BROWSER_NAME = "edge";

    @Value("${browserName:chrome}")
    private String browserName;

    @Autowired
    private ChromiumDriverCreator chromiumDriverCreator;

    @Autowired
    private FirefoxDriverCreator firefoxDriverCreator;

    private Map<String, Callable<WebDriver>> webDriverSetupMethodsMap;

    @PostConstruct
    private void setUpWebDriverConfigMethods() {
        webDriverSetupMethodsMap = Map.of(
                CHROME_BROWSER_NAME, chromiumDriverCreator::createChromeDriver,
                EDGE_BROWSER_NAME, chromiumDriverCreator::createEdgeDriver,
                FIREFOX_BROWSER_NAME, firefoxDriverCreator::createFirefoxDriver
        );
    }

    public WebDriver createAndGetWebDriver() {
        WebDriver webDriver;
        log.info("Requested driver creation with name: {}", browserName);
        try {
            webDriver = webDriverSetupMethodsMap.get(browserName).call();
        } catch (NullPointerException nullPointerException) {
            throw new RuntimeException("Trying to set up unsupported browser type=" + browserName);
        } catch (Exception e) {
            throw new RuntimeException("Issue when trying to start the driver. Message=" + e.getMessage());
        }
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        return webDriver;
    }
}
