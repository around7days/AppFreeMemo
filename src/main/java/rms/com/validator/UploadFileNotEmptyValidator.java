package rms.com.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import rms.com.validator.annotation.UploadFileNotEmpty;

/**
 * ファイルアップロードチェック 独自Validator
 * @author
 */
public class UploadFileNotEmptyValidator implements ConstraintValidator<UploadFileNotEmpty, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile multipartFile,
                           ConstraintValidatorContext context) {
        if (multipartFile == null // 存在しない
            || multipartFile.getOriginalFilename().isEmpty() // ファイル名が空白
            || multipartFile.getSize() == 0)// ファイルサイズが空
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
    public void initialize(UploadFileNotEmpty constraintAnnotation) {

    }
}
