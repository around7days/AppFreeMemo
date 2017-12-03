package rms.domain.app.tran.reportlist;

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
public interface ReportListDao {

    /**
     * 月報情報一覧の取得
     * @param condition
     * @param options
     * @return
     */
    @Select
    List<ReportListResultEntity> reportListByCondition(ReportListDto condition,
                                                       SelectOptions options);

}