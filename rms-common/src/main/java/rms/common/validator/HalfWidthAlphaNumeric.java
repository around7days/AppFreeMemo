package rms.common.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

/**
 * 半角英数字チェック 独自Annotation<br>
 * 半角英数字以外が含まれている場合にエラー
 * @author
 */
@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
@Pattern(regexp = "[a-zA-Z0-9]*")
public @interface HalfWidthAlphaNumeric {
    String message() default "{HalfWidthAlphaNumeric.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
