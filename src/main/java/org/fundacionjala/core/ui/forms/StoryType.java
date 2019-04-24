package org.fundacionjala.core.ui.forms;

/** This class represent story types. **/
public enum StoryType {
    FEATURE("feature"),
    BUG("bug"),
    CHORE("chore"),
    RELEASE("release");

    private final String label;

    /**
     * Constructor.
     *
     * @param newLabel label.
     */
    StoryType(final String newLabel) {
        this.label = newLabel;
    }

    /**
     * This method search a story type since label.
     *
     * @param label string label.
     * @return StoryType.
     */
    public static StoryType getStoryType(final String label) {
        for (final StoryType type : StoryType.values()) {
            if (type.label.equals(label)) {
                return type;
            }
        }
        return FEATURE;
    }

    /**
     * This method get the label.
     *
     * @return label.
     */
    public String label() {
        return this.label;
    }
}
