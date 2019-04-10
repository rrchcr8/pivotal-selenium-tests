package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class WorkEditSteps {
    public WorkEditSteps() {
        @Given("an workspace")
        // obtener la pagina de workspace
        public void clickDashboardLink() {
            action.click(dashboardLink);
        }

       @When("clicks on workspace settings button")

        public void clickSettingsLink() {
            action.click(settingsLink);
        }


        @And("edit workspace’s title")
        public void setWorkSpacename(final String strname) {
            action.setValue(setWorkSpacename, strname);
        }

         @And ("clicks on Save Button")
        public void clickSaveButton() {
            action.click(saveButton);
        }



        @Then("workspace title should be edited")

        public String getWorkSpaceLabel() {

            return workSpaceLabel.getText();

        }





        @And("^click delete workspace\\.$", () -> {
        });
        @Then("^workspace should be deleted\\.$", () -> {
        });
        @When("^the user clicks on workspace name$", () -> {
        });
        @And("^clicks on Add Projects$", () -> {
        });
        @And("^to selected a project from list$", () -> {
        });
        @And("^click Save Workspace Changes$", () -> {
        });
        @Then("^Project is displayed on WorkSpace board$", () -> {
        });
    }
}



