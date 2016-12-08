package converters;

import org.springframework.core.convert.converter.Converter;

/**
 * {@inheritDoc}
 */
public interface CustomConverter<S,T> extends Converter<S,T> {
    /**
     * Convert the source of type S to target type T.
     * @param source the source object to convert, which must be an instance of S
     * @throws IllegalArgumentException if the source could not be converted to the desired target type
     */
    void convert(S source, T target);
}
