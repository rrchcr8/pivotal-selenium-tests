package org.fundacionjala.pivotal.pages;

import org.apache.log4j.Logger;
import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

/** This class represent workspace settings page. **/
@Component
public class WorkSpaceSettings extends AbstractPage {
    private static final Logger LOGGER =
            Logger.getLogger(WorkSpaceSettings.class.getName());

    @FindBy(id = "delete_link")
    private WebElement deleteLink;

    @FindBy(id = "confirm_delete")
    private WebElement confirmDelete;

    @FindBy(id = "workspace_name")
    private WebElement name;

    @FindBy(className = "save_bar__submit")
    private WebElement saveButton;

    /** This represent click action on delete link. */
    public void clickOnDeleteLink() {
        this.action.click(this.deleteLink);
    }

    /** This represent click action on confirm delete. */
    public void clickOnConfirmDelete() {
        this.action.click(this.confirmDelete);
    }

    /** This represent click action on save. */
    public void clickOnSave() {
        this.action.click(this.saveButton);
    }

    /**
     * This method set name on the edit field.
     *
     * @param newName string.
     */
    public void setName(final String newName) {
        this.name.sendKeys(newName);
    }
}
