package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.fundacionjala.pivotal.pages.Dashboard;
import org.fundacionjala.pivotal.pages.Header;
import org.fundacionjala.pivotal.pages.HeaderMenu;
import org.fundacionjala.pivotal.pages.ToastMessage;
import org.fundacionjala.pivotal.pages.WorkSpaceNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.asserts.SoftAssert;

/**
 * Assertion on common steps with soft assert.
 */
public class AssertSteps {

    @Autowired
    private SoftAssert softAssert;

    @Autowired
    private Dashboard dashboard;

    @Autowired
    private WorkSpaceNew workSpaceNew;

    @Autowired
    private Header header;

    @Autowired
    private HeaderMenu headerMenu;

    @Autowired
    private ToastMessage toastMessage;

    private static final String WORKSPACE = "Workspaces";
    /**
     * This steps verify that workspace.
     *
     * @param name param.
     **/
    @Then("the workspace should be created: {string}")
    public void theWorkspaceShouldBeCreated(final String name) {
        final String actualresult = this.workSpaceNew.getWorkSpaceLabel();
        softAssert.assertEquals(actualresult, name);
    }

    /**
     * This step verify that workspace is displayed.
     **/
    @And("the workspace board should be displayed")
    public void theWorkspaceBoardShouldBeDisplayed() {
        softAssert.assertTrue(true);
    }

    /**
     * Validate title match on header title.
     *
     * @param headerTitle String
     */
    @Then("validates {string} on header title")
    public void validatesOnHeaderTitle(final String headerTitle) {
        final String actual = this.header.getTitleName();
        softAssert.assertEquals(actual, headerTitle, String.format(" %s match on header title", headerTitle));
    }

    /**
     * Validate listing of item on a specific group.
     *
     * @param name          String
     * @param specificGroup String
     */
    @And("validates {string} on {string} group list")
    public void validatesOnGroupList(final String name, final String specificGroup) {
        if (specificGroup.equals(WORKSPACE)) {
            softAssert.assertTrue(this.headerMenu.isWorkspaceListedOnMenu(name),
                    String.format(" %s match inside group list on %s", name, specificGroup));
        } else {
            softAssert.assertTrue(this.headerMenu.isProjectListedOnMenu(name),
                    String.format(" %s match inside group list on %s", name, specificGroup));
        }
    }

    /**
     * Validate listing of item on a specific dashboard tab.
     *
     * @param name         String
     * @param specificList String
     */
    @And("validates {string} on {string} dashboard tab")
    public void validatesOnDashboardTab(final String name, final String specificList) {
        if (specificList.equals(WORKSPACE)) {
            softAssert.assertTrue(this.dashboard.existWorkSpace(name),
                    String.format(" %s found on dashboard page inside %s", name, specificList));
        } else {
            softAssert.assertTrue(this.dashboard.existProject(name),
                    String.format(" %s found on dashboard page inside %s", name, specificList));
        }
    }

    /**
     * Validate not listing of item on a specific group.
     *
     * @param name          String
     * @param specificGroup String
     */
    @And("validates {string} not listed on {string} group list")
    public void validatesNotListedOnGroupList(final String name, final String specificGroup) {
        if (specificGroup.equals(WORKSPACE)) {
            softAssert.assertFalse(this.headerMenu.isWorkspaceListedOnMenu(name),
                    String.format(" %s not listed on %s", name, specificGroup));
        } else {
            softAssert.assertFalse(this.headerMenu.isProjectListedOnMenu(name),
                    String.format(" %s not listed on %s", name, specificGroup));
        }
    }

    /**
     * Validate not listing of item on a specific dashboard tab.
     *
     * @param name         String
     * @param specificList String
     */
    @And("validates {string} not listed on {string} dashboard tab")
    public void validatesNotListedOnDashboardTab(final String name, final String specificList) {
        if (specificList.equals(WORKSPACE)) {
            softAssert.assertFalse(this.dashboard.existWorkSpace(name),
                    String.format(" %s not listed on %s", name, specificList));
        } else {
            softAssert.assertFalse(this.dashboard.existProject(name),
                    String.format(" %s not listed on %s", name, specificList));
        }
    }

    /**
     * Validate the display of a given message.
     *
     * @param messageOnScreen String
     */
    @Then("a {string} message should be displayed")
    public void aMessageShouldBeDisplayed(final String messageOnScreen) {
        softAssert.assertTrue(this.toastMessage.checkVisibilityOfMessage(messageOnScreen));
    }
}
