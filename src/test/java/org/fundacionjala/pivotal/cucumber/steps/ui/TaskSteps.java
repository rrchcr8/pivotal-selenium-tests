package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fundacionjala.pivotal.pages.Tasks;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskSteps {

    @Autowired
    private Tasks tasksPanel;

    @Given("Project with one story")
    public void projectWithOneStory() {
    }

    @When("Create a task with text {string}")
    public void createATaskWithText(final String text) {
        this.tasksPanel.clickOnAddTaskButton();
        this.tasksPanel.setTaskText(text);
        this.tasksPanel.clickOnSave();
        ScenarioContext.getInstance().setContext("taskName", text);
    }

    @Then("Verify that task was created")
    public void verifyThatTaskWasCreated() {
        final String text = (String) ScenarioContext.getInstance()
                .getContext("taskName");
        this.tasksPanel.existTask(text);
    }
}
