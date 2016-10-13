package rms.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 配列nullチェック 独自Validator
 * @author
 */
public class NotNullArrayValidator implements ConstraintValidator<NotNullArray, Object[]> {

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
