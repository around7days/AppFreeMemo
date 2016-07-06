package rms.com.abstracts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * @author
 */
public abstract class AbstractService {
    @Autowired
    public MessageSource message;
}
