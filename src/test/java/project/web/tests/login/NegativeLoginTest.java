package project.web.tests.login;

import core.web.utils.TestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import project.utils.TestSetup;
import project.web.entities.pages.LoginPage;

import static com.codeborne.selenide.Selenide.refresh;
import static org.testng.Assert.assertEquals;
import static project.utils.Properties.*;


public class NegativeLoginTest extends TestSetup {
    LoginPage loginPage;
    TestContext testContext;

    @BeforeClass
    public void setUp() {
        testContext = new TestContext(BASE_URL, getDriver());
        loginPage = new LoginPage(testContext);
    }

    @Test
    public void emptyPasswordTest() {
        refresh();
        loginPage.doFailLogin(COMPANY, USER_ID, "");

        assertEquals(loginPage.getErrorMessage(), "Missing obligatory information.");
    }

    @Test
    public void wrongUserIdTest() {
        refresh();
        loginPage.doFailLogin(COMPANY, "User", PASSWORD);

        assertEquals(loginPage.getErrorMessage(), "Invalid user name or password.");
    }

    @Test
    public void wrongCompanyNameTest() {
        refresh();
        loginPage.doFailLogin("Demo", USER_ID, PASSWORD);

        assertEquals(loginPage.getErrorMessage(), "Given company is not registered.");
    }


}
