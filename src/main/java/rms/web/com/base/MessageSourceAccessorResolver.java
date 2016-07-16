package rms.web.com.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

/**
 * @author
 */
public class MessageSourceAccessorResolver implements MessageSourceResolver {

    @Autowired
    private MessageSource messageSource;

    /*
     * (非 Javadoc)
     * @see rms.web.com.base.MessageSourceResolver#resolve(org.springframework.context.MessageSource)
     */
    @Override
    public MessageSourceAccessor resolve() {
        // TODO 自動生成されたメソッド・スタブ
        return new MessageSourceAccessor(messageSource);
    }

}