//package mms.com.validator.annotation;
//
//import java.lang.annotation.Documented;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//
//import mms.com.validator.Test1Validator;
//
///**
// * 相関チェック 独自Annotation
// * @author
// */
//@Documented
//@Constraint(validatedBy = Test2Validator.class)
//@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
//@Retention(RetentionPolicy.RUNTIME)
//public @interface Test2 {
//    String message() default "";
//
//    String value1();
//
//    String value2();
//
//    Class<?>[] groups() default {};
//
//    Class<? extends Payload>[] payload() default {};
//}