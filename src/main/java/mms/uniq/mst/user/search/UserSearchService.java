package mms.uniq.mst.user.search;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.seasar.doma.jdbc.SelectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mms.com.doma.entity.MUser;
import mms.com.page.PageInfo;
import mms.com.utils.SelectOptionsUtil;

/**
 * ユーザ一覧画面サービス
 * @author
 */
@Service
public class UserSearchService extends mms.com.abstracts.AbstractService {
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(UserSearchService.class);

    /** ユーザ一覧画面Dao */
    @Autowired
    UserSearchDao userSearchDao;

    /**
     * 検索処理
     * @param form
     */
    public void search(UserSearchForm form) {
        // ページング設定
        PageInfo pageInfo = form.getPageInfo();
        SelectOptions options = SelectOptionsUtil.get(pageInfo);

        // 検索処理
        List<MUser> result = userSearchDao.searchUser(form, options);
        logger.debug("検索結果(全件)：{}件", options.getCount());
        logger.debug("検索結果：{}件", result.size());
        result.forEach(obj -> logger.debug(ToStringBuilder.reflectionToString(obj)));

        // 検索結果格納
        pageInfo.setTotalSize(options.getCount());
        form.setResultList(result);
    }
}
