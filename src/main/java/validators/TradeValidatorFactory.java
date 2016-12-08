package validators;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Map;

/**
 * {@inheritDoc}
 */
public class TradeValidatorFactory implements ValidatorFactory{

    private Map<String, List<Validator>> tradeValidators;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Validator> getInstance(final String productType) {
        return tradeValidators.get(productType);
    }

    /**
     * Setter for map of trade product types and list of validators.
     *
     * @param tradeValidators list of trade product validators.
     */
    @Required
    public void setTradeValidators(Map<String, List<Validator>> tradeValidators) {
        this.tradeValidators = tradeValidators;
    }
}
