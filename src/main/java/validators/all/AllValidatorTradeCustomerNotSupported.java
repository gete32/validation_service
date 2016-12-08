package validators.all;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import dto.TradeProduct;

import java.util.Set;

/**
 * Customer not supported trade product validator. It checks that customer of
 * trade product is supported and retrieves validation results to error object.
 */
@PropertySource("classpath:application.properties")
public class AllValidatorTradeCustomerNotSupported extends AbstractAllValidator {

    @Value("#{'${customer.supported}'.split(',')}")
    private Set<String> codes;

    /**
     * The method checks that customer is supported.
     * @param target trade product object that should be validated.
     * @param errors validation results.
     */
    @Override
    public void validate(Object target, Errors errors) {
        final TradeProduct product = (TradeProduct) target;
        if (CollectionUtils.isEmpty(codes) || codes.contains(product.getCustomer())) {
            return;
        }
        errors.rejectValue("customer", "customer.notSupported");
    }
}
