/**
 *
 */
package tests.helpers;

import tests.simplepojo.IValues;

/**
 *
 */
public interface ITemplateProcessor {

    /**
     * @param template
     *            --
     * @param values
     *            -- Email data from client system.
     * @return -- Populated email body.
     * @throws Exception
     *             -- We failed.
     */
    String getEmailBody(String template, IValues values) throws Exception;
}
