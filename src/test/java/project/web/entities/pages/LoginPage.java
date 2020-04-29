package project.web.entities.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import core.web.pageobject.PageObject;
import core.web.utils.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends PageObject {

    private By companyField = By.xpath("//*[@data-test='login-company-input']");
    private By userIdField = By.xpath("//*[@data-test='login-userid-input']");
    private By passwordField = By.xpath("//*[@data-test='login-password-input']");
    private By loginBtn = By.xpath("//*[@data-test='login-submit-button']");
    private By loginDialogBox = By.xpath("//*[contains(@class, 'login-box')]");
    private By errorMessage = By.xpath("//*[@data-test='login-error-message']");

    public LoginPage(TestContext testContext) {
        super(testContext);
        open(testContext.getBaseURL());
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginDialogBox));
    }

    private void enterCompany(String company) {
        cleanField(companyField);
        $(companyField).sendKeys(company);
        $(companyField).shouldHave(Condition.value(company));
    }

    private void enterUserID(String userId) {
        cleanField(userIdField);
        $(userIdField).sendKeys(userId);
        $(userIdField).shouldHave(Condition.value(userId));
    }

    private void enterPassword(String pass) {
        cleanField(passwordField);
        $(passwordField).sendKeys(pass);
        $(passwordField).shouldHave(Condition.value(pass));
    }

    private void submitForm() {
        $(loginBtn).click();
    }

    public DashboardPage doSuccessLogin(String company, String userId, String password) {
        enterCompany(company);
        enterUserID(userId);
        enterPassword(password);
        submitForm();
        Selenide.sleep(500);
        return new DashboardPage(getTestContext());
    }

    public LoginPage doFailLogin(String company, String userId, String password) {
        enterCompany(company);
        enterUserID(userId);
        enterPassword(password);
        submitForm();
        Selenide.sleep(500);
        return this;
    }

    public String getErrorMessage() {
        return $(errorMessage).text();
    }



}
