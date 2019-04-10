package org.fundacionjala.pivotal.cucumber.steps.ui;

import java.util.HashMap;
import java.util.Map;

/** This class represent scenario context. **/
public class ScenarioContext {

    private final Map<String, Object> scenarioContext;

    private static final ScenarioContext context = new ScenarioContext();

    /** Default constructor. **/
    private ScenarioContext() {
        this.scenarioContext = new HashMap<>();
    }

    /**
     * Get context instance.
     *
     * @return ScenarioContext.
     */
    public static ScenarioContext getInstance() {
        return context;
    }

    /**
     * Set an element in context.
     *
     * @param key   string key
     * @param value object value
     */
    public void setContext(final String key, final Object value) {
        this.scenarioContext.put(key.toString(), value);
    }

    /**
     * This method get context value for key.
     *
     * @param key string
     * @return object value.
     */
    public Object getContext(final String key) {
        return this.scenarioContext.get(key.toString());
    }

    /**
     * This method check if a key is in the context.
     *
     * @param key string
     * @return boolean
     **/
    public Boolean isContains(final String key) {
        return this.scenarioContext.containsKey(key);
    }

}