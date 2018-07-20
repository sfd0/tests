/**
 *
 */
package tests.helpers;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import tests.simplepojo.IEmailData;
import tests.simplepojo.IValues;

/**
 * @param <T>
 *            -- POJO.
 */
public class Json2Pojo<T extends IEmailData & IValues> implements IJson2Pojo<T> {

    /**
     * {@inheritDoc}
     *
     * @see tests.helpers.IJson2Pojo#parse(java.lang.String, java.lang.Class)
     */
    @Override
    public T parse(final String json, final Class<T> clazz) throws Exception {
        final String routineName = "parse";
        LOGGER.debug(routineName + " - " + "Starting");
        // Use jackson, as the gson processor did not call the bean setters.
        final ObjectMapper mapper = new ObjectMapper();
        final T ed = mapper.readValue(json, clazz);
        // final Gson gson = new Gson();
        // final T ed = gson.fromJson(json, clazz);
        LOGGER.debug(routineName + " - " + "Returning pojo:\n" + ed.toString());
        return ed;
    }

    /**
     *
     */
    private static final Logger LOGGER = Logger.getLogger(Json2Pojo.class);
}