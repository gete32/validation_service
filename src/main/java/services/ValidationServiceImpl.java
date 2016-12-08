package services;

import converters.CustomConverter;
import dto.OptionTradeProduct;
import dto.ProductTypes;
import dto.TradeProduct;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.ValidationUtils;
import validators.TradeValidationUtils;
import validators.TradeValidatorFactory;

/**
 * {@inheritDoc}
 */
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private TradeValidatorFactory factory;

    @Autowired
    private Converter<Errors, JSONObject> errorsJsonConverter;

    @Autowired
    SmartValidator validator;

    @Autowired
    private CustomConverter<JSONObject, OptionTradeProduct> stringAbstractProductConverter;

    /**
     * {@inheritDoc}
     */
    @Override
    public JSONObject validate(TradeProduct product, Errors errors) {
        ValidationUtils.invokeValidator(validator, product, errors);
        if (!errors.hasErrors()) {
            TradeValidationUtils.validateAll(factory.getInstance(ProductTypes.ALL.toString()), product, errors);
            if (!errors.hasErrors()) {
                TradeValidationUtils.validateAll(factory.getInstance(product.getType()), product, errors);
            }
        }
        return errorsJsonConverter.convert(errors);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JSONObject validate(String json, OptionTradeProduct product, Errors errors) {
        final JSONObject jsonObject = new JSONObject(json);
        stringAbstractProductConverter.convert(jsonObject, product);
        return this.validate(product, errors);
    }
}
