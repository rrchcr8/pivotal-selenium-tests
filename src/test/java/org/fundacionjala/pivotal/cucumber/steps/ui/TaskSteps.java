package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fundacionjala.core.api.services.ProjectService;
import org.fundacionjala.core.api.services.StoryService;
import org.fundacionjala.pivotal.pages.Tasks;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TaskSteps {

    @Autowired
    private Tasks tasksPanel;

    @Given("Project with one story")
    public void projectWithOneStory() {
        final int id = ProjectService.createProject("My test project");
        ScenarioContext.getInstance().setContext("defaultProjectId", id);
        final int taskId = StoryService.createStory(id, "Task test");
        ScenarioContext.getInstance().setContext("defaultTaskId", id);
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
        assertTrue(this.tasksPanel.existTask(text));
    }

    @When("Modify text by {string}")
    public void modifyTextBy(final String newText) {
        final String text = (String) ScenarioContext.getInstance()
                .getContext("taskName");
        this.tasksPanel.selectTask(text);
        this.tasksPanel.setTaskText(newText);
        this.tasksPanel.clickOnSave();
        ScenarioContext.getInstance().setContext("taskName", newText);
    }

    @Then("Verify that task {string} doesn't exist")
    public void verifyThatTaskDoesnTExist(final String text) {
        assertFalse(this.tasksPanel.existTask(text));
    }

    @And("Task with name {string} exist")
    public void taskWithNameExist(final String text) {
        assertTrue(this.tasksPanel.existTask(text));
    }

    @Before
    public void before(final Scenario scenario) {
        System.out.println("------------------------------");
        System.out.println(scenario.getName() + " Status - " + scenario.getStatus());
        System.out.println("------------------------------");
    }

    @After
    public void after(final Scenario scenario) {
        System.out.println("------------------------------");
        System.out.println(scenario.getName() + " Status - " + scenario.getStatus());
        final int projectId = (Integer) ScenarioContext.getInstance()
                .getContext("defaultProjectId");
        ProjectService.deleteProject(projectId);
        System.out.println("------------------------------");
    }

    @When("Delete task")
    public void deleteTask() {
        final String text = (String) ScenarioContext.getInstance()
                .getContext("taskName");
        this.tasksPanel.deleteTask(text);
    }
}
