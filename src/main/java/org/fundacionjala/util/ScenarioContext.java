package org.fundacionjala.util;

import org.apache.log4j.Logger;
import org.fundacionjala.core.Environment;

import java.util.HashMap;
import java.util.Map;

/** This class represent scenario context. **/
public final class ScenarioContext {
    private static final Logger LOGGER =
            Logger.getLogger(ScenarioContext.class.getName());

    private final Map<String, Object> scenarioContext;

    public static final String API_URL_KEY = "url.api";

    private static final ScenarioContext CONTEXT = new ScenarioContext();

    /** Default constructor. **/
    private ScenarioContext() {
        this.scenarioContext = new HashMap<>();
        String baseUrl = "https://www.pivotaltracker.com/services/v5";
        try {
            baseUrl = Environment.getInstance().getValue(API_URL_KEY);
        } catch (final ExceptionInInitializerError e) {
            LOGGER.info("Coudn't get api url from properties.", e);
        }
        this.setContext(API_URL_KEY, baseUrl);
    }

    /**
     * Get context instance.
     *
     * @return ScenarioContext.
     */
    public static ScenarioContext getInstance() {
        return CONTEXT;
    }

    /**
     * Set an element in context.
     *
     * @param key   string key
     * @param value object value
     */
    public void setContext(final String key, final Object value) {
        this.scenarioContext.put(key, value);
    }

    /**
     * This method get context value for key.
     *
     * @param key string
     * @return object value.
     */
    public Object getContext(final String key) {
        return this.scenarioContext.get(key);
    }

    /**
     * This method get context string value for key.
     *
     * @param key string
     * @return object value.
     */
    public static String getContextAsString(final String key) {
        return CONTEXT.scenarioContext.get(key).toString();
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
