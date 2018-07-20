/**
 *
 */
package tests.demos.annotatedpojo;

import com.fasterxml.jackson.annotation.JsonSetter;

/**
 *
 */
public class Value {

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    @JsonSetter("key")
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param typePhooey
     *            the type to set
     */
    @JsonSetter("type")
    public void setTypeX(final String typePhooey) {
        this.type = typePhooey;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param valuePhooey
     *            the value to set
     */
    @JsonSetter("value")
    public void setValueX(final String valuePhooey) {
        this.value = valuePhooey;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Value [key=" + key + ", type=" + type + ", value=" + value + "]";
    }

    private String key = null;

    private String type = null;

    private String value = null;
}
