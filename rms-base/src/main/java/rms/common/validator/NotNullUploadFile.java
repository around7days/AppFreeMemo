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

import org.springframework.web.multipart.MultipartFile;

/**
 * ファイルアップロード 独自Annotation<br>
 * ・ファイルが存在しない場合にエラー<br>
 * ・ファイルサイズが0の場合にエラー
 * @author
 */
@Documented
@Constraint(validatedBy = NotNullUploadFileValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullUploadFile {
    String message() default "{NotNullUploadFile.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

/**
 *
 */
class NotNullUploadFileValidator implements ConstraintValidator<NotNullUploadFile, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile multipartFile,
                           ConstraintValidatorContext context) {
        if (multipartFile == null // 存在しない
            || multipartFile.getOriginalFilename().isEmpty() // ファイル名が空白
            || multipartFile.getSize() == 0) // ファイルサイズが空
        {
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
    public void initialize(NotNullUploadFile constraintAnnotation) {

    }
}