/**
 *
 */
package tests.helpers;

import tests.utils.ResourceToString;

/**
 *
 */
public class TestJsonSource implements IJsonSource {

    /**
     * {@inheritDoc}
     *
     * @see tests.helpers.IJsonSource#getJson()
     */
    @Override
    public String getJson() throws Exception {
        return new ResourceToString().resourceToString(TEST_RESOURCE_NAME);
    }

    private static final String TEST_RESOURCE_NAME = "json_msg.txt";
}
