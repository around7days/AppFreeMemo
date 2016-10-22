package rms.common.base;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * エラー画面コントローラー
 * @author
 */
@Controller
public class ErrorControllerImpl implements ErrorController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ErrorControllerImpl.class);

    /** ページURL */
    private static final String PAGE_URL = "html/error";

    /** マッピングURL */
    public static final String MAPPING_URL = WebSecurityConfig.ERROR_MAPPING_URL;

    /*
     * (非 Javadoc)
     * @see org.springframework.boot.autoconfigure.web.ErrorController#getErrorPath()
     */
    @Override
    public String getErrorPath() {
        return MAPPING_URL;
    }

    @RequestMapping(MAPPING_URL)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String error(HttpSession session) {
        if (session != null && !session.isNew()) {
            logger.error("session invalidate -> {}", session.getId());
            session.invalidate();
        }
        return PAGE_URL;
    }
}
