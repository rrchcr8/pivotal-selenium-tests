package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.fundacionjala.core.Environment;
import org.fundacionjala.core.api.RequestManager;
import org.fundacionjala.core.ui.forms.FormsElements;
import org.fundacionjala.pivotal.pages.Dashboard;
import org.fundacionjala.pivotal.pages.DeleteModal;
import org.fundacionjala.pivotal.pages.HeaderContainer;
import org.fundacionjala.pivotal.pages.ISteps;
import org.fundacionjala.pivotal.pages.Panel;
import org.fundacionjala.pivotal.pages.Project;
import org.fundacionjala.pivotal.pages.ProjectWorkspaceList;
import org.fundacionjala.pivotal.pages.Story;
import org.fundacionjala.util.ScenarioContext;
import org.fundacionjala.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this is an story steps.
 */
public class StorySteps {
    private static final Logger LOGGER =
            Logger.getLogger(StorySteps.class.getName());
    private final String allFields = "all_story_fields";
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

    private List<String> names;

    /**
     * This method clicks the expand button for a specific story.
     *
     * @param storyName the name of the story.
     */
    @When("expands the story {string}")
    public void expandsTheStory(final String storyName) {
        this.panel.expandStory(storyName);
    }

    /** This method clicks the delete button inside the story page. */
    @When("clicks delete button")
    public void clickDeleteButton() {
        this.story.clickDeleteButton();
    }

    /**
     * Not a reliable method.
     *
     * @param params not really sure (it was in progress).
     */
    @When("selects the bulk of:")
    public void selectsStoryBulk(final List<String> params) {
        this.names = new ArrayList();
        for (final String storyNameKey : params) {
            final String storyName = StringUtil
                    .getValue(storyNameKey);
            this.panel.clickStoryCheckboxButton(storyName);
            this.names.add(storyName);
        }
    }

    @Then("verifies that the stories are not present on panel")
    public void verifiesThatTheStoriesAreNotPresentOnPanel() {
        for (final String storyName : this.names) {
            this.panel.existStory(storyName);
        }
    }

    /**
     * @param storyNameKey is the name of the story.
     */
    @Then("verifies that the story {string} is deleted")
    public void verifyThatTheStoryIsDeleted(final String storyNameKey) {
        final String storyName = StringUtil.getValue(storyNameKey);
        Assert.assertFalse(this.panel.existStory(storyName));
    }

    /** clicks the button of the header container. */
    @When("clicks delete button of Header container")
    public void clickDeleteButtonOfHeaderContainer() {
        this.headerContainer.clickDeleteButtonOfToast();
    }

    /** Confirm button. */
    @When("clicks confirm delete button")
    public void clickConfirmDeleteButton() {
        this.deleteModal.clickConfirmDeleteButton();
    }

    /** This step verifies that story appears in panel. **/
    @Then("verifies the story is created in panel")
    public void verifiesTheStoryIsCreatedInPanel() {
        final String storyName = ScenarioContext
                .getContextInMapAsString("all_story_fields", "name");
        Assert.assertTrue(this.panel.existStory(storyName));
    }

    /**
     * This step verifies that story have all data provided in create step.
     *
     * @param attributes input data.
     **/
    @Then("verifies the story is created in story")
    public void verifiesTheStoryIsCreatedInStory(final Map<String, String> attributes) {
        final Map<String, ISteps> strategy = new HashMap<>();
        strategy.put(FormsElements.NAME.key(), () ->
                Assert.assertEquals(this.story.getStoryNameText(),
                        attributes.get(FormsElements.NAME.key())));
        strategy.put(FormsElements.STORY_TYPE.key(), () ->
                Assert.assertEquals(this.story.getStoryTypeText(),
                        attributes.get(FormsElements.STORY_TYPE.key())));
        strategy.put(FormsElements.ESTIMATED_POINTS.key(), () ->
                Assert.assertEquals(this.story.getEstimatedPoints(),
                        attributes.get(FormsElements.ESTIMATED_POINTS.key())));
        strategy.put(FormsElements.REQUESTER.key(), () -> {
            final String key = attributes.get(FormsElements.REQUESTER.key());
            final String name = Environment.getInstance().getAccountName(key);
            Assert.assertEquals(this.story.getRequester(), name);
        });
        strategy.put(FormsElements.OWNER.key(), () -> {
            final String key = attributes.get(FormsElements.OWNER.key());
            final String name = Environment.getInstance().getAccountName(key);
            Assert.assertEquals(this.story.getOwner(), name);
        });
        strategy.put(FormsElements.DESCRIPTION.key(), () ->
                Assert.assertEquals(this.story.getDescription(),
                        attributes.get(FormsElements.DESCRIPTION.key())));

        attributes.keySet()
                .forEach(key -> strategy.get(key).perform());
    }

    /**
     * This step create a story with given information.
     *
     * @param attributes story info.
     */
    @When("creates a story with:")
    public void createsAStoryWith(final Map<String, String> attributes) {
        this.story.createStory(attributes);
        ScenarioContext.getInstance().setContext(this.allFields, attributes);
    }

    /**
     * This step verifies the story counter in project list page.
     *
     * @param projectNameKey project name key in context.
     * @param expectedCount  expected story count in project.
     */
    @Then("verifies the story count for project {string} is equal {string} in  project list")
    public void verifiesTheStoryIsCreatedInProjectList(final String projectNameKey, final String expectedCount) {
        final String projectName = StringUtil.getValue(projectNameKey);
        final String amount = this.projectList.getAmountOfStories(projectName);
        Assert.assertEquals(amount, expectedCount);
    }

    /** After hook that delete project created in the background steps. **/
    @After
    public void after() {
        try {
            final String url = StringUtil.getExplicitEndpoint("/projects/{project_response.id}");
            RequestManager.deleteRequest(url);
        } catch (final Exception e) {
            LOGGER.warn("After hook for story steps doesn't run");
        }
    }

    /** This step clicks on add story button. **/
    @When("clicks on add story button")
    public void clicksOnAddStoryButton() {
        this.panel.clickAddButton();
    }
}
