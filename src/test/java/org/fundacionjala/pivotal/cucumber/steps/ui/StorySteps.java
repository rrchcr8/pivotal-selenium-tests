package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fundacionjala.pivotal.pages.Dashboard;
import org.fundacionjala.pivotal.pages.DeleteModal;
import org.fundacionjala.pivotal.pages.HeaderContainer;
import org.fundacionjala.pivotal.pages.Panel;
import org.fundacionjala.pivotal.pages.Project;
import org.fundacionjala.pivotal.pages.ProjectWorkspaceList;
import org.fundacionjala.pivotal.pages.Story;
import org.fundacionjala.util.ScenarioContext;
import org.fundacionjala.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.util.Map;

/**
 * this is an story steps.
 */
public class StorySteps {
    @Autowired
    private Project project;
    @Autowired
    private ProjectWorkspaceList projectList;
    @Autowired
    private Dashboard dashboard;
    @Autowired
    private Story story;
    @Autowired
    private Panel panel;
    @Autowired
    private HeaderContainer headerContainer;
    @Autowired
    private DeleteModal deleteModal;


    /**
     * Given step for story feature.
     *
     * @param projectName is the name of the project.
     */
    @Given("a project called {string}")
    public void aProjectCalled(final String projectName) {
        this.dashboard.goToProject(projectName);
        this.panel.clickAddButton();
        this.story.createStory("ird test");
    }

    /**
     * @param name the name of the story.
     */
    @When("creates a story called {string}")
    public void createsAStoryCalled(final String name) {
        this.panel.clickAddButton();
        this.story.createStory(name);
        ScenarioContext.getInstance().setContext("story_name", name);
    }

    /** This method verifies that a story is created. */
    @Then("verify the story is created")
    public void verifytheStoryIsCreated() {
        final String storyName = ScenarioContext.getContextAsString("story_name");
        Assert.assertTrue(this.panel.existStory(storyName));
    }

    /** @param storyName name of other story. */
    @When("creates other a story called {string}")
    public void createsOtherAStoryCalled(final String storyName) {
        this.panel.clickAddButton();
        this.story.createStory(storyName);
    }

    /**
     * This method clicks the expand button for a specific story.
     *
     * @param storyKeyName the name of the story.
     */
    @When("expands the story {string}")
    public void expandsTheStory(final String storyKeyName) {
        final String storyName = StringUtil.getValue(storyKeyName);
        this.panel.expandStory(storyName);
    }

    /** This method clicks the delete button inside the story page. */
    @And("click delete button")
    public void clickDeleteButton() {
        this.story.clickDeleteButton();
    }

    /**
     * Not a reliable method.
     *
     * @param storyNameKey not really sure (it was in progress).
     */
    @When("selects the bulk of {string}")
    public void deletesSelectingTheCheckboxof(final String storyNameKey) {
        final String storyName = StringUtil.getValue(storyNameKey);
        this.panel.clickStoryCheckboxButton(storyName);


    }

    /**
     * @param storyNameKey is the name of the story.
     */
    @Then("Verify that the story {string} is deleted")
    public void verifyThatTheStoryIsDeleted(final String storyNameKey) {
        final String storyName = StringUtil.getValue(storyNameKey);
        Assert.assertFalse(this.panel.existStory(storyName));
    }

    /** clicks the button of the header container. */
    @And("click delete button of Header container")
    public void clickDeleteButtonOfHeaderContainer() {
        this.headerContainer.clickDeleteButtonOfToast();
    }

    /** Confirm button. */
    @And("click confirm delete button")
    public void clickConfirmDeleteButton() {
        this.deleteModal.clickConfirmDeleteButton();
    }

    @Then("verifies the story is created in panel")
    public void verifiesTheStoryIsCreatedInPanel() {
        final String storyName = ScenarioContext.getContextAsString("story_name");
        Assert.assertTrue(this.panel.existStory(storyName));
    }

    @And("verifies the story is created in story")
    public void verifiesTheStoryIsCreatedInStory() {

    }

    @When("creates a story with:")
    public void createsAStoryWith(final Map<String, String> attributes) {
        this.story.createStory(attributes);
    }

    @And("verifies the story is created in project list")
    public void verifiesTheStoryIsCreatedInProjectList() {
        final String projectName = ScenarioContext
                .getContextAsString("project_response.name");
        final String amount = this.projectList.getAmountOfStories(projectName);
        Assert.assertEquals(amount, "1");
    }
}
