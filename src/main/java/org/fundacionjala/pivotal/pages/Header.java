package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Header extends AbstractPage {
    @FindBy(className = "tc_header_item tc_header_logo")
    private WebElement goDashboard;

    @FindBy(className = "tc_projects_dropdown_link tc_context_name")
    WebElement projecMenu;

    @FindBy(className = "Dropdown__button _2Oy9G__NotificationsBell__button")
    WebElement notifications;

    //@FindBy(className="zWDds__Button TtSTu__Button--header Dropdown__button")
    // List<WebElement> buttons;
    /* profile
       div[data-aid="ProfileDropdown"] .zWDds__Button*/
    /* whats new
    div[data-aid="ProductUpdatesDropdown__indicator--newHeader"] .zWDds__Button
     */

    public Dashboard goToDashBoard() {
        action.click(goDashboard);
        return new Dashboard();
    }

    public void openProjectMenu() {
        action.click(projecMenu);
    }
}
