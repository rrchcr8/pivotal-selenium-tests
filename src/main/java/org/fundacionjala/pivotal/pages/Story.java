package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

/** This is the story page with all the locators. */
@Component
public class Story extends AbstractPage {

    @FindBy(xpath = "//section[@class='story_or_epic_header']/child::button")
    private WebElement collapseStory;

    @FindBy(css = "button[title='Delete this story']")
    private WebElement deleteStoryButton;

    @FindBy(css = ".autosaves.button.std.save")
    private WebElement saveButton;

    @FindBy(css = ".autosaves.cancel.clear")
    private WebElement cancelButton;

    @FindBy(css = "textarea[name='story[name]']")
    private WebElement storyName;

    @FindBy(css = ".autosaves.id.text_value")
    private WebElement idStoryField;

    @FindBy(css = ".selection.item_feature")
    private WebElement feature;

    @FindBy(css = ".selection.item_1")
    private WebElement points;

    @FindBy(css = ".name.hbsAvatarName")
    private WebElement requested;

    @FindBy(how = How.XPATH, using = ".//*[@id='add_owner_c517']")
    private WebElement addStoryOwner;

    @FindBy(xpath = ".//*[@id='blocker-edit-new']")
    private WebElement blockersTextField;

    @FindBy(css = ".tracker_markup")
    private WebElement storyNameCreated;

    @FindBy(css = "button[type='button'][title='Clone this story']")
    private WebElement cloneStory;

    @FindBy(css = "label[data-destination-state='start']")
    private WebElement startStory;

    @FindBy(xpath = "//div[@data-aid='StoryState__dropdown']/child::button")
    private WebElement stateButton;

    @FindBy(xpath = "//div[@data-aid='StoryState__dropdown']/button/span")
    private WebElement stateButtonText;

    /**
     * Basic method with the minimum requerid for create a story.
     *
     * @param strStoryName of the story that you want to create
     */
    public void setStoryNameTextField(final String strStoryName) {
        this.action.setValue(this.storyName, strStoryName);
    }

    /**
     * Basic method with the minimum requerid for create a story.
     */
    public void clickSaveButton() {
        this.action.click(this.saveButton);
    }

    /**
     * Basic method with the minimum requerid for create a story.
     *
     * @param name of the story
     */
    public void createStory(final String name) {
        clickAddButton();
        setStoryNameTextField(name);
        clickSaveButton();
    }


    /** Click delete button. */
    public void clickDeleteButton() {
        this.action.click(this.deleteStoryButton);
    }

    /**
     * This method get the state.
     *
     * @return String state.
     */
    public String getStateText() {
        return this.stateButtonText.getText();
    }
}
