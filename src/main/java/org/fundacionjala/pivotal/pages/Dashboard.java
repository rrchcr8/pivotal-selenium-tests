package org.fundacionjala.pivotal.pages;

import org.apache.log4j.Logger;
import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/** Dashboard page. **/
@Component
public class Dashboard extends AbstractPage {
    private static final Logger LOGGER =
            Logger.getLogger(Dashboard.class.getName());

    @FindBy(css = ".Dashboard__Tabs__tab.Dashboard__Tabs__tab--active")
    private WebElement projects;

    @FindBys({
            @FindBy(css = ".Dashboard__Tabs__tab"),
            @FindBy(linkText = "Workspaces")
    })
    private WebElement workspaces;

    @FindBy(css = "#create-project-button")
    private WebElement createProject;


    @FindBy(css = "#create-workspace-button")
    private WebElement createWorkSpace;

    @FindBy(css = "#projects-search-bar")
    private WebElement searchProject;

    @FindBy(css = ".WorkspaceTile__name")
    private List<WebElement> worksSpaceNames;

    @FindBy(css = ".projectPaneSection__header__heading--count")
    private WebElement amountOfProjects;

    @FindBy(css = ".projectTileHeader__projectName")
    private List<WebElement> projectNames;

    /** Create project. */
    public void createProjectButton() {
        this.action.click(this.createProject);
    }

    /** Create workspace. **/
    public void createWorkSpaceButton() {
        this.action.click(this.createWorkSpace);
    }

    /** Go to work space tab. **/
    public void goToWorkSpaceTab() {
        this.action.click(this.workspaces);
        this.wait(WAIT_TIME, SECONDS);
    }

    /**
     * Check if exist a workspace by name.
     *
     * @param name string to search.
     * @return boolean.
     **/
    public boolean existWorkSpace(final String name) {
        return hasElementWithName(this.worksSpaceNames, name);
    }

    /**
     * Check if exist a project by name.
     *
     * @param name string to search.
     * @return boolean
     **/
    public boolean existProject(final String name) {
        return hasElementWithName(this.projectNames, name);
    }

    /**
     * Check if exist and element in the list with name.
     *
     * @param list list where search.
     * @param name name to search
     * @return boolean.
     **/
    private boolean hasElementWithName(final List<WebElement> list,
                                       final String name) {
        for (final WebElement element : list) {
            if (element.getText().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if a name exist in web elements list.
     *
     * @param list list of webelements.
     * @param name string to search.
     * @return boolean.
     * @throws NoSuchElementException exception if item not found.
     */
    private WebElement getElementWithName(final List<WebElement> list,
                                          final String name)
            throws NoSuchElementException {
        for (final WebElement element : list) {
            if (element.getText().equals(name)) {
                return element;
            }
        }
        throw new NoSuchElementException("Web element not found");
    }

    /**
     * Go to project with name.
     *
     * @param name string project name.
     */
    public void goToProject(final String name) {
        try {
            final WebElement project =
                    getElementWithName(this.projectNames, name);
            this.action.click(project);
        } catch (final NoSuchElementException e) {
            LOGGER.warn("The Project web element was not find ", e);
        }
    }

    /**
     * Go to workspace with name.
     *
     * @param name string workspace.
     */
    public void goToWorkspace(final String name) {
        try {
            final WebElement project =
                    getElementWithName(this.worksSpaceNames, name);
            this.action.click(project);
        } catch (final NoSuchElementException e) {
            LOGGER.warn("The Workspace web element was not find ", e);
        }
    }

    /**
     * Get amount of projects.
     *
     * @return int amount.
     */
    public int getAmountOfProjects() {
        return Integer.valueOf(this.amountOfProjects.getText());
    }

    /**
     * This method click on settings workspace with name provided.
     *
     * @param name string workspace name.
     * @return WorkSpaceSettings.
     * @throws NoSuchElementException woprkspace with name not found.
     */
    public WorkSpaceSettings clickWorkSpaceSettings(final String name)
            throws NoSuchElementException {
        for (final WebElement element : this.worksSpaceNames) {
            if (element.getText().equals(name)) {
                getParent(getParent(element))
                        .findElement(By.xpath("//div//span//a[contains(@class,"
                                .concat("'SettingsIcon__cog projectTileHeader")
                                .concat("__hoverable')]")))
                        .click();
                return new WorkSpaceSettings();
            }
        }
        throw new NoSuchElementException("The work space with name " + name + " was not find");
    }
}
