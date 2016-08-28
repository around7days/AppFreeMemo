package rms.common.validator;
//package rms.com.validator;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//import javax.validation.constraintvalidation.SupportedValidationTarget;
//import javax.validation.constraintvalidation.ValidationTarget;
//
//import rms.com.validator.annotation.Test2;
//
///**
// * 相関チェック 独自Validator
// * @author
// */
//@SupportedValidationTarget(ValidationTarget.PARAMETERS)
//public class Test2Validator implements ConstraintValidator<Test2, String> {
//
//    private String value1 = "";
//    private String value2 = "";
//
//    @Override
//    public void initialize(Test2 test) {
//        this.value1 = test.value1();
//        this.value2 = test.value2();
//    }
//
//    @Override
//    public boolean isValid(String inputs,
//                           ConstraintValidatorContext con) {
//
//        //        List<String> parameterNames = ((ConstraintValidatorContextImpl) constraintValidatorContext).getMethodParameterNames();
//        //
//        //        Map<String, Object> bindings = getBindings(inputs, parameterNames);
//
//        return false;
//    }
//
//    //    private Map<String, Object> getBindings(Object[] arguments,
//    //                                            List<String> parameterNames) {
//    //        Map<String, Object> bindings = new HashMap();
//    //
//    //        for (int i = 0; i < arguments.length; i++) {
//    //            bindings.put(parameterNames.get(i), arguments[i]);
//    //        }
//    //
//    //        return bindings;
//    //    }
//}
