package rms.common.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

/**
 * 記号チェック 独自Annotation<br>
 * 記号が含まれる場合にエラー【 !"#$%&'()*+-.,/:;<=>?@[\]^_`{|}~】
 * @author
 */
@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.METHOD, ElementType.FIELD })
@ReportAsSingleViolation
@Pattern(regexp = "^[ !\"#$%&'()*+-.,/:;<=>?@[\\]^_`{|}~]*")
public @interface NotSymbol {
    String message() default "{NotSymbol.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
