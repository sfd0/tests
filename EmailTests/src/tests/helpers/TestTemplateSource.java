/**
 *
 */
package tests.helpers;

import org.apache.log4j.Logger;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Node;
import nu.xom.Nodes;
import tests.utils.ResourceToString;

/**
 *
 */
public class TestTemplateSource implements ITemplateSource {

    /**
     * {@inheritDoc}
     *
     * @see tests.helpers.ITemplateSource#getBodyHtml()
     */
    @Override
    public String getBodyHtml(final String templateName) throws Exception {
        final String routineName = "getBodyHtml";
        LOGGER.debug(routineName + " - " + "Starting");
        final String xml = new ResourceToString().resourceToString(TEST_RESOURCE_NAME);
        LOGGER.info(routineName + " - xml:\n" + xml);
        final String html = getHtml(xml);
        LOGGER.debug(routineName + " - html:\n" + html);
        return html;
    }

    /**
     * Get the test HTML chunk. Extracted from XML -- to model target procedure.
     *
     * @param xml
     *            -- The XML from the template service.
     * @return -- The embedded HTML email body template.
     */
    private static String getHtml(final String xml) {
        try {
            final Builder builder = new Builder();
            final Document doc = builder.build(xml, null);
            final Nodes nodes = doc.query(HTML_XPATH);
            final Node node = nodes.get(0);
            final String value = node.getValue();
            return value;
        } catch (final Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     *
     */
    private static final Logger LOGGER = Logger.getLogger(TestTemplateSource.class);

    private static final String TEST_RESOURCE_NAME = "template.xml";
}
