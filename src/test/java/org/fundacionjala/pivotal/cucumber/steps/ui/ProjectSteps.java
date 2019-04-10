package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fundacionjala.pivotal.pages.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.util.Map;

/**
 * Project steps.
 */
public class ProjectSteps {

    @Autowired
    private Project project;

    /**
     * Create a new project.
     *
     * @param projectAttributes for create project.
     */
    @When("user creates a default project as")
    public void userCreatesNewProjectAs(final Map<String, String> projectAttributes) {
        project.createNewProject(projectAttributes);
    }

    /**
     * was project created with specific name.
     */
    @Then("validate the project is created with specify name")
    public void validateTheProjectIsCreatedWithSpecifyName() {
        String actual = project.getProjectNameOnDashboard();
        Assert.assertEquals(actual, project.getProjectName(), "Project name match");
    }

    /**
     * A project to delete.
     * @param projectName the project name
     */
    @Given("an existing project named as {string} that user intends to delete")
    public void anExistingProjectNamedAsThatUserIntendsToDelete(final String projectName) {
        project.setProjectName(projectName);
    }

    /**
     * Open context on settings.
     */
    @And("open project's settings")
    public void openProjectSSettings() {
        String projectName = project.getProjectName();
        project.openProjectSettingsbyName(projectName);
    }

    /**
     * Delete project by link.
     */
    @When("user click over delete project link")
    public void userClickOverDeleteProjectLink() {
        project.clickOnDeleteProjectLink();

    }

    /**
     * confirm deletion.
     */
    @And("Agree to delete")
    public void agreeToDelete() {
        project.clickOnDeleteButton();
    }

    /**
     * Check if project is still displayed.
     */
    @Then("The project no longer appear on projects section")
    public void theProjectNoLongerAppearOnProjectsSection() {
        Assert.assertFalse(project.isProjectListed(), "False if project is not listed after deletion");
    }

    /**
     * Save on context value of project name.
     * @param projectName the project name
     */
    @Given("an existing project known as {string} that user intends to edit")
    public void anExistingProjectKnownAsThatUserIntendsToEdit(final String projectName) {
        project.setProjectName(projectName);
        project.openProjectSettingsbyName(projectName);
    }

    /**
     * Intend to change values by given attributes.
     * @param projectAttributes table of attributes
     */
    @And("change values on form as")
    public void changeValuesOnFormAs(final Map<String, String> projectAttributes) {
        project.setValuesOnEditProjectForm(projectAttributes);
    }

    /**
     * Save button is pressed.
     */
    @When("user press save button")
    public void userPressSaveButton() {
        project.saveFormOnEditProject();
    }

    /**
     * Verifies is success message type was displayed on screen.
     */
    @Then("A successful message is displayed")
    public void aSuccessfulMessageIsDisplayed() {
        boolean actual = project.getResponseMessage();
        Assert.assertTrue(actual, "Passed if edit project was processed");
    }
}
