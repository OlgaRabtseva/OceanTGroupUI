package project.web.tests.login;

import core.web.utils.TestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import project.utils.TestSetup;
import project.web.entities.pages.DashboardPage;
import project.web.entities.pages.LoginPage;

import static com.codeborne.selenide.Selenide.refresh;
import static org.testng.Assert.assertNotEquals;
import static project.utils.Properties.*;

public class PositiveLoginTest extends TestSetup {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    TestContext testContext;

    @BeforeClass
    public void setUp() {
        testContext = new TestContext(BASE_URL, getDriver());
        loginPage = new LoginPage(testContext);
    }

    @Test
    public void successLoginTest() {
        refresh();
        dashboardPage = loginPage.doSuccessLogin(COMPANY, USER_ID, PASSWORD);

        assertNotEquals(dashboardPage, null);
    }
}
