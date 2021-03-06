package rms.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 単項目チェック 独自Validator
 * @author
 */
public class Test1Validator implements ConstraintValidator<Test1Annotation, String> {

    private String value1;

    @Override
    public void initialize(Test1Annotation test) {
        this.value1 = test.value1();
    }

    @Override
    public boolean isValid(String input,
                           ConstraintValidatorContext con) {
        if (value1.equals(input)) {
            // 入力値とvalueが異なる場合はNG
            return true;
        }

        // OK
        return false;
    }
}
