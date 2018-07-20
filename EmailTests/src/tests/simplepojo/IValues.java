/**
 *
 */
package tests.simplepojo;

/**
 *
 */
public interface IValues {

    /**
     * @param key
     *            --
     * @return --
     */
    Value getValue(String key);

    /**
     * @param key
     *            --
     * @return --
     */
    String getStringValue(String key);

    /**
     * @param key
     *            --
     * @return --
     */
    boolean containsValue(String key);

}
