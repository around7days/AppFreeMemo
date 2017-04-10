package rms.common.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 * 配列nullチェック 独自Annotation<br>
 * 指定された配列の値が空白の場合にエラー
 * @author
 */
@Documented
@Constraint(validatedBy = NotNullArrayValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullArray {
    String message() default "{NotNullArray.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

/**
 *
 */
class NotNullArrayValidator implements ConstraintValidator<NotNullArray, Object[]> {

    @Override
    public boolean isValid(Object[] obj,
                           ConstraintValidatorContext context) {
        if (obj == null || obj.length == 0) {
            // NG
            return false;
        }

        // OK
        return true;

    }

    /*
     * (非 Javadoc)
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
     */
    @Override
    public void initialize(NotNullArray constraintAnnotation) {

    }
}