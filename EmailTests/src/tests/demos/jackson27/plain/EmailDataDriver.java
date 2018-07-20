/**
 *
 */
package tests.demos.jackson27.plain;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.fasterxml.jackson.databind.ObjectMapper;

import tests.demos.pojo.EmailRequest;

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
        LOGGER.addAppender(new ConsoleAppender(new PatternLayout("%d %5p [%t] %c - %m%n")));
        LOGGER.setLevel(Level.ALL);
        //
        try (InputStream is = EmailDataDriver.class.getResourceAsStream("json_msg.txt")) {
            final Reader reader = new InputStreamReader(is, "UTF-8");
            final ObjectMapper mapper = new ObjectMapper();
            final EmailRequest req = mapper.readValue(reader, EmailRequest.class);
            LOGGER.info(routineName + " - \n" + req.getEmail());
        }
    }

    /**
    *
    */
    private static final Logger LOGGER = Logger.getLogger(EmailDataDriver.class);

    /** */
    private EmailDataDriver() {}
}
