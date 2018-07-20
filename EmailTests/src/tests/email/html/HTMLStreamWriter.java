package tests.email.html;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;

/**
 * Writes HTML output.
 *
 * @author heibel
 */
public class HTMLStreamWriter implements Closeable {

    private final PrintWriter out;

    /**
     * The tags opened by the constructor that need to be closed in
     * <code>close()</code>.
     */
    private final Stack<String> tagsToBeClosed;

    /** Indicates if we are just within a start tag. */
    private String startTag = null;

    /**
     * Creates an instance that writes to a given writer. Does not write
     * anything.
     *
     * @param out
     *            the writer to write to.
     */
    public HTMLStreamWriter(final Writer out) {
        this.out = (out instanceof PrintWriter) ? (PrintWriter) out : new PrintWriter(out);
        this.tagsToBeClosed = new Stack<>(2);
    }

    /**
     * Creates an instance that writes to a given writer. Writes the header and
     * the <code>body</code> start tag.
     *
     * @param out
     *            the writer to write to.
     * @param charsetName
     *            the name of character set used (optional). If specified, a
     *            meta tag is written to the HTML header.
     * @param header
     *            the header (optional).
     */
    public HTMLStreamWriter(final Writer out, final String charsetName,
                            final HTMLHeader header) throws IOException {
        this(out);

        startTag("html").newLine();
        startTag("head").newLine();

        if (charsetName != null) {
            startTag("meta")
                    //
                    .attribute("http-equiv", "content-type").attribute("content",
                            "text/html; charset=" + charsetName).endTag("meta").newLine();
        }

        if (header != null) {
            final String title = header.getTitle();
            if (title != null) {
                startTag("title").text(title).endTag("title").newLine();
            }

            final String css = header.getStyles();
            if (css != null) {
                startTag("style").attribute("type", "text/css").newLine().text(css).newLine()
                        .endTag("style").newLine();
            }
        }

        endTag("head").newLine();
        startTag("body").newLine();

        this.tagsToBeClosed.push("html");
        this.tagsToBeClosed.push("body");
    }

    /**
     * Creates an instance that writes to a file using the given character set.
     * Unmappable characters will be replaced by the character set's replacement
     * character (see {@link CodingErrorAction#REPLACE}). Writes the header and
     * the <code>body</code> start tag.
     *
     * @param f
     *            the file to write to.
     * @param charset
     *            the character set.
     * @param header
     *            the header.
     */
    public HTMLStreamWriter(final File f, final Charset charset,
                            final HTMLHeader header) throws IOException {
        this(new FileOutputStream(f), charset, header);
    }

    /**
     * Creates an instance that writes to a given stream using the given
     * character set. Unmappable characters will be replaced by the character
     * set's replacement character (see {@link CodingErrorAction#REPLACE}).
     * Writes the header and the <code>body</code> start tag.
     *
     * @param out
     *            the stream to write to.
     * @param charset
     *            the character set.
     * @param header
     *            the header (optional).
     */
    public HTMLStreamWriter(
                            //
                            final OutputStream out, final Charset charset,
                            final HTMLHeader header) throws IOException {
        this(new PrintWriter(new BufferedWriter(new OutputStreamWriter(out, createEncoder(
                charset)))), //
                charset.toString(), header);
    }

    /**
     * Creates an encoder for the given character set that replaces unmappable
     * characters by the character set's replacement character (see
     * {@link CodingErrorAction#REPLACE}).
     *
     * @param charset
     *            the character set.
     * @return the encoder.
     */
    private static CharsetEncoder createEncoder(final Charset charset) {
        final CharsetEncoder enc = charset.newEncoder();
        enc.onUnmappableCharacter(CodingErrorAction.REPLACE);
        enc.onMalformedInput(CodingErrorAction.REPLACE);
        return enc;
    }

    /** {@inheritDoc} */
    public HTMLStreamWriter startTag(final CharSequence tagName) throws IOException {
        closeStartTag();
        this.out.print('<');
        this.out.print(tagName);
        this.startTag = tagName.toString();
        return this;
    }

    /**
     * Writes an attribute definition.
     *
     * @param name
     *            the attribute name.
     * @param value
     *            the attribute value.
     * @return this object.
     * @throws IllegalStateException
     *             if the writer is not inside a start tag.
     */
    public HTMLStreamWriter attribute(final CharSequence name,
            final CharSequence value) throws IOException {
        if (this.startTag == null) {
            throw new IllegalStateException("Cannor write an attribute outside a start tag.");
        }

        this.out.print(' ');
        this.out.print(name);
        this.out.print("=\"");
        final int len = value.length();
        for (int i = 0; i < len; ++i) {
            final Character c = value.charAt(i);
            if (c == '"') {
                this.out.print("&quot;");
            } else {
                this.out.print(c);
            }
        }
        this.out.print('"');

        return this;
    }

    /**
     * Writes several attributes.
     *
     * @param namesAndValues
     *            the names and values. Each pair has the attribute name as
     *            first element and the attribute value as second element.
     * @return this object.
     * @throws IllegalStateException
     *             if the writer is not inside a start tag.
     */
    public HTMLStreamWriter attributes(final Iterable<? extends Pair<//
            ? extends CharSequence, ? extends CharSequence>> namesAndValues) throws IOException {
        for (final Pair<? extends CharSequence,
                ? extends CharSequence> nameAndValue : namesAndValues) {
            attribute(nameAndValue.getFirst(), nameAndValue.getSecond());
        }
        return this;
    }

    /**
     * Writes an end tag.
     *
     * @param tagName
     *            the tag name.
     * @return this object.
     */
    public HTMLStreamWriter endTag(final String tagName) throws IOException {
        if (this.startTag != null && this.startTag.equals(tagName)) {
            this.out.print("/>");
            this.startTag = null;
        } else {
            closeStartTag();
            this.out.print("</");
            this.out.print(tagName);
            this.out.print('>');
        }
        return this;
    }

    /**
     * writes escaped text. Ampersand and less-than characters are escaped.
     *
     * @param text
     *            the text.
     * @return this object.
     */
    public HTMLStreamWriter text(final CharSequence text) throws IOException {
        closeStartTag();
        if (text != null) {
            for (int i = 0; i < text.length(); ++i) {
                final char c = text.charAt(i);
                switch (c) {
                    case '<':
                        this.out.print("&lt;");
                        break;
                    case '&':
                        this.out.print("&amp;");
                        break;

                    default:
                        this.out.print(c);
                }
            }
        }
        return this;
    }

    /**
     * writes unescaped text.
     *
     * @param text
     *            the text.
     * @return this object.
     */
    public HTMLStreamWriter unescapedText(final CharSequence text) throws IOException {
        closeStartTag();
        if (text != null) {
            this.out.print(text);
        }
        return this;
    }

    /**
     * Writes a line break.
     *
     * @return this object.
     */
    public HTMLStreamWriter newLine() throws IOException {
        closeStartTag();
        this.out.println();
        return this;
    }

    /**
     * Closes all resources. Writes all closing tags for the start tags written
     * by the constructor.
     */
    @Override
    public void close() throws IOException {
        closeStartTag();
        while (!this.tagsToBeClosed.isEmpty()) {
            endTag(this.tagsToBeClosed.pop()).newLine();
        }
        this.out.close();
    }

    /**
     * Closes an open start tag and resets the flag. Does nothing if the start
     * tag flag is <code>false</code>.
     */
    private void closeStartTag() throws IOException {
        if (this.startTag != null) {
            this.out.print('>');
            this.startTag = null;
        }
    }

}
