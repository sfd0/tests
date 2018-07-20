/**
 *
 */
package tests.demos.pojo;

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
     * @param type
     *            the type to set
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(final String value) {
        this.value = value;
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
