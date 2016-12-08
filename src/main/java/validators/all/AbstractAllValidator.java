package validators.all;

import dto.TradeProduct;
import org.springframework.validation.Validator;

/**
 * Abstract trade validator that consists methods for trade product validation.
 */
public abstract class AbstractAllValidator implements Validator{

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return TradeProduct.class.equals(clazz) || TradeProduct.class.equals(clazz.getSuperclass());
    }

}
