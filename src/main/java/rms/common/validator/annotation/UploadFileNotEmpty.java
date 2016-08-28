package rms.common.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import rms.common.validator.UploadFileNotEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * ファイルアップロード 独自Annotation
 * @author
 */
@Documented
@Constraint(validatedBy = UploadFileNotEmptyValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UploadFileNotEmpty {
    String message() default "Validate File Upload.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}