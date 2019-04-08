package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fundacionjala.pivotal.pages.WorkSpace;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkSpaceSteps {

    @Autowired
    private WorkSpace workSpace;

    @When("clicks the create workspace button")
    public void clicksTheCreateWorkspaceButton() {
        workSpace.clickDashboardLink();
        workSpace.clickCreateWorkSpaceButton();
    }

    @And("sets the workspace name: {string}")
    public void setsTheWorkspaceNameName(final String name) {
        workSpace.setName(name);
        workSpace.clickCreateButton();
    }

    @Then("the workspace should be created: {string}")
    public void theWorkspaceShouldBeCreated(final String name) {
        workSpace.checkValueOnLabelWorkSpaceName(name);
    }

    @And("the workspace board should be displayed")
    public void theWorkspaceBoardShouldBeDisplayed() {
    }
}
