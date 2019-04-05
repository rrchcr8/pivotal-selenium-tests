package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.Given;
import org.fundacionjala.pivotal.pages.Story;
import org.springframework.beans.factory.annotation.Autowired;

import org.fundacionjala.core.Environment;
import org.fundacionjala.pivotal.pages.Login;


public class StorySteps {
    @Autowired
    private Story story;
    @Given("create a Story called {name}")
    public void createStory(final String key) {

        story.createStory(key);
        //login.loginAs(Environment.getInstance().getValue(userNameKey), Environment.getInstance().getValue(passwordKey));
    }


}
