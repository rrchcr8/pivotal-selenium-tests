package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.fundacionjala.core.api.services.ProjectService;
import org.fundacionjala.core.api.services.StoryService;
import org.fundacionjala.core.ui.forms.FormsElements;
import org.fundacionjala.pivotal.pages.Dashboard;
import org.fundacionjala.pivotal.pages.Project;
import org.fundacionjala.pivotal.pages.Tasks;
import org.fundacionjala.util.ScenarioContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.asserts.SoftAssert;

import java.util.Map;


/**
 * This class will have steps for task feature.
 **/
public class TaskSteps {
    private static final Logger LOGGER =
            Logger.getLogger(TaskSteps.class.getName());

    private static final String TASK_NAME = "taskName";

    @Autowired
    private Dashboard dashboard;

    @Autowired
    private Project project;

    @Autowired
    private Tasks tasksPanel;
    @Autowired
    private SoftAssert softAssert;

    /**
     * Step to set default project with story.
     **/
    @And("Project with one story")
    public static void projectWithOneStory() {
        final int id = ProjectService.createProject("My test project");
        ScenarioContext.getInstance().setContext("defaultProjectId", id);
        final int taskId = StoryService.createStory(id, "Task test");
        ScenarioContext.getInstance().setContext("defaultTaskId", taskId);
    }

    /**
     * Before steps for every feature.
     *
     * @param scenario Scenario
     **/
    @Before
    public static void before(final Scenario scenario) {
        LOGGER.info(String.format("@Before %s  Status - %s", scenario.getName(),
                scenario.getStatus()));
    }

    /**
     * After steps for every feature.
     *
     * @param scenario Scenario
     */
    @After("@DeleteProject")
    public static void after(final Scenario scenario) {
        LOGGER.info(String.format("@After.1 %s  Status - %s", scenario.getName(),
                scenario.getStatus()));
        final int projectId = (Integer) ScenarioContext.getInstance()
                .getContext("defaultProjectId");
        ProjectService.deleteProject(projectId);
        LOGGER.info(String.format("@After.2 %s  Status - %s", scenario.getName(),
                scenario.getStatus()));
    }

    /**
     * Step to create a task with text.
     *
     * @param taskAttributes Map.
     */
    @When("adds a task to current Story")
    public void addsATaskToCurrentStory(final Map<String, String> taskAttributes) {
        this.tasksPanel.clickOnAddTaskButton();
        this.tasksPanel.setTaskText(taskAttributes.get(FormsElements.NAME.toString()));
        this.tasksPanel.clickOnSave();
        ScenarioContext.getInstance().setContext(TASK_NAME, FormsElements.NAME.toString());
    }

    /**
     * Step to verify that the task was added to story.
     */
    @Then("validates task aggregation")
    public void verifyTheTaskWasAdded() {
        final String text = (String) ScenarioContext
                .getContextAsString(TASK_NAME);
        softAssert.assertTrue(this.tasksPanel.existTask(text));
    }

    /**
     * Step to modify a task.
     *
     * @param taskAttributes Map of task attributes.
     **/
    @When("edits the task name")
    public void editTaskName(final Map<String, String> taskAttributes) {
        final String text = (String) ScenarioContext
                .getContextAsString(TASK_NAME);
        this.tasksPanel.selectTask(text);
        this.tasksPanel.setTaskText(taskAttributes.get(FormsElements.NAME.toString()));
        this.tasksPanel.clickOnSave();
        ScenarioContext.getInstance().setContext(TASK_NAME, taskAttributes.get(FormsElements.NAME.toString()));
    }

    /**
     * Step to verify that a task doesn't exist.
     */
    @Then("the old task should not be listed")
    public void verifyThatTaskNotListed() {
        softAssert.assertFalse(this.tasksPanel.existTask((String) ScenarioContext
                .getContextAsString(TASK_NAME)));
    }

    /**
     * Step to verify that a task with text exist.
     *
     * @param name string
     **/
    @And("Task with name {string} exist")
    public void taskWithNameExist(final String name) {
        softAssert.assertTrue(this.tasksPanel.existTask(name));
    }

    /**
     * Step to delete a task.
     **/
    @When("deletes the task")
    public void deleteTask() {
        final String text = (String) ScenarioContext
                .getContextAsString(TASK_NAME);
        this.tasksPanel.deleteTask(text);
    }

    /**
     * Step to open a project.
     **/
    @And("Go to default project")
    public void goToDefaultProject() {
        this.dashboard.goToProject("My test project");
    }

    /**
     * Step to open a story.
     **/
    @And("Open a story")
    public void openAStory() {
        this.project.expandOneStory();
    }
}
