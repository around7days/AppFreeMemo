package rms.common.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * ファイルアップロード 独自Annotation
 * @author
 */
@Documented
@Constraint(validatedBy = NotNullUploadFileValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullUploadFile {
    String message() default "Validate File Upload.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
