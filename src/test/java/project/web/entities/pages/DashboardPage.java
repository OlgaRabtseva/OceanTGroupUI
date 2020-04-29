package project.web.entities.pages;

import core.web.pageobject.PageObject;
import core.web.utils.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends PageObject {
    private By dashboardNavBar = By.xpath("//*[contains(@class, 'navbar ')]");

    public DashboardPage(TestContext testContext) {
        super(testContext);
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardNavBar));
    }
}
