/**
 *
 */
package tests.helpers;

/**
 *
 */
public interface ITemplateSource {

    /**
     * Get the HTML template for the email.
     *
     * @param templateName
     *            -- What it says.
     * @return -- The HTML template.
     * @throws Exception
     *             --
     */
    String getBodyHtml(String templateName) throws Exception;

    /** path to HMTL chunk in XML. */
    String HTML_XPATH = "//content";
}
