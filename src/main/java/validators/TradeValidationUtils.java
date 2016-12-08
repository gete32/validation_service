package validators;

import dto.TradeProduct;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/**
 * The class provides utils for validation.
 */
public class TradeValidationUtils {

    /**
     * The method provides custom validation.
     *
     * @param validators a list of validators which should be used for trade product validation.
     * @param product trade product that should be validated.
     * @param errors object that consists validation results.
     */
    public static void validateAll(List<Validator> validators, TradeProduct product, Errors errors) {
        if (!CollectionUtils.isEmpty(validators)) {
            validators.stream().forEach(validator -> ValidationUtils.invokeValidator(validator, product, errors));
        }
    }
}
