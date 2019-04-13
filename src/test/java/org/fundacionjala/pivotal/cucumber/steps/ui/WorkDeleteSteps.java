package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.fundacionjala.core.api.services.WorkSpaceService;
import org.fundacionjala.pivotal.pages.Dashboard;
import org.fundacionjala.pivotal.pages.WorkSpaceSettings;
import org.fundacionjala.util.ScenarioContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertFalse;

/**
 *
 */
public class WorkDeleteSteps {
    private static final Logger LOGGER =
            Logger.getLogger(WorkDeleteSteps.class.getName());
    @Autowired
    Dashboard dashboard;

    /**
     *
     */
    @Given("create an workspace")
    public void createaworkspace() {
        final String name = "My WorkSpace Test";
        final int workspaceId = WorkSpaceService.createWorkspace(name);
        ScenarioContext.getInstance().setContext("ws_name", name);
        ScenarioContext.getInstance().setContext("ws_id", workspaceId);
    }

    @When("the user clicks on workspace settings button")
    public void theUserClicksOnWorkSpaceSettingBtn() {
        final String name = (String) ScenarioContext.getInstance().getContext(
                "ws_name");
        try {
            final WorkSpaceSettings page =
                    this.dashboard.clickWorkSpaceSettings(name);
            ScenarioContext.getInstance().setContext("ws_settings_page", page);


        } catch (final NoSuchElementException e) {
            LOGGER.warn("The Workspace web element was not find ", e);
        }
    }


    @And("click delete workspace")
    public void clickDeleteWorkspace() {
        final WorkSpaceSettings settingsPage =
                (WorkSpaceSettings) ScenarioContext.getInstance().getContext(
                        "ws_settings_page");
        settingsPage.clickOnDeleteLink();

    }

    @And("click delete confirm")
    public void clickConfirmDeleteWorkspace() {
        final WorkSpaceSettings settingsPage =
                (WorkSpaceSettings) ScenarioContext.getInstance().getContext(
                        "ws_settings_page");
        settingsPage.clickOnConfirmDelete();

    }

    @Then("workspace should be deleted")
    public void verifyDelete() {
        final String name = (String) ScenarioContext.getInstance().getContext(
                "ws_name");
        this.dashboard.goToWorkSpaceTab();
        assertFalse(this.dashboard.existWorkSpace(name));
    }
}
