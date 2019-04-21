/**
 *
 */
package com.spr.flux.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Level;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.spr.flux.common.log.Logger;
import com.spr.flux.common.util.other.TheAppBase;

/**
 * @author sfd0
 */
@SuppressWarnings("unused")
public class ParseTest01 {

    /**
     *
     */
    public void test() {
        final String routineName = "test";
        //
        try {
            final SAXParserFactory factory = SAXParserFactory
                    .newInstance("org.apache.xerces.jaxp.SAXParserFactoryImpl", getClass().getClassLoader());
            final SAXParser parser = factory.newSAXParser();
            final File dir = new File("D:\\work_local\\tmp\\flux-8964");
            final File file = new File(dir, "output.ms03.xml");
            try (InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "UTF-8")) {
                final InputSource source = new InputSource(reader);
                parser.parse(source, new DefaultHandler() {

                    /**
                     * {@inheritDoc}
                     *
                     * @see org.xml.sax.helpers.DefaultHandler#warning(org.xml.sax.SAXParseException)
                     */
                    @Override
                    public void warning(final SAXParseException e) throws SAXException {
                        super.warning(e);
                        LOGGER.info(routineName + " - " + "Warning");
                    }

                    /**
                     * {@inheritDoc}
                     *
                     * @see org.xml.sax.helpers.DefaultHandler#error(org.xml.sax.SAXParseException)
                     */
                    @Override
                    public void error(final SAXParseException e) throws SAXException {
                        super.error(e);
                        LOGGER.info(routineName + " - " + "Error");
                    }
                });
            }
        } catch (final Exception ex) {
            LOGGER.warn(routineName + " - " + ex, ex);
        }
        //
        LOGGER.info(routineName + " - " + "Done");
    }

    /**
     *
     */
    private void notCalled() {

    }

    /**
     *
     */
    static final Logger LOGGER = new Logger(ParseTest01.class);

    /**
     * @param args
     *            --
     */
    public static void main(final String[] args) {
        final String routineName = ParseTest01.class.getName() + ".main";
        LOGGER.info(routineName + " - " + Arrays.toString(args));
        TheAppBase.setUpTestConsole("com.spr", Level.ALL, true);
        new ParseTest01().test();
    }

}
