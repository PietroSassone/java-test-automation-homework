package com.homework.test.automation.factory;

import com.homework.test.automation.drivercreators.ChromiumDriverCreator;
import com.homework.test.automation.drivercreators.FirefoxDriverCreator;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DriverFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class);
    private static final String FIREFOX_BROWSER_NAME = "firefox";
    private static final String EDGE_BROWSER_NAME = "edge";

    @Value("${browserName:chrome}")
    private String browserName;

    @Autowired
    private ChromiumDriverCreator chromiumDriverCreator;

    @Autowired
    private FirefoxDriverCreator firefoxDriverCreator;

    private WebDriver webDriver;

//    private Map<String, Callable<WebDriver>> webDriverSetupMethodsMap;

//    @Autowired
//    public DriverFactory(final ChromiumDriverCreator chromiumDriverCreator) {
//        this.chromiumDriverCreator = chromiumDriverCreator;
//    }

//    @PostConstruct
//    private void setUpWebDriverConfigMethods() {
//        System.out.println("Setting up WebDriver configuration methods...");
//        webDriverSetupMethodsMap = Map.of(
//                CHROME_BROWSER_NAME, chromiumDriverCreator::createChromeDriver,
//                EDGE_BROWSER_NAME, chromiumDriverCreator::createEdgeDriver,
//                FIREFOX_BROWSER_NAME, firefoxDriverCreator::createFirefoxDriver
//        );
//    }

    public WebDriver getExistingWebDriver() {
        return getWebDriver(false);
    }

    public WebDriver createAndGetWebDriver() {
        return getWebDriver(true);
    }

    public void shutDownWebDriver() {
        if (Objects.nonNull(webDriver)) {
            try {
                webDriver.close();
                webDriver.quit();
            } catch (Exception e) {
                LOGGER.info("Browser already closed, did not need to quit. Exception: {}", e.getMessage());
            }

            webDriver = null;
            LOGGER.info("Selenium WebDriver was shut down.");
        }
    }

    private WebDriver getWebDriver(final boolean shouldCreateNewDriver) {
        if (shouldCreateNewDriver && Objects.isNull(webDriver)) {
            try {
                switch (browserName) {
                    case FIREFOX_BROWSER_NAME -> webDriver = firefoxDriverCreator.createFirefoxDriver();
                    case EDGE_BROWSER_NAME -> webDriver = chromiumDriverCreator.createEdgeDriver();
                    default -> webDriver = chromiumDriverCreator.createChromeDriver();
                }
            } catch (NullPointerException nullPointerException) {
                throw new RuntimeException("Trying to set up unsupported browser type=" + browserName);
            } catch (Exception e) {
                throw new RuntimeException("Issue when trying to start the driver. Message=" + e.getMessage());
            }
        }
        return webDriver;
    }
}
