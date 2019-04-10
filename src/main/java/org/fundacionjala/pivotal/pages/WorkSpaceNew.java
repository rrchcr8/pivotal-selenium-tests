package org.fundacionjala.pivotal.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;
import org.fundacionjala.core.ui.AbstractPage;

@Component
public class WorkSpaceNew extends AbstractPage {

    @FindBy(css = "Dashboard__Tabs__tab Dashboard__Tabs__tab--active")
    private WebElement dashboardLink;

    @FindBy(id = "create-workspace-button")
    private WebElement createWorkSpaceButton;

    @FindBy(css = "tc-form__input")
    private WebElement textBoxname;

    @FindBy(css = "zWDds__Button pvXpn__Button--positive")
    private WebElement createButton;

    @FindBy(css = "raw_context_name")
    private WebElement labelWorkSpaceName;


    /**
     * Clicks the next button.
     */
    public void clickDashboardLink() {
        action.click(dashboardLink);
    }


    public void clickCreateWorkSpaceButton() {
        action.click(createWorkSpaceButton);
    }


    public void setName(final String strname) {
        action.setValue(textBoxname, strname);
    }

    public void clickCreateButton() {
        action.click(createButton);
    }

    public String getWorkSpaceLabel() {

         return labelWorkSpaceName.getText();

    }

}
