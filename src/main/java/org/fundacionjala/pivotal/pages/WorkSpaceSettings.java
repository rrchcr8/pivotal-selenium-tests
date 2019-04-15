package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

/** This class represent workspace settings page. **/
@Component
public class WorkSpaceSettings extends AbstractPage {

    @FindBy(css = "#delete_link")
    private WebElement deleteLink;

    @FindBy(css = "#confirm_delete")
    private WebElement confirmDelete;

    @FindBy(css = "#workspace_name")
    private WebElement name;

    @FindBy(css = ".save_bar__submit")
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
