package rms.com.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
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
        logger.error(ex.getMessage(), ex);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/error");

        return mv;
    }
}