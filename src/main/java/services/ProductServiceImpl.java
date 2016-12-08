package services;

import dto.TradeProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;

/**
 * {@inheritDoc}
 */
public class ProductServiceImpl implements ProductService {

    @Autowired
    private Converter<Field[], List<String>> converter;

    @Resource(name = "supportedProducts")
    private Map<String, TradeProduct> supportedProductsMap;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getFieldsForProductClass(final Class clazz) {
        final List<String> fields = new LinkedList<>();
        Class<?> current = clazz;
        while (current.getSuperclass() != null) {
            fields.addAll(converter.convert(current.getDeclaredFields()));
            current = current.getSuperclass();
        }
        return fields;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, List> getFieldsForSupportedProductsMap() {
        final Map<String, List> supportedProducts = new HashMap<>(supportedProductsMap.size());
        supportedProductsMap.entrySet().stream().forEach(entry ->
                supportedProducts.put(entry.getKey(), getFieldsForProductClass(entry.getValue().getClass()))
        );
        return supportedProducts;
    }
}
