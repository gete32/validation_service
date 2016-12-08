package annotations;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Defines a logic to validate a given string is not empty,
 * its length equal 6 letters and all of them are capital letters.
 */
public class CurrencyPairValidator implements ConstraintValidator<CurrencyPair, String> {

    private final static Pattern pattern = Pattern.compile("^[A-Z]{6}$");

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(CurrencyPair constraintAnnotation) {}

    /**
     * Trade product currency pair validator logic.
     * if value is empty or length of value is not equal 6 or doesn't consist capital letters only
     * return false
     *
     * @param value string that
     * @param context contextual data
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !StringUtils.isEmpty(value) && value.length() == 6 && pattern.matcher(value).matches();
    }
}
