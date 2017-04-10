package rms.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanInstantiationException;

/**
 * BeanUtilsクラス
 * @author
 */
public class BeanUtilsImpl extends org.springframework.beans.BeanUtils {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(BeanUtilsImpl.class);

    /**
     * ターゲットクラスをインスタンス化し、与えられたソースBeanのプロパティ値をコピーして返却します。
     * @param source
     * @param target
     * @param <T>
     * @return
     */
    public static <T> T createCopyProperties(Object source,
                                             Class<T> target) {
        try {
            T cls = target.newInstance();
            copyProperties(source, cls);
            return cls;
        } catch (InstantiationException | IllegalAccessException e) {
            logger.warn("bean copy fails.");
            throw new BeanInstantiationException(target, null, e);
        }
    }

}
