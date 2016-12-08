package converters;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.converter.Converter;
import org.springframework.validation.Errors;

/**
 * A converter convert errors to JSONObject
 */
public class ErrorsJsonConverter implements Converter<Errors, JSONObject> {

    @Autowired
    private MessageSource messageSource;

    /**
     * Convert the source of type Errors to target type JSONObject.
     *
     * @param source Errors
     * @return JSONObject
     */
    @Override
    public JSONObject convert(final Errors source) {
        final JSONObject jsonObject = new JSONObject();

        if (!source.hasErrors()) {
            return jsonObject;
        }
        source.getFieldErrors().stream().forEach(fieldError -> {
            String key = fieldError.getField();
            if (jsonObject.isNull(key))
                jsonObject.put(key, messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()));
            else
                jsonObject.put(key, jsonObject.get(key) + ", " + messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()));
        });
        return jsonObject;
    }
}
