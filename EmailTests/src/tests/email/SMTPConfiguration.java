package tests.email;

/**
 * Interface for classes that provide SMTP settings.
 *
 * @author heibel
 */
public interface SMTPConfiguration {

    /** @return Returns the name of the SMTP server. */
    String getServer();

    /** @return Returns the name of the SMTP port. */
    int getPort();

}
