package services;

import java.util.List;
import java.util.Map;

/**
 * Service provides functionality for trade product
 */
public interface ProductService {

    /**
     * The method returns trade product class fields names.
     *
     * @param clazz trade product class.
     * @return list of class names.
     */
    List getFieldsForProductClass(Class clazz);

    /**
     * The method returns trade product class fields names only for supported products.
     *
     * @return Map of supported product as a key, and the list of fields names as a value.
     */
    Map<?, ?> getFieldsForSupportedProductsMap();
}
