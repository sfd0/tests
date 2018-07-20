package tests.email;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;

/**
 * SMTP client class that initializes itself from configuration in the repository.
 *
 * @author heibel
 */
public class SMTPClient {

    /**
     * The XPath to the SMPT server in {@link SprConfigurationObject sprconfigurationobject.xml}.
     */
    public static final String CFG_SMTP_SERVER = "/config/Smtp/Server";

    /**
     * The XPath to the SMPT port in {@link SprConfigurationObject sprconfigurationobject.xml}.
     */
    public static final String CFG_SMTP_PORT = "/config/Smtp/Port";

    /**
     * Creates an instance with the given settings.
     *
     * @param cfg
     *            provides the settings.
     */
    public SMTPClient(final SMTPConfiguration cfg) {
        this.cfg = cfg;
    }

    /**
     * Sends an email.
     *
     * @param email
     *            the email to be sent.
     */
    public void send(final Email<?> email) throws EmailException {
        final String routineName = "send";
        final org.apache.commons.mail.Email wrappedEmail = email.getEmailToBeSent();
        wrappedEmail.setHostName(this.cfg.getServer());
        wrappedEmail.setSmtpPort(this.cfg.getPort());
        final String response = wrappedEmail.send();
        LOGGER.trace(routineName + " - response: " + response);
    }

    private final SMTPConfiguration cfg;

    /**
    *
    */
    private static final Logger LOGGER = Logger.getLogger(SMTPClient.class);
}
