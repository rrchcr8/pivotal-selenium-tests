package org.fundacionjala.pivotal.pages;
import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

/**
 *  carlangas es el autor de esta
 */
public class Story extends AbstractPage {
    @FindBy(name = "story[name]")
    private WebElement storyName;

    @FindBy(id = "story_submit_cancel_c205")
    private WebElement cancelButton;

    @FindBy(id = "story_close_c205")
    private WebElement saveButton;

    @FindBy(id="panel_backlog_2324243")
    private WebElement addStoryButton;



    public void clickAddButton() {
        action.click(addStoryButton);
    }

    public void setStoryNameTextField(final String strStoryName) {
        action.setValue(storyName, strStoryName);
    }

    public void clickSaveButton() {
        action.click(saveButton);
    }

    public void createStory(final String name) {
        clickAddButton();
        setStoryNameTextField(name);
        clickSaveButton();

    }


}
