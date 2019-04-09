package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.springframework.stereotype.Component;

import java.util.List;

/** Task panel. **/
@Component
public class Tasks extends AbstractPage {
    @FindBy(css = "div[data-aid=\"Tasks\"] h4")
    private WebElement status;

    @FindBy(css = "div[data-aid=\"TaskDescription\"] span p")
    private List<WebElement> tasksNames;

    @FindBy(css = "textarea[data-aid='editor']")
    private WebElement taskText;

    @FindBy(css = "button[data-aid='saveTaskButton'] ")
    private WebElement saveTask;

    @FindBys({
            @FindBy(className = "AddSubresourceButton__message___2vsNCBXi"),
            @FindBy(linkText = "Add a task")
    })
    private WebElement addTaskBtn;

    public void clickOnAddTaskButton() {
        this.action.click(this.addTaskBtn);
    }

    public void setTaskText(final String text) {
        this.taskText.sendKeys(Keys.TAB);
        this.taskText.clear();
        this.taskText.sendKeys(text);
    }

    public void selectTask(final String text) {
        for (final WebElement element : this.tasksNames) {
            if (element.getText().equals(text)) {
                this.action.click(element);
                return;
            }
        }
    }

    public void clickOnSave() {
        this.action.click(this.saveTask);
    }

    public void deleteTask(final String text) {

    }

    public boolean existTask(final String text) {
        for (final WebElement element : this.tasksNames) {
            if (element.getText().equals(text)) {
                return true;
            }
        }
        return false;
    }
}
