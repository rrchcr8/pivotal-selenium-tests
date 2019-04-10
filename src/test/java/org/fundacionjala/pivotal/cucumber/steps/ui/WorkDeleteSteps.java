package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fundacionjala.pivotal.pages.Dashboard;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkDeleteSteps {
    @Autowired
    Dashboard dashboard;

    @Given("an workspace")
    public void createaworkspace() {
        this.dashboard.goToWorkSpaceTab();
        this.dashboard.createWorkSpaceButton();
    }

    @When("the user clicks on workspace settings button$")
    public void theUserClicksOnWorkSpaceSettingBtn() {

    }


    @And("click delete workspace")
    public void clickDeleteWorkspace() {
    }

    @Then("workspace should be deleted")
    public void xx() {
    }


}
