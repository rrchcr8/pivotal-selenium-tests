package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.restassured.path.json.JsonPath;
import org.fundacionjala.core.api.RequestManager;
import org.fundacionjala.core.ui.driver.DriverManager;
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
    private Project project;
    /**
     * Logs in with user.
     *
     * @param key for start session.
     */
    @Given("logs in with user {string}")
    public void logsInWithUser(final String key) {
        String userNameKey = String.format("credentials.%s.username", key);
        String passwordKey = String.format("credentials.%s.password", key);
        DriverManager.getInstance().getDriver().get(Environment.getInstance().getValue("url.base"));
        login.loginAs(Environment.getInstance().getValue(userNameKey), Environment.getInstance().getValue(passwordKey));
    }


    @Given("send a POST request {string}")
    public void sendAPOSTRequest(String arg0,final Map<String, String> projectAttributes) {
        ScenarioContext.getInstance().setContext("url.api", Environment.getInstance().getValue("url.api"));

        final String projectUrl = StringUtil.getExplicitEndpoint(arg0);
        String projectName = projectAttributes.get("name");
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", projectName);
        JsonPath resp = RequestManager.postRequest(projectUrl, parameters).body()
                .jsonPath();
        ScenarioContext.getInstance().setContext("Resp",resp);
    }

    @And("stores response as {string}")
    public void storeRespondeAs(String arg0) {
        ScenarioContext.getInstance().setContext(arg0,ScenarioContext.getInstance().getContext("Resp"));
    }
}
