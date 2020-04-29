package project.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static com.codeborne.selenide.Selenide.sleep;
import static project.utils.BrowserName.*;

public class TestSetup {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    @BeforeTest
    @Parameters("browserName")
    protected void initializeTestSetup(@Optional("chrome") String browserName) {
        try {
            System.out.println(browserName);
            setDriver(browserName);

        } catch (Exception e) {
            System.out.println("Error…" + e.getStackTrace());
        }
    }

    @AfterTest
    protected void tearDown() {
        driver.quit();
    }

    private void setDriver(String browserName) {
        BrowserName browser = BrowserName.fromName(browserName);
        switch (browser) {
            case FIREFOX: {
                driver = setFirefoxDriverConfiguration();
                break;
            }
            case EDGE: {
                driver = setEdgeDriverConfiguration();
                sleep(3000);
                break;
            }
            default: {
                driver = setChromeDriverConfiguration();
                break;
            }
        }
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
    }

    private WebDriver setChromeDriverConfiguration() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        Configuration.browser = CHROME.getName();
        return driver;
    }

    private WebDriver setEdgeDriverConfiguration() {
        System.setProperty("webdriver.edge.useJsonWireProtocol", "useIt");

        WebDriver driver = new EdgeDriver();
        Configuration.browser = EDGE.getName();
        return driver;
    }

    private WebDriver setFirefoxDriverConfiguration() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        Configuration.browser = FIREFOX.getName();
        return driver;
    }
}

