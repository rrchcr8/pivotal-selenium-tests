package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * this class represented a login page.
 */
@Component
public class Project extends AbstractPage {

    @FindBy(id = "create-project-button")
    private WebElement createNewProjectButton;

    @FindBy(css = ".tc-form__input")
    private WebElement projectNameField;

    @FindBy(css = ".tc-account-selector")
    private WebElement accountSelector;

    @FindBy(css = ".zWDds__Button.pvXpn__Button--positive")
    private WebElement createButton;

    @FindBy(css = ".tc-account-selector__create-account-icon")
    private WebElement newAccountOption;

    @FindBy(css = ".tc-account-creator__name")
    private WebElement newAccountField;

    @FindBy(css = "raw_context_name")
    private WebElement titleOnDashboard;

    @FindBy(id = "delete_link")
    private WebElement deleteLink;

    @FindBy(id = "confirm_delete")
    private WebElement confirmDelete;

    @FindBy(id = "project_name")
    private WebElement editProjectNameField;

    @FindBy(id = "project_description")
    private WebElement editProjectDescriptionField;

    @FindBy(xpath = "//input[@id='project_enable_tasks' and @type='checkbox']")
    private WebElement editProjectEnableTask;

    @FindBy(xpath = "//input[@id='project_public' and @type='checkbox']")
    private WebElement editProjectPrivacy;

    @FindBy(xpath = "//input[@name='commit' and @type='submit']")
    private WebElement saveButtonOnEditProject;

    @FindBy(id = "save_success_bar")
    private WebElement successBar;

    private String projectName;
    /**
     * Clicks the create new project button.
     */
    public void clickCreateNewProjectButton() {
        action.click(createNewProjectButton);
    }

    /**
     * This method add one value of projectname text field.
     *
     * @param strProjectName value of input.
     */
    public void setProjectNameTextField(final String strProjectName) {
        this.projectName = strProjectName;
        action.setValue(projectNameField, strProjectName);
    }

    /**
     * Open account list.
     **/
    public void openSelectAccountCombobox() {
        action.click(accountSelector);
    }

    /**
     * Select an option from list.
     * @param accountName to select an account from list
     */
    public void selectAccount(final String accountName) {

        boolean onListAccount = isAccountListed(accountName);

        if (!onListAccount) {
            action.click(newAccountOption);
            createAccount(accountName);
        } else {
            WebElement accountSelectorByName = driver.findElement(By.xpath("//div[text()='" + accountName + "']"));
            action.click(accountSelectorByName);
        }
    }

    /**
     * Check if account is listed.
     * @param accountName name of account
     * @return Boolean true if it is displayed
     */
    private boolean isAccountListed(final String accountName) {
        boolean exist;
        try {
            exist = driver.findElement(By.xpath("//div[text()='" + accountName + "']")).isDisplayed();
        } catch (NoSuchElementException e) {
            exist = false;
        }
        return exist;
    }

    /**
     * Create an account by given name.
     * @param accountName name of the new account
     */
    private void createAccount(final String accountName) {

        action.setValue(newAccountField, accountName);
    }

    /**
     * Clicks the create button.
     */
    public void clickCreateButton() {
        action.click(createButton);
    }

    /**
     * Create new project by given name.
     *
     * @param projectElements value
     */
    public void createNewProject(final Map<String, String> projectElements) {
        clickCreateNewProjectButton();
        String projectName = projectElements.get("name");
        setProjectNameTextField(projectName);
        openSelectAccountCombobox();
        selectAccount(projectElements.get("account"));
        selectProjectPrivacy(projectElements.get("privacy"));
        clickCreateButton();
    }

    /**
     * Method to select privacy based on election.
     * @param privacy option public or private
     */
    public void selectProjectPrivacy(final String privacy) {
        WebElement accountPrivacyOption = driver.findElement(
                By.xpath("//input[@type='radio' and @data-aid='" + privacy + "']"));
        action.click(accountPrivacyOption);
    }

    /**
     * Capture project's name on dashboard.
     * @return title text displayed on dashboard
     */
    public String getProjectNameOnDashboard() {
        return  titleOnDashboard.getText();
    }

    /**
     * Method to return project's name from context.
     * @return context value on project name
     */
    public String getProjectName() {
        return this.projectName;
    }

    /**
     * Method to keep project's name to context.
     * @param strProjectName project name
     */
    public void setProjectName(final String strProjectName) {
        this.projectName = strProjectName;
    }

    /**
     * Given a project name open settings section.
     * @param projectName Name of project
     */
    public void openProjectSettingsbyName(final String projectName) {
        WebElement projectItem = driver.findElement(
                By.xpath("//a[@data-aid='project-name' and contains(text(),'" + projectName + "')]"));
        String linkText = projectItem.getAttribute("css=a@href");
        WebElement projectItemSettingElement = driver.findElement(
                By.xpath("//a[@aria-label='settings' and @href='" + linkText + "')]")
        );
        action.click(projectItemSettingElement);
    }

    /**
     * Delete through link option.
     */
    public void clickOnDeleteProjectLink() {
        action.click(deleteLink);
    }

    /**
     * Cofirm deletion.
     */
    public void clickOnDeleteButton() {
        action.click(confirmDelete);
    }

    /**
     * To check if project is listed.
      * @return Boolean tru if it is seeing on page
     */
    public boolean isProjectListed() {
        boolean listed;
        try {
            listed = driver.findElement(
                    By.xpath("//a[@data-aid='project-name' and contains(text(),'" + this.getProjectName() + "')]"))
                    .isDisplayed();
        } catch (NoSuchElementException e) {
            listed = false;
        }
        return listed;
    }

    /**
     * Set values on form as specified.
     * @param projectAttributes Attributes to set on form
     */
    public void setValuesOnEditProjectForm(final Map<String, String> projectAttributes) {

        setEditProjectTitle(projectAttributes.get("title"));
        setEditProjectDescription(projectAttributes.get("description"));
        setEditProjectAccount(projectAttributes.get("account"));
        setEditProjectTaskEnable(projectAttributes.get("taskEnable"));
        setEditProjectPrivacy(projectAttributes.get("privacy"));
    }

    /**
     * change check status if different.
     * @param privacy between private and public
     */
    private void setEditProjectPrivacy(final String privacy) {
        if (editProjectPrivacy.isSelected() && "private".equals(privacy)) {
            action.click(editProjectPrivacy);
        }
    }

    /**
     * change check status if different.
     * @param taskEnable between allow or disallow
     */
    private void setEditProjectTaskEnable(final String taskEnable) {
        if (editProjectPrivacy.isSelected() && "Allow".equals(taskEnable)) {
            action.click(editProjectEnableTask);
        }
    }

    /**
     * Proccess to change account.
     * @param account account name
     */
    private void setEditProjectAccount(final String account) {
    }

    /**
     * Set value to description field.
     * @param description the desired value
     */
    private void setEditProjectDescription(final String description) {
        action.setValue(editProjectDescriptionField, description);

    }

    /**
     * Set value to title field.
     * @param title desired title
     */
    private void setEditProjectTitle(final String title) {
        action.setValue(editProjectNameField, title);
    }

    /**
     * Click to save button on edit project form.
     */
    public void saveFormOnEditProject() {
        action.click(saveButtonOnEditProject);
    }

    /**
     * validate message of success.
     * @return Boolean if message was displayed
     */
    public boolean getResponseMessage() {
        return successBar.isDisplayed();

    }
}
