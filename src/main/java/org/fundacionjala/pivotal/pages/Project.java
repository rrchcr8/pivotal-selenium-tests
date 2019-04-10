package org.fundacionjala.pivotal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import org.fundacionjala.core.ui.AbstractPage;

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

    private String ProjectName;
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
        this.ProjectName = strProjectName;
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

    private boolean isAccountListed(String accountName) {
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
        return this.ProjectName;
    }
}
