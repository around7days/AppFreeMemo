package rms.web.com.base;

import org.springframework.context.support.MessageSourceAccessor;

/**
 * @author
 */
public interface MessageSourceResolver {

    MessageSourceAccessor resolve();

}