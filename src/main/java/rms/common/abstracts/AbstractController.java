package rms.common.abstracts;

import org.springframework.beans.factory.annotation.Autowired;

import rms.common.bean.UrlCreateHelper;
import rms.common.bean.MessageSourceEnumAccessor;

/**
 * AbstractController
 * @author
 */
public abstract class AbstractController {

    /** MessageSource */
    @Autowired
    protected MessageSourceEnumAccessor message;

    /** UrlCreateHelper */
    @Autowired
    protected UrlCreateHelper urlHelper;

    /**
     * 画面IDの取得
     * @return
     */
    protected abstract String getScreenId();
}
