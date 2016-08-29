package rms.common.base;

//package rms.com.base;
//
//import rms.web.base.UserInfo;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
///**
// * @author
// */
//public class UserInfoResolverImpl implements UserInfoResolver {
//
//    /*
//     * (非 Javadoc)
//     * @see rms.com.base.UserInfoResolver#resolve()
//     */
//    @Override
//    public UserInfo resolve() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null) {
//            return null;
//        }
//
//        return (UserInfo) authentication.getPrincipal();
//    }
//
//}