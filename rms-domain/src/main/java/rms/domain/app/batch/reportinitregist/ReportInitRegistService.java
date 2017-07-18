package rms.domain.app.batch.reportinitregist;

/**
 * 月報初期データ登録バッチサービス
 * @author
 */
public interface ReportInitRegistService {

    /**
     * 月報初期データ登録処理<br>
     * 指定した年月の月報データを未提出状態で作成する
     * @param targetYm 対象年月
     * @return 結果 [true:成功 false:失敗]
     */
    public boolean regist(Integer targetYm);
}
