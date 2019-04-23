package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

/**
 * This class represent the menu opened on header when you click over.
 * project/workspace title.
 **/
@Component
public class HeaderMenu extends AbstractPage {
    @FindBy(css = "a[href='/projects']")
    private WebElement showAllProjectsWorkSpaces;

    /** This method click on showAllProject/showAllWorkspaces. **/
    public void showAllProjectsWorkSpaces() {
        this.action.click(this.showAllProjectsWorkSpaces);
    }


    /**
     * To check if project is listed on header menu.
     *
     * @param name Project name.
     * @return Boolean true if it is seeing on contextual menu
     */
    public boolean isProjectListedOnMenu(final String name) {
        final String xpath =
                "//span[@class='raw_project_name' and contains(text(),'%s')]";
        return this.action.isExistingSelector(
                By.xpath(String.format(xpath, name)));
    }
}
