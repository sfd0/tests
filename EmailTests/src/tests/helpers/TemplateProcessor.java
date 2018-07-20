/**
 *
 */
package tests.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import tests.simplepojo.IValues;

/**
 *
 */
public class TemplateProcessor implements ITemplateProcessor {

    /**
     * {@inheritDoc}
     *
     * @see tests.helpers.ITemplateProcessor#getEmailBody(java.lang.String,
     *      tests.simplepojo.IEmailData)
     */
    @Override
    public String getEmailBody(final String template, final IValues ed) throws Exception {
        final String routineName = "getEmailBody";
        LOGGER.debug(routineName + " - " + "Starting");
        final String resolvedHtml = resolveKeys(template, ed);
        LOGGER.debug(routineName + " - resolvedHtml: " + resolvedHtml);
        return resolvedHtml;
    }

    /**
     * Fix up the replaceable parameters.
     *
     * @param rawHtml
     *            -- The raw text.
     * @param values
     *            -- The values to insert.
     * @return -- Resolved line.
     */
    private static String resolveKeys(final String rawHtml, final IValues values) {
        final Pattern pat = Pattern.compile("\\$\\$([^$]+)\\$\\$");
        final Matcher matcher = pat.matcher(rawHtml);
        final StringBuffer buf = new StringBuffer();
        while (matcher.find()) {
            final String key = matcher.group(1);
            String value = null;
            if (values.containsValue(key)) {
                value = values.getStringValue(key);
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
    private static final Logger LOGGER = Logger.getLogger(TemplateProcessor.class);
}
