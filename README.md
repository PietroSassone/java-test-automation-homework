# Demo project to demonstrate test automation framework development for UI testing

**Test automation UI framework demo project.**

UI test automation in Java 17, demonstrating how to implement a scalable, flexible framework for running UI tests in parallel.
Supporting different browsers.
The UI tests are running in parallel. The API test suite is set to execute after.

**1. Technologies used**
- Selenium 4 for UI tests
- TestNG for executing the tests
- Maven to help running the framework and manage dependencies
- Maven Surefire plugin to run the test suite with automatic test report
- Logback for logging
- Spring Core for dependency injection & system property handling
- Jackson for deserializing JSON files into a Java class
- Apache HTTP client and OKHTTP client for API tests

**2. Reporting and logging**  
The framework saves reports and logs in the target folder after a test run finishes.
1. Logs are saved in target/logs
1. TestNG and Surefire reports are saved in target/surefire-reports

**3. Pre-requirements for running the tests**
- Have Maven installed.
- Have Java installed, at least version 17.

**4. Launching the tests**  
Open a terminal and type:
```
mvn clean test
```
 
Supported arguments:  
| argument name     | supported values      | default value | description                                                |
| ----------------- | --------------------- | ------------- | ---------------------------------------------------------- |
| browserName       | chrome, edge, firefox | chrome        | tells the tests which browser to use                       |
| headless          | true, false           | true          | sets whether the tests should run with GUI enabled         |

Example command to run the tests with MS Edge Driver in non-headless mode:  
```
mvn clean test -DbrowserName=edge -Dheadless=false
```

**5. GitHub Action**
The framework has a simple GitHub Actions workflow setup to run the test on a CI job.
1. The job accepts 1 input: the browserName argument to set the browser.
2. The browser is set to headless mode by default for the job.
3. The workflow uploads the zipped test reports and logs at the end.
