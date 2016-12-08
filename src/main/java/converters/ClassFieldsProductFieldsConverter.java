package converters;

import org.springframework.core.convert.converter.Converter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * A converter converts the array of class fields to the list of fields names
 */
public class ClassFieldsProductFieldsConverter implements Converter<Field[], List<String>> {
    /**
     * Method converts array of class fields to the list of fields names
     *
     * @param source fields of class
     * @return the list of field names
     */
    @Override
    public List<String> convert(Field[] source) {
        final List<String> fields = new ArrayList<>(source.length);
        for (Field field : source) {
            fields.add(field.getName());
        }
        return fields;
    }
}
