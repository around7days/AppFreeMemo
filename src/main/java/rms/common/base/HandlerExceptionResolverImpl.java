package rms.common.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 例外ハンドラ共通処理
 * @author
 */
@Component
public class HandlerExceptionResolverImpl implements HandlerExceptionResolver {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(HandlerExceptionResolverImpl.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {
        if (ex instanceof AccessDeniedException) {
            // 操作権限エラー
            logger.error(ex.getMessage());
        } else {
            // その他エラー
            logger.error(ex.getMessage(), ex);
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:" + WebSecurityConfig.ERROR_MAPPING_URL);

        return mv;
    }
}