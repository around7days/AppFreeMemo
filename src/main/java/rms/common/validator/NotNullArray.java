package rms.common.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 配列nullチェック 独自Annotation
 * @author
 */
@Documented
@Constraint(validatedBy = NotNullArrayValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullArray {
    String message() default "Validate array.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
