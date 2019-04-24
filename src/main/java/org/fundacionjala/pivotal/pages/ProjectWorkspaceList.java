package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

/** This class represent workspace and project list page. **/
@Component
public class ProjectWorkspaceList extends AbstractPage {

    /**
     * To check if project is listed on header menu.
     *
     * @param name project name.
     * @return Boolean true if it is seeing on contextual menu
     */
    public boolean isProjectListedOnPage(final String name) {
        final String xpath =
                "//a[@class='project_name' and contains(text(),'%s')]";
        return this.action.isExistingSelector(
                By.xpath(String.format(xpath, name)));
    }
}
