package services;

import dto.OptionTradeProduct;
import dto.TradeProduct;
import org.json.JSONObject;
import org.springframework.validation.Errors;

/**
 * The service provides trade validation.
 */
public interface ValidationService {

    /**
     * The method provides functionality for trade product validation.
     *
     * @param product trade product.
     * @param errors information about data-binding and validation
     * errors for a trade product object.
     * @return JSONObject that represents validation result.
     */
    JSONObject validate(final TradeProduct product, Errors errors);
    /**
     * The method provides functionality for trade product validation.
     * It validates json string and uses product to bind results of validation.
     *
     * @param json json string for a validation.
     * @param product trade product for validation result binding.
     * @param errors information about data-binding and validation
     * errors for the json string.
     * @return JSONObject that represents validation result.
     */
    JSONObject validate(final String json, OptionTradeProduct product, Errors errors);
}
