package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This is a page located between the dashboard and the story page.
 */
@Component
public class Panel extends AbstractPage {

    @FindBy(css = "a[data-aid='AddButton']")
    private WebElement addStoryButton;

    @FindBy(css = "span[class='tracker_markup']")
    private List<WebElement> storyNames;

    @FindBy(css = "a[title='Select this story for bulk actions']")
    private List<WebElement> storyCheckBoxes;

    /** Basic method with the minimum requerid for create a story. */
    public void clickAddButton() {
        this.action.click(this.addStoryButton);
    }

    /**
     * This method check if a story text exist in story names list.
     *
     * @param text texto.
     * @return boolean true or false
     */
    public boolean existStory(final String text) {
        for (final WebElement element : this.storyNames) {
            if (element.getText().equals(text)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method expand a story.
     *
     * @param text name of the story
     */
    public void expandStory(final String text) {
        final String xpathExpression = "//span[@data-aid='StoryPreviewItem__"
                .concat("title' and text()='").concat(text)
                .concat("']/ancestor::header/child::button");
        this.action.click(By.xpath(xpathExpression));
    }

    /**
     * This method click over bulk checkbox of a story.
     *
     * @param text text of a specific story.
     */
    public void clickStoryCheckboxButton(final String text) {
        final String xpathExpression = "//span[@data-aid=\"StoryPreviewItem__title\" "
                .concat("and text()='").concat(text)
                .concat("']/ancestor::header/child::a");
        this.action.click(By.xpath(xpathExpression));
    }
}
