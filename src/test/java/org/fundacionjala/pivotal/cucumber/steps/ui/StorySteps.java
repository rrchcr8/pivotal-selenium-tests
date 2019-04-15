package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import org.fundacionjala.core.Environment;
import org.fundacionjala.core.api.RequestManager;
import org.fundacionjala.pivotal.pages.Dashboard;
import org.fundacionjala.pivotal.pages.Project;
import org.fundacionjala.pivotal.pages.Story;
import org.fundacionjala.util.ScenarioContext;
import org.fundacionjala.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * this is an story steps.
 */
public class StorySteps {
    @Autowired
    private Project project;
    @Autowired
    private Dashboard dashboard;
    @Autowired
    private Story story;
    /**
     * Given step for story feature.
     * @param arg0 is the name of the project.
     */
    @Given("a project called {string}")
    public void aProjectCalled(final String arg0) {
    dashboard.goToProject(arg0);
    //story.clickAddButton()1;
    story.createStory("ird test");
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

    @When("creates a project called {string}")

    public void createsAProjectCalled(String arg0) {
        String name= ((JsonPath) ScenarioContext.getInstance().getContext("Resp")).get("name").toString();
        dashboard.goToProject(name);
        story.createStory(arg0);
    }
}
