package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class WorkEdit extends AbstractPage {

    @FindBy(css = "Dashboard__Tabs__tab Dashboard__Tabs__tab--active")
    private WebElement dashboardLink;


    @FindBy(css = "SettingsIcon__cog projectTileHeader__hoverable")
    private WebElement settingsLink;


    @FindBy(id = "workspace_name")
    private WebElement WorkSpacename;

    @FindBy(css = "save_bar__submit")
    private WebElement saveButton;

    @FindBy(css = "tc_header_project_name")
    private WebElement workSpaceLabel;

    /**
     * Clicks the next button.
     */
    public void clickDashboardLink() {
        action.click(dashboardLink);
    }


    public void clickSettingsLink() {
        action.click(settingsLink);
    }

    public void setWorkSpacename(final String strname) {
        action.setValue(setWorkSpacename, strname);
    }

    public void clickSaveButton() {
        action.click(saveButton);
    }

    public String getWorkSpaceLabel() {

        return workSpaceLabel.getText();

    }



}
