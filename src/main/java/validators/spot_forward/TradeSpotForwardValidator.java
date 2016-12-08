package validators.spot_forward;

import dto.TradeProduct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Calendar;
import java.util.Date;

/**
 * Trade product validator for spot trade and forward trade types of products.
 */
@PropertySource("classpath:application.properties")
public class TradeSpotForwardValidator implements Validator {

    @Value("${trade.spot.days:2}")
    private Integer spotDays;

    @Value("${trade.forward.days:5}")
    private Integer forwardDays;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return TradeProduct.class.equals(clazz.getSuperclass());
    }

    /**
     * The method checks that value date and trade date are not null and
     * value date is relevant according to the types of trade.
     *
     * @param target trade product.
     * @param errors validation results.
     */
    @Override
    public void validate(Object target, Errors errors) {
        final TradeProduct product = (TradeProduct) target;
        final Date valueDate = product.getValueDate();
        final Date tradeDate = product.getTradeDate();
        if (valueDate == null || tradeDate == null) {
            return;
        }

        final String type = product.getType();
        final int days;
        if ("SPOT".equals(type))
            days = spotDays;
        else
            days = forwardDays;

        final Calendar valueDateCalendar = Calendar.getInstance();
        valueDateCalendar.setTime(valueDate);
        final Calendar futureCalendar = Calendar.getInstance();
        futureCalendar.setTime(valueDate);
        futureCalendar.add(Calendar.DAY_OF_MONTH, days);
        if (valueDateCalendar.compareTo(futureCalendar) != 0 && valueDateCalendar.after(futureCalendar)) {
            errors.rejectValue("valueDate", "valueDate.wrong", new Object[]{type}, "Value date does not match the type");
        }
    }
}
