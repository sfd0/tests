package tests.helpers;

import tests.simplepojo.IEmailData;
import tests.simplepojo.IValues;

/**
 * String to POJO converter.
 *
 * @param <T>
 *            -- POJO.
 */
public interface IJson2Pojo<T extends IEmailData & IValues> {

    /**
     * Convert a JSON string to a POJO.
     *
     * @param json
     *            -- The stringJSON reepresentation.
     * @param clazz
     *            -- The top-level type.
     * @return -- POJO representation.
     * @throws Exception
     *             -- We failed.
     */
    T parse(String json, Class<T> clazz) throws Exception;

}