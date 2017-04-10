package rms.common.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Pattern;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 * 半角記号チェック 独自Annotation<br>
 * 半角記号が含まれる場合にエラー<br>
 * 対象記号は\p{Punct}を参照
 * @see "https://docs.oracle.com/javase/jp/8/docs/api/java/util/regex/Pattern.html"
 * @author
 */
@Documented
@Constraint(validatedBy = { NotSymbolValidator.class })
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotSymbol {
    String message() default "{NotSymbol.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

/**
 *
 */
class NotSymbolValidator implements ConstraintValidator<NotSymbol, String> {

    /** 記号表現文字列 */
    private static final String symbol = "\\p{Punct}";
    /** 記号の文字列パターン */
    private static final Pattern pattern = Pattern.compile("[" + symbol + "]");

    @Override
    public boolean isValid(String str,
                           ConstraintValidatorContext context) {
        boolean hasSymbol = pattern.matcher(str).find();
        if (hasSymbol) {
            // NG（記号を含む）
            return false;
        }

        // OK（記号を含まない）
        return true;

    }

    /*
     * (非 Javadoc)
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
     */
    @Override
    public void initialize(NotSymbol constraintAnnotation) {

    }
}