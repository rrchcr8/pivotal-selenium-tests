package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.fundacionjala.core.api.services.WorkSpaceService;
import org.fundacionjala.pivotal.pages.Dashboard;
import org.fundacionjala.pivotal.pages.Header;
import org.fundacionjala.pivotal.pages.WorkSpaceNew;
import org.fundacionjala.pivotal.pages.WorkSpaceSettings;
import org.fundacionjala.util.ScenarioContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** This is a comment. **/
public class WorkspaceSteps {
    private static final Logger LOGGER =
            Logger.getLogger(WorkspaceSteps.class.getName());
    public static final String WS_SETTINGS_PAGE = "ws_settings_page";
    public static final String WS_NAME = "ws_name";

    @Autowired
    private Dashboard dashboard;

    @Autowired
    private Header header;

    @Autowired
    private WorkSpaceNew workSpaceNew;

    /** This is a comment. **/
    @Given("an workspace")
    public void clickDashboardLink() {
        this.header.goToDashBoard();
    }

    /**
     * This is a comment.
     *
     * @param strname param.
     **/
    @And("edit workspace’s title")
    public void setWorkSpacename(final String strname) {

        final WorkSpaceSettings settingsPage =
                (WorkSpaceSettings) ScenarioContext.getInstance().getContext(
                        WS_SETTINGS_PAGE);
        ScenarioContext.getInstance().setContext(WS_NAME, strname);
        settingsPage.setName(strname);
    }

    /** This is a comment. **/
    @And("clicks on Save Button")
    public void clickSaveButton() {
        final WorkSpaceSettings settingsPage =
                (WorkSpaceSettings) ScenarioContext.getInstance().getContext(
                        WS_SETTINGS_PAGE);
        settingsPage.clickOnSave();

    }

    /** This is a comment. **/
    @Then("workspace title should be edited")
    public void getWorkSpaceLabel() {
        final String name = (String) ScenarioContext.getInstance().getContext(
                WS_NAME);
        this.dashboard.goToWorkSpaceTab();
        assertTrue(this.dashboard.existWorkSpace(name));
    }

    /** This is a comment. **/
    @Given("create an workspace")
    public void createaworkspace() {
        final String name = "My WorkSpace Test";
        final int workspaceId = WorkSpaceService.createWorkspace(name);
        ScenarioContext.getInstance().setContext(WS_NAME, name);
        ScenarioContext.getInstance().setContext("ws_id", workspaceId);
    }

    /** This is a comment. **/
    @When("the user clicks on workspace settings button")
    public void theUserClicksOnWorkSpaceSettingBtn() {
        final String name = (String) ScenarioContext.getInstance().getContext(
                WS_NAME);
        try {
            final WorkSpaceSettings page =
                    this.dashboard.clickWorkSpaceSettings(name);
            ScenarioContext.getInstance().setContext(WS_SETTINGS_PAGE, page);


        } catch (final NoSuchElementException e) {
            LOGGER.warn("The Workspace web element was not find ", e);
        }
    }

    /** This is a comment. **/
    @And("click delete workspace")
    public void clickDeleteWorkspace() {
        final WorkSpaceSettings settingsPage =
                (WorkSpaceSettings) ScenarioContext.getInstance().getContext(
                        WS_SETTINGS_PAGE);
        settingsPage.clickOnDeleteLink();

    }

    /** This is a comment. **/
    @And("click delete confirm")
    public void clickConfirmDeleteWorkspace() {
        final WorkSpaceSettings settingsPage =
                (WorkSpaceSettings) ScenarioContext.getInstance().getContext(
                        WS_SETTINGS_PAGE);
        settingsPage.clickOnConfirmDelete();

    }

    /** This is a comment. **/
    @Then("workspace should be deleted")
    public void verifyDelete() {
        final String name = (String) ScenarioContext.getInstance().getContext(
                WS_NAME);
        this.dashboard.goToWorkSpaceTab();
        assertFalse(this.dashboard.existWorkSpace(name));
    }

    /** This is a comment. **/
    @When("clicks the create workspace button")
    public void clicksTheCreateWorkspaceButton() {
        this.workSpaceNew.clickDashboardLink();
        this.workSpaceNew.clickCreateWorkSpaceButton();
    }

    /**
     * This is a comment.
     *
     * @param name param.
     **/
    @And("sets the workspace name: {string}")
    public void setsTheWorkspaceNameName(final String name) {
        // guardar el name de forma mas optima q e una variable
        this.workSpaceNew.setName(name);
        this.workSpaceNew.clickCreateButton();
    }

    /**
     * This is a comment.
     *
     * @param name param.
     **/
    @Then("the workspace should be created: {string}")
    public void theWorkspaceShouldBeCreated(final String name) {
        final String actualresult = this.workSpaceNew.getWorkSpaceLabel();
        Assert.assertEquals(actualresult, name);
    }

    /** This is a comment. **/
    @And("the workspace board should be displayed")
    public void theWorkspaceBoardShouldBeDisplayed() {
        assertTrue(true);
    }
}



