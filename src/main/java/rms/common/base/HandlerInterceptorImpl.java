package rms.common.base;

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
        StringBuilder uri = new StringBuilder(request.getRequestURI());
        String query = request.getQueryString();
        if (query != null && !query.isEmpty()) {
            uri.append("?");
            uri.append(query);
        }
        logger.info("request uri  -> {}", uri);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        String uri = modelAndView.getViewName();
        logger.info("return uri   -> {}", uri);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
    }

}
