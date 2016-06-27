package mms.com.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author
 */
@Controller
public class ErrorControllerImpl implements ErrorController {

    /** エラーページマッピング */
    private static final String PATH = "/error";

    /*
     * (非 Javadoc)
     * @see org.springframework.boot.autoconfigure.web.ErrorController#getErrorPath()
     */
    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(PATH)
    public String error() {
        return "html/error";
    }
}
