package validators.all;

import org.springframework.validation.Errors;
import dto.TradeProduct;

import java.util.Date;

/**
 * Trade product dates validator. It checks that value date before trade date.
 */
public class AllValidatorTradeDateBefore extends AbstractAllValidator {

    /**
     * The method validates trade product value date. The date must be before trade date.
     * It retrieves validation results via errors object.
     *
     * @param target trade product object.
     * @param errors validation results object.
     */
    @Override
    public void validate(Object target, Errors errors) {
        final TradeProduct product = (TradeProduct) target;
        final Date tradeDate = product.getTradeDate();
        final Date valueDate = product.getValueDate();
        if (tradeDate != null && valueDate != null && valueDate.before(tradeDate))
            errors.rejectValue("valueDate", "valueDate.before.tradeDate");
    }
}
