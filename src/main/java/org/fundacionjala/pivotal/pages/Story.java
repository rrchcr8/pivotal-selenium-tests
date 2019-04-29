package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    @FindBy(css = ".autosaves.button.std.close")
    private WebElement closeButton;

    @FindBy(css = "textarea[name='story[name]']")
    private WebElement storyName;

    @FindBy(css = ".autosaves.id.text_value")
    private WebElement idStoryField;

    @FindBy(css = "div[class='dropdown story_type']")
    private WebElement storyTypeDropdown;

    @FindBy(xpath = "//div[@class='dropdown story_type']/a[1]/span")
    private WebElement storyTypeText;

    @FindBy(xpath = "//div[contains(@class,'dropdown story_estimate')]")
    private WebElement estimatedPointsDropdown;

    @FindBy(xpath = "//div[contains(@class,'dropdown story_estimate')]/a[1]/span")
    private WebElement estimatedPointsText;

    @FindBy(xpath = "//div[@class='dropdown story_requested_by_id']")
    private WebElement requesterDropdown;

    @FindBy(css = ".name.hbsAvatarName")
    private WebElement requesterName;

    @FindBy(xpath = "//a[contains(@class,'selectable_owner_row_element add_owner')]")
    private WebElement addStoryOwner;

    /** 1 follower, 2 followers. */
    @FindBy(css = ".count.not_read_only")
    private WebElement followerCount;

    @FindBy(css = "div[data-aid='BlockerAdd']")
    private WebElement addBlocker;

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
