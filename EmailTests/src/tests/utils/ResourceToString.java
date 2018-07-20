package tests.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import org.apache.log4j.Logger;

/**
 *
 */
public class ResourceToString {

    /**
     * Get a resource as a String.
     *
     * @param resourceName
     *            -- What it says.
     * @return -- String.
     * @throws Exception
     *             -- We failed.
     */
    public String resourceToString(final String resourceName) throws Exception {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourceName);
                StringWriter writer = new StringWriter();) {
            final Reader reader = new InputStreamReader(is, "UTF-8");
            final char[] chars = new char[256];
            for (int kount = reader.read(chars); kount > 0; kount = reader.read(chars)) {
                writer.write(chars, 0, kount);
            }
            return writer.getBuffer().toString();
        }
    }

    /**
     * @param args
     *            -- From the command line.
     * @throws Exception
     *             --
     */
    public static void main(final String[] args) throws Exception {
        final String routineName = ResourceToString.class.getName() + ".main";
        LoggerSetup.setUp(LOGGER);
        final String resource = new ResourceToString().resourceToString("json_msg.txt");
        LOGGER.info(routineName + " - resource: " + resource);
    }

    /**
     *
     */
    private static final Logger LOGGER = Logger.getLogger(ResourceToString.class);
}