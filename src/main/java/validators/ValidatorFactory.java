package validators;

import org.springframework.validation.Validator;

import java.util.List;

/**
 * Factory(LightWeight) for trade product validator creation.
 */
public interface ValidatorFactory {

    /**
     * The method retrieves object of validator that
     * the list of relevant validators for the trade product type.
     *
     * @param productType type of trade product.
     * @return the list of relevant validators for trade product type.
     */
    List<Validator> getInstance(String productType);
}
