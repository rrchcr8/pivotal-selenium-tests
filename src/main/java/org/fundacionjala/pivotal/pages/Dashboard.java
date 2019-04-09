package org.fundacionjala.pivotal.pages;

import org.apache.log4j.Logger;
import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Wait;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.List;

public class Dashboard extends AbstractPage {
    private static final Logger LOGGER = Logger.getLogger(Dashboard.class.getName());

    @FindBy(css = ".Dashboard__Tabs__tab.Dashboard__Tabs__tab--active")
    WebElement projects;

    @FindBys({
            @FindBy(className="Dashboard__Tabs__tab"),
            @FindBy(linkText = "Workspaces")
    })
    WebElement workspaces;

    @FindBy(id="create-project-button")
    WebElement createProject;

    @FindBy(id="create-workspace-button")
    WebElement createWorkSpace;

    @FindBy(id="projects-search-bar")
    WebElement searchProject;

    @FindBy(className = "WorkspaceTile__name")
    List<WebElement> worksSpaceNames;

    @FindBy(className = "projectPaneSection__header__heading--count")
    WebElement amountOfProjects;

    @FindBy(className = "projectTileHeader__projectName")
    List<WebElement> projectNames;

    public void createProjectButton() {
        action.click(createProject);
        //return new CreateProjectDialog();
    }

    public void goToWorkSpaceTab() {
        action.click(workspaces);
        wait.withTimeout(WAIT_TIME, SECONDS);
    }

    public boolean existWorkSpace(String name) {
        return hasElementWithName(worksSpaceNames, name);
    }

    public boolean existProject(String name) {
        return hasElementWithName(projectNames, name);
    }

    private boolean hasElementWithName(List<WebElement> list, String name) {
        for (WebElement element : list) {
            if(element.getText().equals(name)){
                return true;
            }
        }
        return false;
    }

    private WebElement getElementWithName(List<WebElement> list, String name) throws Exception {
        for (WebElement element : list) {
            if(element.getText().equals(name)){
                return element;
            }
        }
        throw new Exception("Web element not found");
    }

    public void goToProject(String name) {
        try {
            WebElement project = getElementWithName(projectNames, name);
            action.click(project);
            // return new ProjectPage();
        } catch (Exception e) {
            LOGGER.warn("The Project web element was not find ", e);
        }
    }

    public void goToWorkspace(String name) {
        try {
            WebElement project = getElementWithName(worksSpaceNames, name);
            action.click(project);
            // return new ProjectPage();
        } catch (Exception e) {
            LOGGER.warn("The Workspace web element was not find ", e);
        }
    }

    public int getAmountOfProjects() {
        return Integer.valueOf(amountOfProjects.getText());
    }
}
