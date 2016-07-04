//package mms.com.exception;
//
//import javax.servlet.http.HttpSession;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.web.ErrorController;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import mms.com.consts.PageIdConst;
//
///**
// * @author
// */
//@Controller
//public class ErrorControllerImpl implements ErrorController {
//
//    /** logger */
//    private static final Logger logger = LoggerFactory.getLogger(ErrorControllerImpl.class);
//
//    /** エラーページマッピング */
//    private static final String PATH = "/error";
//
//    /*
//     * (非 Javadoc)
//     * @see org.springframework.boot.autoconfigure.web.ErrorController#getErrorPath()
//     */
//    @Override
//    public String getErrorPath() {
//        return PATH;
//    }
//
//    @RequestMapping(PATH)
//    public String error(HttpSession session) {
//        if (session != null && !session.isNew()) {
//            logger.error("exception session invalidate to {}", session.getId());
//            session.invalidate();
//        }
//        return PageIdConst.ERROR;
//    }
//}
