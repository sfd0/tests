package tests.email.html;

/**
 * An abstract implementation of the {@link HTMLHeader} interface that returns
 * <code>null</code> from all methods.
 *
 * @author heibel
 */
public abstract class AbstractHTMLHeader implements HTMLHeader {

    /** {@inheritDoc} */
    @Override
    public String getTitle() {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public String getStyles() {
        return null;
    }

}
