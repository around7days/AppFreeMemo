package rms.com.abstracts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

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
}
