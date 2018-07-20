/**
 *
 */
package tests.demos.gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.google.gson.Gson;

import tests.demos.simplepojo.EmailData;
import tests.demos.simplepojo.IEmailData;

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
        LOGGER.addAppender(new ConsoleAppender(new PatternLayout("%d %5p [%t]%c - %m%n")));
        LOGGER.setLevel(Level.ALL);

        try (InputStream is = EmailDataDriver.class.getResourceAsStream("json_msg.txt")) {
            final Reader reader = new InputStreamReader(is, "UTF-8");
            final Gson gson = new Gson();
            final IEmailData ed = gson.fromJson(reader, EmailData.class);
            LOGGER.info("wim " + routineName + " - \n" + ed);
        }
    }

    /**
    *
    */
    private static final Logger LOGGER = Logger.getLogger(EmailDataDriver.class);

    /** */
    private EmailDataDriver() {}
}
