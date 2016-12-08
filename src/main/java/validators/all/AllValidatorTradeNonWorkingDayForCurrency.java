package validators.all;

import dto.TradeProduct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.Errors;

import java.util.Calendar;
import java.util.Set;
/**
 * Trade product dates validator. It checks that value date is working day.
 */
@PropertySource("classpath:application.properties")
public class AllValidatorTradeNonWorkingDayForCurrency extends AbstractAllValidator {

    @Value("#{'${currency.nonworkingdays.USD}'.split(',')}")
    private Set<Integer> nonWorkingDaysForUSD;

    @Value("#{'${currency.nonworkingdays.EUR}'.split(',')}")
    private Set<Integer> nonWorkingDaysForEUR;

    /**
     * The method validates trade product value date. The date must be a working day.
     * It retrieves validation results via errors object.
     *
     * @param target trade product object.
     * @param errors validation results object.
     */
    @Override
    public void validate(Object target, Errors errors) {

        final TradeProduct product = (TradeProduct) target;
        Calendar calendar = Calendar.getInstance();

        //valueDate is not null, we check it in TradeProduct
        calendar.setTime(product.getValueDate());
        Integer dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (nonWorkingDaysForUSD.contains(dayOfWeek) || nonWorkingDaysForEUR.contains(dayOfWeek)) {
            errors.rejectValue("valueDate", "valueDate.weekend");
        }
    }
}
