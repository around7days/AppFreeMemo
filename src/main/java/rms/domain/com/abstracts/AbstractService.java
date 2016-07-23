package rms.domain.com.abstracts;

import rms.web.base.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author
 */
public abstract class AbstractService {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(AbstractService.class);

    @Autowired
    protected MessageSource message;

    // TODO DIの書き方に変更予定　※Listnerクラスも
    /**
     * 認証情報
     * @return
     */
    protected UserInfo getPrincipal() {
        return (UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
