package rms.com.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 例外ハンドラ
 * @author
 */
@Component
public class HandlerExceptionResolverImpl implements HandlerExceptionResolver {
    /** logger */
    private static Logger logger = LoggerFactory.getLogger(HandlerExceptionResolverImpl.class);

    //    // TODO
    //    /**
    //     * <pre>
    //     * 例外ハンドラの優先順位を1番にする。
    //     * DispatcherServletに保管されている他のHandlerExceptionResolverより先に実行されるように
    //     * オーダーを0にする。
    //     * これをしないと、@RequestParamアノテーションで指定した引数の型変換エラー時にこのハンドラが例外をキャッチできない。
    //     * </pre>
    //     */
    //    @Override
    //    public int getOrder() {
    //        return 0;
    //    }

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