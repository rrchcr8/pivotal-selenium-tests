package org.fundacionjala.pivotal.cucumber.steps.ui;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private final Map<String, Object> scenarioContext;

    private static final ScenarioContext context = new ScenarioContext();

    private ScenarioContext() {
        this.scenarioContext = new HashMap<>();
    }

    public static ScenarioContext getInstance() {
        return context;
    }

    public void setContext(final String key, final Object value) {
        this.scenarioContext.put(key.toString(), value);
    }

    public Object getContext(final String key) {
        return this.scenarioContext.get(key.toString());
    }

    public Boolean isContains(final String key) {
        return this.scenarioContext.containsKey(key);
    }

}