package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.By;
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

    @FindBy(css="div[data-aid='ProfileDropdown'] .zWDds__Button")
    WebElement profile;

    @FindBy(css="div[data-aid='ProductUpdatesDropdown__indicator--newHeader'] .zWDds__Button")
    WebElement whatsNew;

    public Dashboard goToDashBoard() {
        action.click(goDashboard);
        return new Dashboard();
    }

    public void openProjectMenu() {
        action.click(projecMenu);
    }

    public void openProfile() {
        action.click(profile);
    }

    public void openWhatsNew() {
        action.click(whatsNew);
    }

    public void openHelp() {
        List<WebElement> buttons = driver.findElements(By.cssSelector(
                ".zWDds__Button.TtSTu__Button--header.Dropdown__button"));
        for (WebElement element : buttons) {
            if(element != profile && element != whatsNew) {
                element.click();
                return;
            }
        }
    }
}
