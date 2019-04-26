package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.fundacionjala.core.Environment;
import org.fundacionjala.core.ui.driver.DriverManager;
import org.fundacionjala.pivotal.pages.Dashboard;
import org.fundacionjala.pivotal.pages.Login;
import org.fundacionjala.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

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
        final String userNameKey = String
                .format("credentials.%s.username", key);
        final String passwordKey = String
                .format("credentials.%s.password", key);
        DriverManager.getInstance().getDriver().get(Environment.getInstance()
                .getValue("url.login"));
        this.login.loginAs(Environment.getInstance().getValue(userNameKey),
                Environment.getInstance().getValue(passwordKey));
    }

    /**
     * This method reload page to go dashboard.
     *
     * @param tabName name of dashboard tab.
     **/
    @Given("Go to Dashboard {string}")
    public void goToDashboardAndTab(final String tabName) {
        this.dashboard.reload();
        if (tabName.toLowerCase().contains("workspace")) {
            this.dashboard.goToWorkSpaceTab();
        }
        LOGGER.info(String.format("Method: isExistingSelectorBy -> FALSE %s",
                element));
    }

    /**
     * @param projectKeyName is the name of the project.
     */
    @And("opens a project {string}")
    public void opensAProject(final String projectKeyName) {
        final String projectName = StringUtil.getValue(projectKeyName);
        this.dashboard.goToProject(projectName);
    }
}
