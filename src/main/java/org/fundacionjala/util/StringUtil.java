package org.fundacionjala.util;

import io.restassured.path.json.JsonPath;

/** This utility class manage string endpoints. **/
public final class StringUtil {
    private static final String BASE_URL = Properties.getValue("url.api");

    /** Default constructor. **/
    private StringUtil() {
    }

    /**
     * This method build explicit endpoint from a format url.
     * @param bareUrl url format.
     * @return string specific url.
     */
    public static String getExplicitEndpoint(final String bareUrl) {
        if (bareUrl.contains("{")) {
            final StringBuilder result = new StringBuilder();
            result.append(BASE_URL);
            for (final String part : bareUrl.split("\\{")) {
                if (part.contains("}")) {
                    final String key = getKey(part, '}');
                    final String value = getValue(key);
                    final String restPart = getRestPart(part, '}');
                    result.append(value).append(restPart);
                } else {
                    result.append(part);
                }
            }
            return result.toString();
        }
        return BASE_URL.concat(bareUrl);
    }

    /**
     * This method get a substring from a line until keyLimit.
     * @param line     string
     * @param keyLimit char limit of substring.
     * @return substring from begining to keylimit
     */
    private static String getKey(final String line, final char keyLimit) {
        final int index = line.indexOf(keyLimit);
        return line.substring(0, index);
    }

    /**
     * This method get the value associated with key in the ScenarioContext.
     * @param key string key.
     * @return value in the context for key.
     */
    private static String getValue(final String key) {
        if (key.contains(".")) {
            final String mainKey = getKey(key, '.');
            final JsonPath value = (JsonPath) ScenarioContext.getInstance()
                    .getContext(mainKey);
            final String subKey = getRestPart(key, '.');
            return value.get(subKey).toString();

        } else {
            return String.valueOf(ScenarioContext.getInstance().getContext(key));
        }
    }

    /**
     * This method get a substring from keyLimit to end of the line string.
     * @param line     string.
     * @param keyLimit char limit of substring.
     * @return substring from keylimit to end.
     */
    private static String getRestPart(final String line, final char keyLimit) {
        final int index = line.indexOf(keyLimit);
        return index == line.length() - 1 ? ""
                : line.substring(index + 1, line.length());
    }
}
