package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;
/**
 * This is the story page.
 */
@Component
public class Story extends AbstractPage {
    /**
     * This is the add story button.
     */
    @FindBy(css = "a[data-aid='AddButton']")
    private WebElement addStoryButton;
    /**
     * This is the save button.
     */
    @FindBy(css = ".autosaves.button.std.save")
    private WebElement saveButton;
    /**
     * This is the cancel button.
     */
    //@FindBy(id = "story_submit_cancel_c205")
    //@FindBy(className = "autosaves cancel clear")
    @FindBy(css = ".autosaves.cancel.clear")
    private WebElement cancelButton;
    /**
     * This is the story page.
     */
    //@FindBy(name = "story[name]")
    @FindBy(css = "textarea[name='story[name]']")
    private WebElement storyName;
    /**
     * another locators for non required fields.
     */
    //@FindBy(id = "story_copy_id_value_c205")
    @FindBy(css = ".autosaves.id.text_value")
    private WebElement idStoryField;
    /**
     * another locators for non required fields.
     */
    //@FindBy(id = "story_type_dropdown_c517")
    @FindBy(css =".selection.item_feature" )
    private WebElement feature;
    /**
     * another locators for non required fields.
     */
    @FindBy(css = ".selection.item_1")
    private WebElement points;
    /**
     * another locators for non required fields.
     */
    @FindBy(css = ".name.hbsAvatarName")
    private WebElement requested;
    /**
     * another locators for non required fields.
     */
    @FindBy(how = How.XPATH, using = ".//*[@id='add_owner_c517']")
    private WebElement addStoryOwner;
    /**
     * another locators for non required fields.
     */
    @FindBy(xpath = ".//*[@id='blocker-edit-new']")
    private WebElement blockersTextField;
    /**
     * Basic method with the minimum requerid for create a story.
     */
    public void clickAddButton() {
        action.click(addStoryButton);
    }
    /**
     * Basic method with the minimum requerid for create a story.
     * @param strStoryName of the story that you want to create
     */
    public void setStoryNameTextField(final String strStoryName) {
        action.setValue(storyName, strStoryName);
    }
    /**
     * Basic method with the minimum requerid for create a story.
     */
    public void clickSaveButton() {
        action.click(saveButton);
    }
    /**
     * Basic method with the minimum requerid for create a story.
     * @param  name of the story
     */
    public void createStory(final String name) {
        clickAddButton();
        setStoryNameTextField(name);
        clickSaveButton();
    }

}
