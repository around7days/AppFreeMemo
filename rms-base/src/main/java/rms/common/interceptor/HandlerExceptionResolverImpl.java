package rms.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import rms.common.base.WebSecurityConfig;

/**
 * 例外ハンドラ共通処理
 * @author
 */
@Component
public class HandlerExceptionResolverImpl implements HandlerExceptionResolver, Ordered {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(HandlerExceptionResolverImpl.class);

    /** 例外ハンドラ優先順位(数値が大きいほど優先順位が低い) */
    private static int order = Integer.MAX_VALUE;

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {
        // エラーの種類によって処理を変更
        if (ex instanceof AccessDeniedException) {
            // 操作権限エラー
            logger.error("{}", ex.toString());
        } else {
            // その他エラー
            logger.error(ex.getMessage(), ex);
        }

        // エラーの返却
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:" + WebSecurityConfig.ERROR_MAPPING_URL);

        return mv;
    }

    @Override
    public int getOrder() {
        return order;
    }
}