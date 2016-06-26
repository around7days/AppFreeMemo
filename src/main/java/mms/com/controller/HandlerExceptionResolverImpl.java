package mms.com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class HandlerExceptionResolverImpl implements HandlerExceptionResolver {
    // TODO
    /** logger */
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(HandlerExceptionResolverImpl.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler,
                                         Exception ex) {
        System.out.println(ex.getClass() + " : " + ex.getMessage());

        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");

        return mv;
    }
}