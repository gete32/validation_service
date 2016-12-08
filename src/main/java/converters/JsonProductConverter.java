package converters;

import dto.OptionTradeProduct;
import dto.ProductTypes;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * A converter convert JSONObject to OptionTradeProduct.
 */
public class JsonProductConverter implements CustomConverter<JSONObject, OptionTradeProduct> {

    private final static Pattern pattern = Pattern.compile("([0-9]{4})-([0-9]{2})-([0-9]{2})");
    private final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private String getString(final JSONObject json, final String key){
        return json.has(key) ? json.getString(key) : null;
    }

    /**
     * Convert the source of type Errors to target type JSONObject.
     *
     * @param jsonObject object that consist trade info.
     * @return new object of type OptionTradeProduct.
     */
    @Override
    public OptionTradeProduct convert(JSONObject jsonObject) {
        final OptionTradeProduct product = new OptionTradeProduct();
        convert(jsonObject, product);
        return product;
    }

    //we are trying to achieve an exact match here
    private Date dateParse(final String source){
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        try {
            return pattern.matcher(source).matches() ? dateFormat.parse(source) : null;
        } catch (ParseException e) {
            //We will check date in a validator
        }
        return null;
    }

    /**
     * Convert the source of type Errors to target type JSONObject.
     *
     * @param jsonObject object that consist trade info.
     * @param target object of OptionTradeProduct.
     */
    @Override
    public void convert(JSONObject source, OptionTradeProduct target) {
        final String type = getString(source, "type");
        target.setType(type);
        target.setTradeDate(dateParse(getString(source, "tradeDate")));
        target.setValueDate(dateParse(getString(source, "valueDate")));
        target.setCustomer(getString(source, "customer"));
        target.setCcyPair(getString(source, "ccyPair"));

        //convert option product
        if (ProductTypes.OPTION.toString().equals(type)){
            target.setStrategy(getString(source, "strategy"));
            target.setStyle(getString(source, "style"));
            target.setPremiumDate(dateParse(getString(source, "premiumDate")));
            target.setExpiryDate(dateParse(getString(source, "expiryDate")));
            target.setDeliveryDate(dateParse(getString(source, "deliveryDate")));
            target.setExcerciseStartDate(dateParse(getString(source, "excerciseStartDate")));
        }
        //No need to convert another fields because they will not be validated. It is just validation service.
    }
}
