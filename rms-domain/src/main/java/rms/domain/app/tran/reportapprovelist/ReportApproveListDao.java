package rms.domain.app.tran.reportapprovelist;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SelectOptions;

/**
 * 月報情報取得Dao
 */
@Dao
@ConfigAutowireable
public interface ReportApproveListDao {

    /**
     * 月報情報一覧の取得（承認者用）
     * @param condition
     * @param options
     * @return
     */
    @Select
    List<ReportApproveListResultEntity> reportApproveListByCondition(ReportApproveListDto condition,
                                                                     SelectOptions options);

}