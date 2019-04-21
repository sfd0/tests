/**
 *
 */
package com.spr.flux.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Properties;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Level;

import com.spr.flux.common.log.Logger;
import com.spr.flux.common.util.other.TheAppBase;

/**
 *
 */
public final class TransformTest01 {

    /**
     * @param args
     *            --
     * @throws Exception
     *             --
     */
    public static void main(final String[] args) throws Exception {
        final String routineName = TransformTest01.class.getName() + ".main";
        TheAppBase.setUpTestConsole("com.spr", Level.ALL, true);
        LOGGER.info(routineName + " - " + Arrays.toString(args));
        //
        final File ddsDir = new File("D:\\u02\\flux\\work\\DDS");
        final File sourceFile =
                new File(ddsDir, "small.xml" /* "416096_0_En_172-1_ChapterVersion.xml" */);
        final File destFile = new File(ddsDir, sourceFile.getName().replaceFirst("(\\.[^.]+)", ".tmp$1"));
        System.out.println("destFile.getName(): " + destFile.getName());
        final File stylesheetFile = new File(ddsDir, "copy.xsl");
        final FileInputStream styleStream = new FileInputStream(stylesheetFile);
        final Source styleSource = new StreamSource(styleStream);
        final FileInputStream istream = new FileInputStream(sourceFile);
        final Source source = new StreamSource(istream);
        final FileOutputStream ostream = new FileOutputStream(destFile);
        final Result result = new StreamResult(ostream);

        final TransformerFactory factory = TransformerFactory
                .newInstance(
                        "org.apache.xalan.processor.TransformerFactoryImpl",
                        TransformTest01.class.getClassLoader());
        final Transformer t = factory.newTransformer(styleSource);
        final Properties outputProperties = new Properties();
        outputProperties.put(OutputKeys.INDENT, "yes");
        outputProperties.put(OutputKeys.ENCODING, StandardCharsets.UTF_8.name());
        outputProperties.put("{http://xml.apache.org/xalan}indent-amount", "2");
        t.setOutputProperties(outputProperties);

        t.transform(source, result);
        System.out.println("destFile.getPath(): " + destFile.getPath());
    }

    /**
    *
    */
    private static final Logger LOGGER = new Logger(TransformTest01.class);

    /** */
    private TransformTest01() {}

}
