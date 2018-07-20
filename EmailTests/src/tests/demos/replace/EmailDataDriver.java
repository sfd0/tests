/**
 *
 */
package tests.demos.replace;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.google.gson.Gson;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Node;
import nu.xom.Nodes;
import tests.demos.annotatedpojo.EmailData;
import tests.demos.annotatedpojo.Value;

/**
 *
 */
public final class EmailDataDriver {

    /**
     * @param args
     *            --
     * @throws Exception
     *             --
     */
    public static void main(final String[] args) throws Exception {
        final String routineName = EmailDataDriver.class.getName() + ".main";
        LOGGER.addAppender(new ConsoleAppender(new PatternLayout("%d %5p [%t] %c - %m%n")));
        LOGGER.setLevel(Level.ALL);
        //
        EmailData ed = null;
        try (InputStream is = EmailDataDriver.class.getResourceAsStream("json_msg.txt")) {
            final Reader reader = new InputStreamReader(is, "UTF-8");
            final Gson gson = new Gson();
            ed = gson.fromJson(reader, EmailData.class);
            LOGGER.info(routineName + " - \n" + Arrays.toString(ed.getValues()));
            LOGGER.info("test" + Arrays.toString(ed.getCcAddress()));

        }
        final Map<String, String> valueMap = new HashMap<>();
        for (final Value value : ed.getValues()) {
            // Here we resolve URLs, adding markers as map values
            valueMap.put(value.getKey(), value.getValue());
        }
        String html = null;
        try (InputStream is = EmailDataDriver.class.getResourceAsStream("template.xml")) {
            final Reader reader = new InputStreamReader(is, "UTF-8");
            html = getHtml(reader);
            LOGGER.info(routineName + " - " + html);
        }
        html = resolveKeys(html, valueMap);
        LOGGER.info(routineName + " - " + html);
    }

    /**
     * Get the test HTML chunk. Extracted from XML -- to model target procedure.
     *
     * @param reader
     *            --
     * @return --
     */
    private static String getHtml(final Reader reader) {
        try {
            final Builder builder = new Builder();
            final Document doc = builder.build(reader);
            final Nodes nodes = doc.query("//content");
            final Node node = nodes.get(0);
            final String value = node.getValue();
            return value;
        } catch (final Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Fix up the replaceable parameters.
     *
     * @param line
     *            -- The raw text.
     * @param map
     *            -- The values to insert.
     * @return -- Resolved line.
     */
    private static String resolveKeys(final String line, final Map<String, String> map) {
        final Pattern pat = Pattern.compile("\\$\\$([^$]+)\\$\\$");
        final Matcher matcher = pat.matcher(line);
        final StringBuffer buf = new StringBuffer();
        while (matcher.find()) {
            final String key = matcher.group(1);
            String value = null;
            if (map.containsKey(key)) {
                value = map.get(key);
            } else {
                value = matcher.group();
            }
            matcher.appendReplacement(buf, value);
        }
        matcher.appendTail(buf);

        return buf.toString();
    }

    /**
    *
    */
    private static final Logger LOGGER = Logger.getLogger(EmailDataDriver.class);

    /** */
    private EmailDataDriver() {}
}
