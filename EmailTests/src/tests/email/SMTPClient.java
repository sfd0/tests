package tests.email;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.mail.EmailException;

/**
 * SMTP client class that initializes itself from configuration
 */
public class SMTPClient {

    private String smtpServer;

    private int smtpPort;

    /**
     * Creates an instance with server settings from
     * {@link SprConfigurationObject sprconfigurationobject.xml}.
     *
     * @param session
     *            session object to access the configuration.
     */
    public SMTPClient() throws Exception {
        final Properties prop = new Properties();
        final InputStream input = getClass().getClassLoader().getResourceAsStream(
                "email.properties");
        prop.load(input);

        if (input == null) {
            System.out.println("Unable to find " + "email.properties");
        } else {
            setSmtpServer(prop.getProperty("smtpserver"));
            setSmtpPort(prop.getProperty("smtpport"));
            input.close();
        }
    }

    /**
     * Sends an email.
     *
     * @param email
     *            the email to be sent.
     */
    public void send(final Email<?> email) throws EmailException {
        final org.apache.commons.mail.Email wrappedEmail = email.getEmailToBeSent();
        wrappedEmail.setHostName(getSmtpServer());
        wrappedEmail.setSmtpPort(getSmtpPort());
        wrappedEmail.send();
    }

    public String getSmtpServer() {
        return smtpServer;
    }

    public void setSmtpServer(final String smtpServer) {
        this.smtpServer = smtpServer;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(final String smtpPort) {
        this.smtpPort = Integer.parseInt(smtpPort);
    }

}
