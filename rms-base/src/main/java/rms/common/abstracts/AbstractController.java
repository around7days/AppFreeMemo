package rms.common.abstracts;

import org.springframework.beans.factory.annotation.Autowired;

import rms.common.base.MessageSourceEnumAccessor;
import rms.common.base.ProjectProperties;
import rms.common.base.UrlCreateHelper;

/**
 * AbstractController
 * @author
 */
public abstract class AbstractController {

    /** MessageSource */
    @Autowired
    protected MessageSourceEnumAccessor message;

    /** application.properties */
    @Autowired
    protected ProjectProperties properties;

    /** UrlCreateHelper */
    @Autowired
    protected UrlCreateHelper urlHelper;

    /**
     * 画面IDの取得
     * @return
     */
    protected abstract String getScreenId();
}
