package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.Given;
import org.fundacionjala.pivotal.pages.Dashboard;
import org.fundacionjala.pivotal.pages.Project;
import org.fundacionjala.pivotal.pages.Story;
import org.springframework.beans.factory.annotation.Autowired;

public class StorySteps {
    @Autowired
    private Project project;
    @Autowired
    private Dashboard dashboard;
    @Autowired
    private Story story;

    @Given("a project called {string}")
    public void aProjectCalled(String arg0) {
    dashboard.goToProject(arg0);
    story.clickAddButton();
    }
}
