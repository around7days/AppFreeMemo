package rms.common.interceptor;

import java.util.Formatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * HandlerInterceptorImplクラス
 * @author
 */
public class HandlerInterceptorImpl implements HandlerInterceptor {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(HandlerInterceptorImpl.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // URIのログ表示
        StringBuilder uri = new StringBuilder(request.getRequestURI());
        String query = request.getQueryString();
        if (query != null && !query.isEmpty()) {
            uri.append("?");
            uri.append(query);
        }
        logger.info("request uri  -> {}", uri);

        // リクエストパラメータのログ表示
        // 負荷軽減の為に事前に判定
        if (logger.isDebugEnabled()) {
            request.getParameterMap().entrySet().forEach(entry -> {
                for (String val : entry.getValue()) {
                    Formatter fm = new Formatter();
                    fm.format("%-15s : %s", entry.getKey(), val);
                    logger.debug("param -> {}", fm);
                }
            });
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            String uri = modelAndView.getViewName();
            logger.info("return uri   -> {}", uri);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
    }

}
