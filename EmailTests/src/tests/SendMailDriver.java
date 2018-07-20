/**
 *
 */
package tests;

import java.util.Properties;

import org.apache.log4j.Logger;

import tests.email.SMTPConfiguration;
import tests.email.SendMail;
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
     *
     */
    public void send() {
        final String routineName = "send";
        try {
            LOGGER.info(routineName + " - " + "Starting");
            final IData ed = getClientSystemData(routineName);
            final String emailBody = getBody(ed);
            final SendMail sm = new SendMail();
            final String fromEmail = ed.getFromAddress();
            final String fromName = "placeholder name";
            final String toEmail = ed.getToAddress()[0];
            final String subject = ed.getSubject();

            sm.sendHTMLMail(fromEmail, fromName, toEmail, subject, emailBody, getConfig());

            LOGGER.info(routineName + " - " + "Done.");
        } catch (final Exception ex) {
            LOGGER.warn(routineName + " - " + ex.getMessage(), ex);
        }

    }

    /**
     * Fake property retrieval. Replace later by Springified whatsit.
     *
     * @return -- The config object.
     * @throws Exception
     *             _We failed.
     */
    private SMTPConfiguration getConfig() throws Exception {
        final Properties props = new Properties();
        props.load(getClass().getClassLoader().getResourceAsStream("email.properties"));
        final SMTPConfiguration c = new SMTPConfiguration() {

            @Override
            public String getServer() {
                return props.getProperty("smtpserver");
            }

            @Override
            public int getPort() {
                return Integer.parseInt(props.getProperty("smtpport"));
            }
        };
        return c;
    }

    /**
     * @param args
     *            -- From the command line.
     */
    public static void main(final String[] args) {
        LoggerSetup.setUp("tests");
        //
        new SendMailDriver().send();
    }

    /**
     * Get the email HTML body.
     *
     * @param ed
     *            -- THe client system data.
     * @return -- The body HTML.
     * @throws Exception
     *             -- We failed.
     */
    private String getBody(final IData ed) throws Exception {
        final String routineName = "getBody";

        final ITemplateSource templateSource = new TestTemplateSource();
        final ITemplateProcessor templateProcessor = new TemplateProcessor();
        final String templateName = ed.getTemplate().getName();
        final String html = templateSource.getBodyHtml(templateName);
        final String emailBody = templateProcessor.getEmailBody(html, ed);
        LOGGER.info(routineName + " - emailBody:\n" + emailBody);
        return emailBody;
    }

    /**
     * @param routineName
     * @return
     * @throws Exception
     */
    private IData getClientSystemData(final String routineName) throws Exception {
        final IJsonSource jsonSource = new TestJsonSource();
        final String json = jsonSource.getJson();
        final IJson2Pojo<EmailData> json2Pojo = new Json2Pojo<>();
        final IData ed = json2Pojo.parse(json, EmailData.class);
        LOGGER.info(routineName + " - ed:\n" + ed);
        return ed;
    }

    /** */
    private SendMailDriver() {}

    /**
     *
     */
    private static final Logger LOGGER = Logger.getLogger(SendMailDriver.class);

}
