package validators.option;

import dto.OptionTradeProduct;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

/**
 * Trade product validator for AMERICAN style of trade.
 * It checks that excercise date is not null
 * and the date before trade date and after expire date.
 */
public class OptionValidatorAmericanOption implements Validator {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return OptionTradeProduct.class.equals(clazz);
    }

    /**
     * The method validates excecise date.
     *
     * @param target trade product object.
     * @param errors validation results.
     */
    @Override
    public void validate(Object target, Errors errors) {
        final OptionTradeProduct product = (OptionTradeProduct) target;
        final Date excerciseStartDate = product.getExcerciseStartDate();
        if ("AMERICAN".equals(product.getStyle())) {
            if (excerciseStartDate == null) {
                errors.rejectValue("excerciseStartDate", "excerciseStartDate.required");
                return;
            }
            final Date tradeDate = product.getTradeDate();
            final Date expireDate = product.getExpiryDate();
            if (tradeDate == null || expireDate == null) {
                return;
            }
            if (!excerciseStartDate.after(tradeDate) || !excerciseStartDate.before(expireDate)) {
                errors.rejectValue("excerciseStartDate", "excerciseStartDate.wrong");
            }
        }
    }
}
