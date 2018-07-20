package tests.email;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import javax.activation.DataHandler;
import javax.activation.URLDataSource;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;

import tests.edflux.email.html.HTMLStreamWriter;

/**
 * @author Anita Kumari
 */
public class SendMail {

    /**
     * @param fromEmail
     *            sender email address
     * @param fromName
     *            sender email name
     * @param toEmail
     *            receiver email address
     * @param subject
     *            email subject
     * @param content
     *            email content
     * @param config
     *            IDfsession
     * @throws Exception
     */
    public void sendMail(
            final String fromEmail,
            final String fromName,
            final String toEmail,
            final String subject,
            final String content,
            final SMTPConfiguration config) throws Exception {

        final String[] toEmails = getToEmails(toEmail);
        final EmailAddress[] emailAddress = formToEmailAddressArray(toEmails);
        System.setProperty("mail.mime.charset", "Cp1252");
        final EmailAddress from = new EmailAddress(fromEmail, fromName);
        final HTMLEmail email = new HTMLEmail(
                from,
                MimeUtility.encodeText(subject, "UTF-8", null),
                new EmailAddress(toEmails[0], ""),
                emailAddress);
        final HTMLStreamWriter html = email.getHTMLWriter();
        html.unescapedText(content);
        html.close();
        email.getEmailToBeSent();

        new SMTPClient(config).send(email);

    }

    /**
     * Sens HTML EMAIL.
     *
     * @param fromEmail
     *            sender email address
     * @param fromName
     *            sender email name
     * @param toEmail
     *            receiver email address
     * @param subject
     *            email subject
     * @param content
     *            email content
     * @param config
     *            IDfsession
     * @throws Exception
     */
    public void sendHTMLMail(
            final String fromEmail,
            final String fromName,
            final String toEmail,
            final String subject,
            final String content,
            final SMTPConfiguration config) throws Exception {
        LOGGER.info(" sendHTMLMail()");

        final MimeBodyPart htmlData = setHtmlData(content);

        final MimeBodyPart springerLogo = addSpringerLogo();

        final MimeBodyPart bfluxLogo = addBfluxLogo();

        final MimeMultipart multiPart = new MimeMultipart("related");
        multiPart.addBodyPart(htmlData);
        multiPart.addBodyPart(springerLogo);
        multiPart.addBodyPart(bfluxLogo);

        final MultiPartEmail email = new MultiPartEmail();
        email.setContent(multiPart);
        final String[] toEmails = getToEmails(toEmail);
        final Collection<InternetAddress> toList = new ArrayList<>();
        for (final String emailId : toEmails) {
            toList.add(new InternetAddress(emailId));

        }
        email.setTo(toList);
        email.setFrom(fromEmail, fromName);
        email.setSubject(subject);

        final WrapperEmail<MultiPartEmail> we = new WrapperEmail<>(email);

        final SMTPClient client = new SMTPClient(config);
        client.send(we);
        LOGGER.info(" Mail Sent");
    }

    /**
     * Add BFLUX logo to mail.
     *
     * @return MimeBodyPart
     * @throws IOException
     *             IOException
     * @throws MessagingException
     *             MessagingException
     */
    private MimeBodyPart addBfluxLogo() throws IOException, MessagingException {
        final MimeBodyPart part = new MimeBodyPart();
        final URL url = getClass().getResource(BFLUX_LOGO);
        final URLDataSource uds = new URLDataSource(url);
        final DataHandler udh = new DataHandler(uds);
        part.setDataHandler(udh);
        part.setDescription("bfluxemail.png");
        part.setDisposition(Part.INLINE);
        part.setContentID("<bfluxemail>");
        return part;
    }

    /**
     * Add Springernature logo.
     *
     * @return MimeBodyPart
     * @throws IOException
     *             IOException
     * @throws MessagingException
     *             MessagingException
     */
    private MimeBodyPart addSpringerLogo() throws IOException, MessagingException {
        final MimeBodyPart part = new MimeBodyPart();
        final URL url = getClass().getResource(SPRINGERNATURE_LOGO);
        final URLDataSource uds = new URLDataSource(url);
        final DataHandler udh = new DataHandler(uds);
        part.setDataHandler(udh);
        part.setDescription("springernaturemail.png");
        part.setDisposition(Part.INLINE);
        part.setContentID("<springernaturemail>");
        return part;
    }

    /**
     * Add Content to Html Email.
     *
     * @param content
     *            String
     * @return MimeBodyPart MessagingException
     * @throws MessagingException
     */
    private MimeBodyPart setHtmlData(final String content) throws MessagingException {
        final MimeBodyPart part = new MimeBodyPart();

        part.setText(
                "<div style='border: 1px solid #ccc;padding: 10px;'><div>"
                        + "<img align='left'style='height: 27px; margin-top: 1%;' src='cid:springernaturemail'>"
                        + " <img align='right' style='height: 42px; margin-right: 5%;' "
                        + "src='cid:bfluxemail'></div> "
                        + "<div style=\"clear:both\"></div>"
                        + "<hr/><br>"
                        + content
                        + "<div>",
                "US-ASCII",
                "html");
        return part;
    }

    /**
     * @param toEmail
     *            list of receivers email address
     * @return receivers email address separated by ,
     */
    private static String[] getToEmails(final String toEmail) {
        return toEmail.split(",");
    }

    /**
     * @param toEmails
     *            list of senders email address
     * @return sender email address
     */
    private static EmailAddress[] formToEmailAddressArray(final String[] toEmails) {
        if (toEmails.length == 1) {
            return null;
        }
        final EmailAddress[] emailAddress = new EmailAddress[toEmails.length - 1];
        for (int i = 0; i < toEmails.length; i++) {
            if (i != 0) {
                final EmailAddress address = new EmailAddress(toEmails[i]);
                emailAddress[i - 1] = address;
            }
        }
        return emailAddress;
    }

    private static final Logger LOGGER = Logger.getLogger(SendMail.class);

    private static final String SPRINGERNATURE_LOGO = "springernature.png";

    private static final String BFLUX_LOGO = "bflux.png";

}
