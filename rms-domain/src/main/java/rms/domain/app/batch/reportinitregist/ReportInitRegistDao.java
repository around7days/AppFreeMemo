package rms.domain.app.batch.reportinitregist;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import rms.common.entity.MUser;

/**
 * 月報初期データ登録Dao
 */
@Dao
@ConfigAutowireable
public interface ReportInitRegistDao {

    /**
     * 月報未提出ユーザ一覧の取得
     * @param targetYm
     * @return
     */
    @Select
    List<MUser> noReportUserListByTargetYm(Integer targetYm);
}