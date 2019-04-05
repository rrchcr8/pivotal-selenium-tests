package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @rrch is the author
 */
public class Story extends AbstractPage {

    @FindBy(id = "panel_backlog_2324243")
    private WebElement addStoryButton;

    @FindBy(id = "story_close_c205")
    private WebElement saveButton;

    @FindBy(id = "story_submit_cancel_c205")
    private WebElement cancelButton;

    // css selector   #view577 > section > section.model_details > form > section > fieldset > div > div.AutosizeTextarea__container___31scfkZp > textarea
    //xpach   *[@id="view219"]/section/section[1]/form/section/fieldset/div/div[1]/textarea

    @FindBy(name = "story[name]")
    private WebElement storyName;

    //@FindBy(xpath = ".//*[@id=\"view219\"]")
    //private WebElement storyName;

    //@FindBy(how = How.XPATH, using = ".//*[@id=\"view219\"]")
    //private WebElement storyName2;


    /**
     * another locators for non required fields
     */
    @FindBy(id = "story_copy_id_value_c205")
    private WebElement idStoryField;

    //*[@id="story_type_dropdown_c517"]/span
    @FindBy(id = "story_type_dropdown_c517")
    private WebElement feature;

    @FindBy(id = "story_estimate_dropdown_c517")
    private WebElement points;

    @FindBy(id = "story_requested_by_id_dropdown_c517")
    private WebElement requested;

    @FindBy(how = How.XPATH, using = ".//*[@id=\"add_owner_c517\"]")
    private WebElement addStoryOwner;

    @FindBy(xpath = ".//*[@id=\"blocker-edit-new\"]")
    private WebElement blockersTextField;

    @FindBy(xpath = ".//*[@id=\"view531\"]/section/section[2]/div/div/div/div[2]/div/button[1]")
    private WebElement addblockersButton;


    /**
     * Basic method with the minimum requerid for create a story.
     */

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
