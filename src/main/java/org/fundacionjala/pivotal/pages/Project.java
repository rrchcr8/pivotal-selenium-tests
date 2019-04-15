package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * this class represented a login page.
 */
@Component
public class Project extends AbstractPage {

    @FindBy(css = "#create-project-button")
    private WebElement createNewProjectButton;

    @FindBy(css = ".tc-form__input")
    private WebElement projectNameField;

    @FindBy(css = ".tc-account-selector")
    private WebElement accountSelector;

    @FindBy(css = ".tc-account-selector__option-list li:nth-of-type(1)")
    private WebElement accountSelectorOptionone;

    @FindBy(css = ".zWDds__Button.pvXpn__Button--positive")
    private WebElement createButton;

    /**
     * Clicks the create new project button.
     */
    public void clickCreateNewProjectButton() {
        this.action.click(this.createNewProjectButton);
    }

    /**
     * This method add one value of projectname text field.
     *
     * @param strProjectName value of input.
     */
    public void setProjectNameTextField(final String strProjectName) {
        this.action.setValue(this.projectNameField, strProjectName);
    }

    /**
     * Open account list.
     *
     * @param strAccount value of account.
     */
    public void openSelectAccountCombobox(final String strAccount) {
        this.action.click(this.accountSelector);
    }

    /**
     * Select an option from list.
     */
    public void selectAccount() {
        this.action.click(this.accountSelectorOptionone);
    }

    /**
     * Clicks the create button.
     */
    public void clickCreateButton() {
        this.action.click(this.createButton);
    }

    /**
     * Create new project by given name.
     *
     * @param projectElements value
     */
    public void createNewProject(final Map<String, String> projectElements) {
        clickCreateNewProjectButton();
        setProjectNameTextField(projectElements.get("name"));
        openSelectAccountCombobox(projectElements.get("account"));
        selectAccount();
        clickCreateButton();
    }
}
