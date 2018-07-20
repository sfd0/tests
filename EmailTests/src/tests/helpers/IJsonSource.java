package tests.helpers;

/**
 * Source of a string JSON representation.
 */
public interface IJsonSource {

    /**
     * Gets a JSON string from somewhere.
     *
     * @return -- A string JSON representation.
     * @throws Exception
     *             -- We Failed.
     */
    String getJson() throws Exception;

}