package rms.com.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * @author
 */
public class MessageSourceAccessorResolver implements MessageSourceResolver {
    //TODO 未完成

    @Autowired
    private MessageSource messageSource;

    /*
     * (非 Javadoc)
     * @see rms.web.com.base.MessageSourceResolver#resolve(org.springframework.context.MessageSource)
     */
    @Override
    public MessageSourceAccessor resolve() {
        return new MessageSourceAccessor(messageSource);
    }

}