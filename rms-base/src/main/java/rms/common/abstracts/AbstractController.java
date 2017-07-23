package rms.common.abstracts;

import org.springframework.beans.factory.annotation.Autowired;

import rms.common.utils.MessageSourceEnumAccessor;
import rms.common.utils.ProjectProperties;
import rms.common.utils.UrlCreateHelper;

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
