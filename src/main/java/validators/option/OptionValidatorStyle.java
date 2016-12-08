package validators.option;

import dto.OptionTradeProduct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Set;

/**
 * Trade product style validator for optional type of trade.
 */
@PropertySource("classpath:application.properties")
public class OptionValidatorStyle implements Validator {

    @Value("#{'${options.style}'.split(',')}")
    private Set<String> styles;

    @Override
    public boolean supports(Class<?> clazz) {
        return OptionTradeProduct.class.equals(clazz);
    }

    /**
     * The method validates that style is not empty and trade style is supported, e.g. AMERICAN or EUROPEAN.
     *
     * @param target trade product.
     * @param errors validation results.
     */
    @Override
    public void validate(Object target, Errors errors) {
        final OptionTradeProduct product = (OptionTradeProduct) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "style", "style.required");
        if (!styles.contains(product.getStyle())) {
            errors.rejectValue("style", "style.notSupported");
        }
    }
}
