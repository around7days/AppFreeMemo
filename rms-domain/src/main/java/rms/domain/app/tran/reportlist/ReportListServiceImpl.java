package rms.domain.app.tran.reportlist;

import java.util.List;

import org.seasar.doma.jdbc.SelectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rms.common.utils.PageInfo;
import rms.common.utils.SearchResultDto;
import rms.common.utils.SelectOptionsUtils;

/**
 * 月報一覧画面サービス
 * @author
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportListServiceImpl implements ReportListService {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ReportListServiceImpl.class);

    /** 月報情報取得Dao */
    @Autowired
    ReportListDao dao;

    @Override
    public SearchResultDto<ReportListResultEntity> search(ReportListDto condition,
                                                          PageInfo pageInfo) {
        // ページ情報の生成
        SelectOptions options = SelectOptionsUtils.get(pageInfo);

        // 検索処理
        List<ReportListResultEntity> resultList = dao.reportListByCondition(condition, options);

        // 検索結果格納
        SearchResultDto<ReportListResultEntity> resultDto = new SearchResultDto<>();
        resultDto.setResultList(resultList);
        resultDto.setCount(options.getCount());

        return resultDto;
    }

}
