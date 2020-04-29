package core.web.utils;

import org.openqa.selenium.WebDriver;

public class TestContext {
    private String baseURL;
    private WebDriver driver;

    public TestContext(String baseURL, WebDriver driver) {
        this.baseURL = baseURL;
        this.driver = driver;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
