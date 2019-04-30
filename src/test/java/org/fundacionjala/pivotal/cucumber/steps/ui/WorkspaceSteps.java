package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.fundacionjala.core.api.services.WorkSpaceService;
import org.fundacionjala.core.ui.forms.FormsElements;
import org.fundacionjala.pivotal.pages.*;
import org.fundacionjala.util.ScenarioContext;
import org.fundacionjala.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.NoSuchElementException;

/**
 * This class will have steps for workspace feature.
 **/
public class WorkspaceSteps {
    public static final String WS_SETTINGS_PAGE = "ws_settings_page";
    public static final String WS_NAME = "ws_name";
    private static final Logger LOGGER =
            Logger.getLogger(WorkspaceSteps.class.getName());
    @Autowired
    private Dashboard dashboard;

    @Autowired
    private Header header;

    @Autowired
    private WorkSpaceNew workSpaceNew;

    @Autowired
    private WorkSpaceSettings workSpaceSettings;

    @Autowired
    private ConfirmAction confirm;


    /**
     * This step edit workspace.
     *
     * @param strname param.
     **/
    @And("edit workspace’s title")
    public static void setWorkSpacename(final String strname) {

        final WorkSpaceSettings settingsPage =
                (WorkSpaceSettings) ScenarioContext.getInstance().getContext(
                        WS_SETTINGS_PAGE);
        ScenarioContext.getInstance().setContext(WS_NAME, strname);
        settingsPage.setName(strname);
    }

    /**
     * This step save workspace.
     **/
    @And("clicks on Save Button")
    public static void clickSaveButton() {
        final WorkSpaceSettings settingsPage =
                (WorkSpaceSettings) ScenarioContext.getInstance().getContext(
                        WS_SETTINGS_PAGE);
        settingsPage.clickOnSave();

    }

    /**
     * This step create a workspace.
     **/
    @Given("create an workspace")
    public static void createaworkspace() {
        final String name = "My WorkSpace Test";
        final int workspaceId = WorkSpaceService.createWorkspace(name);
        ScenarioContext.getInstance().setContext(WS_NAME, name);
        ScenarioContext.getInstance().setContext("ws_id", workspaceId);
    }

    /**
     * This step delete a workspace.
     **/
    @And("clicks on delete workspace link")
    public void clickDeleteWorkspace() {
        this.confirm.clickOnDeleteWorkspaceProjectLink();
        this.confirm.clickOnConfirmWorkspaceProjectDeleteButton();
    }


    /**
     * This step click over settings button.
     *
     * @param workspaceAttributes Map of attributes
     **/
    @When("the settings page from the particular workspace")
    public void theUserClicksOnWorkSpaceSettingBtn(final Map<String, String> workspaceAttributes) {
        final String name = StringUtil.getValue(workspaceAttributes.get(FormsElements.NAME.toString()));
        try {
            final WorkSpaceSettings page =
                    this.dashboard.clickWorkSpaceSettings(name);
            ScenarioContext.getInstance().setContext(WS_SETTINGS_PAGE, page);


        } catch (final NoSuchElementException e) {
            LOGGER.warn("The Workspace web element was not find ", e);
        }
    }

    /**
     * This step click over create button.
     **/
    @Given("clicks on create workspace button")
    public void clicksTheCreateWorkspaceButton() {
        this.workSpaceNew.clickDashboardLink();
        this.workSpaceNew.clickCreateWorkSpaceButton();
    }

    /**
     * This step set name for a workspace.
     *
     * @param name param.
     **/
    @And("sets the workspace name: {string}")
    public void setsTheWorkspaceNameName(final String name) {
        this.workSpaceNew.setName(name);
        this.workSpaceNew.clickCreateButton();
    }

    /**
     * Creates a workspace by given attributes.
     *
     * @param workspaceAttributes Map ot attributes
     */
    @When("creates a workspace")
    public void createsAWorkspace(final Map<String, String> workspaceAttributes) {
        final String name = StringUtil.getValue(workspaceAttributes.get(FormsElements.NAME.toString()));
        this.workSpaceNew.setName(name);
        ScenarioContext.getInstance().setContext(WS_NAME, name);
        this.workSpaceNew.clickCreateButton();
    }

    /**
     * Click over a tab from header menu.
     *
     * @param tabNameOnHeader String
     */
    @And("clicks on {string} tab on header menu")
    public void clicksOnTabOnHeaderMenu(final String tabNameOnHeader) {
        this.workSpaceSettings.openWorkspaceMenuTab(tabNameOnHeader);
    }

    /**
     * Set the given attributes on workspace form.
     *
     * @param workspaceAttributes Map of attributes
     */
    @When("edits attributes of the workspace")
    public void editsAttributesOfTheWorkspace(final Map<String, String> workspaceAttributes) {
        ScenarioContext.getInstance().setContext("workspace",
                StringUtil.setKeyValue(FormsElements.NAME.toString(), workspaceAttributes
                        .get(FormsElements.NAME.toString())));
        this.workSpaceSettings.setName(workspaceAttributes
                .get(FormsElements.NAME.toString()));
    }

    /**
     * Go to the specify workspace home.
     *
     * @param workspaceAttributes Map of attributes
     */
    @Given("the workspace home")
    public void theWorkspaceHome(final Map<String, String> workspaceAttributes) {
        final String name = StringUtil.getValue(workspaceAttributes
                .get(FormsElements.NAME.toString()));
        this.dashboard.goToWorkspace(name);
    }
}
