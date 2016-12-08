package validators.all;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import dto.TradeProduct;

import java.util.Currency;
import java.util.Set;

/**
 * Trade product currency validator. It checks that currency consists only supported currencies.
 */
@PropertySource("classpath:application.properties")
public class AllValidatorTradeInvalidISO extends AbstractAllValidator {

    @Value("#{'${currency.codes}'.split(',')}")
    private Set<String> codes;

    /**
     * The method validates that currency of product is supported and retrieves
     * validation results via errors object.
     *
     * @param target trade product object.
     * @param errors validation results object.
     */
    @Override
    public void validate(Object target, Errors errors) {
        final TradeProduct product = (TradeProduct) target;
        final String ccyPair = product.getCcyPair();

        //ccyPair is not null and its length = 6. We check it in TradeProduct
        final String ccy1 = ccyPair.substring(0, 3);
        final String ccy2 = ccyPair.substring(3, 6);

        if (ccy1.equals(ccy2)) {
            errors.rejectValue("ccyPair", "ccyPair.same");
        }
        if (!CollectionUtils.isEmpty(codes) && (!codes.contains(ccy1) || !codes.contains(ccy2))) {
            errors.rejectValue("ccyPair", "ccyPair.notSupported");
        }
        try {
            Currency.getInstance(ccy1);
            Currency.getInstance(ccy2);
        } catch (IllegalArgumentException ex) {
            errors.rejectValue("ccyPair","ccyPair.invalidISO");
        }
    }
}
