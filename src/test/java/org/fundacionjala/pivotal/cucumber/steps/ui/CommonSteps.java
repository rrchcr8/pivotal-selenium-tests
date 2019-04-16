package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.restassured.path.json.JsonPath;
import org.fundacionjala.core.api.RequestManager;
import org.fundacionjala.core.ui.driver.DriverManager;
import org.fundacionjala.pivotal.pages.Dashboard;
import org.fundacionjala.pivotal.pages.Project;
import org.fundacionjala.util.ScenarioContext;
import org.fundacionjala.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.fundacionjala.core.Environment;
import org.fundacionjala.pivotal.pages.Login;

import java.util.HashMap;
import java.util.Map;

/**
 * Common steps.
 */
public class CommonSteps {

    @Autowired
    private Login login;

    @Autowired
    private Dashboard dashboard;

    /**
     * Logs in with user.
     *
     * @param key for start session.
     */
    @Given("logs in with user {string}")
    public void logsInWithUser(final String key) {
        String userNameKey = String.format("credentials.%s.username", key);
        String passwordKey = String.format("credentials.%s.password", key);
        DriverManager.getInstance().getDriver().get(Environment.getInstance().getValue("url.login"));
        login.loginAs(Environment.getInstance().getValue(userNameKey), Environment.getInstance().getValue(passwordKey));
    }

    @And("opens a project {string}")
    public void opensAProject(String projectKeyName) {
        String projectName = StringUtil.getValue(projectKeyName);
        dashboard.goToProject(projectName);
    }

    /** This method reload page to go dashboard. **/
    @And("Go to Dashboard")
    public void goToDashboard() {
        this.dashboard.reload();
    }
}
