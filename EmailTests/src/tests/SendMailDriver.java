/**
 *
 */
package tests;

import org.apache.log4j.Logger;

import tests.helpers.IJson2Pojo;
import tests.helpers.IJsonSource;
import tests.helpers.ITemplateProcessor;
import tests.helpers.ITemplateSource;
import tests.helpers.Json2Pojo;
import tests.helpers.TemplateProcessor;
import tests.helpers.TestJsonSource;
import tests.helpers.TestTemplateSource;
import tests.simplepojo.EmailData;
import tests.simplepojo.IData;
import tests.utils.LoggerSetup;

/**
 *
 */
public final class SendMailDriver {

    /**
     * @param args
     *            -- From the command line.
     */
    public static void main(final String[] args) {
        final String routineName = SendMailDriver.class.getName() + ".main";
        LoggerSetup.setUp("tests");
        //
        try {
            LOGGER.info(routineName + " - " + "Starting");
            final IJsonSource jsonSource = new TestJsonSource();
            final String json = jsonSource.getJson();
            final IJson2Pojo<EmailData> json2Pojo = new Json2Pojo<>();
            final IData ed = json2Pojo.parse(json, EmailData.class);
            LOGGER.info(routineName + " - ed:\n" + ed);
            final ITemplateSource templateSource = new TestTemplateSource();
            final ITemplateProcessor templateProcessor = new TemplateProcessor();
            final String templateName = ed.getTemplate().getName();
            final String html = templateSource.getBodyHtml(templateName);
            final String emailBody = templateProcessor.getEmailBody(html, ed);
            LOGGER.info(routineName + " - emailBody:\n" + emailBody);
            LOGGER.info(routineName + " - " + "Done.");
        } catch (final Exception ex) {
            LOGGER.warn(routineName + " - " + ex.getMessage(), ex);
        }
    }

    /** */
    private SendMailDriver() {}

    /**
     *
     */
    private static final Logger LOGGER = Logger.getLogger(SendMailDriver.class);

}
