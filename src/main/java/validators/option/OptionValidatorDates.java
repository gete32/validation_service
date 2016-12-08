package validators.option;

import dto.OptionTradeProduct;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

/**
 * Trade product validator for expire date, premium date, delivery date.
 */
public class OptionValidatorDates implements Validator {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return OptionTradeProduct.class.equals(clazz);
    }

    /**
     * Trade product validator for expire date, premium date, delivery date.
     * It checks that the dates aren't null and expire date is not before delivery date
     * and premium date is not before delivery date.
     *
     * @param target trade product.
     * @param errors validation results.
     */
    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expiryDate", "expiryDate.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "premiumDate", "premiumDate.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "deliveryDate", "deliveryDate.required");
        final OptionTradeProduct product = (OptionTradeProduct) target;
        final Date expiryDate = product.getExpiryDate();
        final Date premiumDate = product.getPremiumDate();
        final Date deliveryDate = product.getDeliveryDate();
        if (expiryDate == null || premiumDate == null || deliveryDate == null) {
            return;
        }
        if (!expiryDate.before(deliveryDate)) {
            errors.rejectValue("expiryDate", "expireDate.beforeDeliveryDate");
        }
        if (!premiumDate.before(deliveryDate)) {
            errors.rejectValue("premiumDate", "premiumDate.beforeDeliveryDate");
        }
    }
}