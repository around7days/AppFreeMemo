package rms.common.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 単項目チェック 独自Annotation
 * @author
 */
@Documented
@Constraint(validatedBy = Test1Validator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Test1Annotation {
    String message() default "Validate Test.";

    String value1();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}