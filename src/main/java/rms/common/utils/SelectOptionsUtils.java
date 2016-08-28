package rms.common.utils;

import org.seasar.doma.jdbc.SelectOptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SelectOptionsUtilクラス
 * @author
 */
public class SelectOptionsUtils {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SelectOptionsUtils.class);

    /**
     * ページ情報からSelectOptionsを生成
     * @param pageInfo
     * @return
     */
    public static SelectOptions get(PageInfo pageInfo) {
        return get(pageInfo, true);
    }

    /**
     * ページ情報からSelectOptionsを生成
     * @param pageInfo
     * @param countFlg
     * @return
     */
    public static SelectOptions get(PageInfo pageInfo,
                                    boolean countFlg) {
        int offset = (pageInfo.getPage() - 1) * pageInfo.getLimit();
        int limit = pageInfo.getLimit();

        SelectOptions selectOptions;
        if (countFlg) {
            selectOptions = SelectOptions.get().offset(offset).limit(limit).count();
        } else {
            selectOptions = SelectOptions.get().offset(offset).limit(limit);
        }

        return selectOptions;
    }

}