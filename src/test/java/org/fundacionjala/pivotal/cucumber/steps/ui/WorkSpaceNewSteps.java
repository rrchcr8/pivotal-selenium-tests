package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fundacionjala.pivotal.pages.WorkSpaceNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

public class WorkSpaceNewSteps {

    @Autowired
    private WorkSpaceNew workSpaceNew;

    @When("clicks the create workspace button")
    public void clicksTheCreateWorkspaceButton() {
        workSpaceNew.clickDashboardLink();
        workSpaceNew.clickCreateWorkSpaceButton();
    }

    @And("sets the workspace name: {string}")
    public void setsTheWorkspaceNameName(final String name) {
        // guardar el name de forma mas optima q e una variable
        workSpaceNew.setName(name);
        workSpaceNew.clickCreateButton();
    }

    @Then("the workspace should be created: {string}")
    public void theWorkspaceShouldBeCreated(final String name) {
        String actualresult = workSpaceNew.getWorkSpaceLabel();
        Assert.assertEquals(actualresult,name);
    }

    @And("the workspace board should be displayed")
    public void theWorkspaceBoardShouldBeDisplayed() {
    }
}
