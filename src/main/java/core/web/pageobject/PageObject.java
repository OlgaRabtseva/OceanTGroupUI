package core.web.pageobject;

import com.codeborne.selenide.SelenideElement;
import core.web.utils.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

abstract public class PageObject {

    private TestContext testContext;

    public static Wait<WebDriver> wait;

    protected PageObject(TestContext testContext) {
        this.testContext = testContext;
        wait = new FluentWait<>(testContext.getDriver())
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
    }

    protected TestContext getTestContext() {
        return testContext;
    }

    protected void cleanField(By field) {
        SelenideElement fieldValue = $(field).parent();
        List<String> fieldTypes = Arrays.asList("input", "textarea");
        for(String fieldType : fieldTypes) {
            SelenideElement typedField = fieldValue.find(fieldType);
            sleep(1000);
            if (typedField.exists()) {
                fieldValue = typedField;
                break;
            }
        }
        fieldValue.click();
        fieldValue.sendKeys(Keys.CONTROL + "a");
        fieldValue.sendKeys(Keys.DELETE);
        fieldValue.pressEnter();
        sleep(500);
    }
}
