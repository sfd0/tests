package tests.edflux.email.html;

/**
 * Header information for an HTML page.
 *
 * @author heibel
 */
public interface HTMLHeader {

    /**
     * @return Returns the title of the HTML page, <code>null</code> to omit the
     *         titles.
     */
    String getTitle();

    /**
     * @return Returns the CSS styles, <code>null</code> to omit them.
     */
    String getStyles();

}
