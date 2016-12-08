package annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation allows to check field currency pair.
 */
@Documented
@Constraint(validatedBy = CurrencyPairValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrencyPair {

    String message() default "{CurrencyPair}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
