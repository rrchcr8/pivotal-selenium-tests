package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.fundacionjala.pivotal.pages.Dashboard;
import org.fundacionjala.pivotal.pages.Header;
import org.fundacionjala.pivotal.pages.WorkSpaceSettings;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;


public class WorkEditSteps {
    @Autowired
    Dashboard dashboard;
    @Autowired
    Header header;

    @Given("an workspace")
    // obtener la pagina de workspace
    public void clickDashboardLink() {
        this.header.goToDashBoard();
    }

    @And("edit workspace’s title")
    public void setWorkSpacename(final String strname) {

        final WorkSpaceSettings settingsPage =
                (WorkSpaceSettings) ScenarioContext.getInstance().getContext(
                        "ws_settings_page");
        ScenarioContext.getInstance().setContext("ws_name", strname);
        settingsPage.setName(strname);
    }

    @And("clicks on Save Button")
    public void clickSaveButton() {
        final WorkSpaceSettings settingsPage =
                (WorkSpaceSettings) ScenarioContext.getInstance().getContext(
                        "ws_settings_page");
        settingsPage.clickOnSave();

    }


    @Then("workspace title should be edited")
    public String getWorkSpaceLabel() {
        final String name = (String) ScenarioContext.getInstance().getContext(
                "ws_name");
        this.dashboard.goToWorkSpaceTab();
        assertTrue(this.dashboard.existWorkSpace(name));
    }


}



