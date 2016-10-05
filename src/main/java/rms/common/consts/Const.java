package rms.common.consts;

/**
 * 定数定義
 * @author
 */
public class Const {

    /*
     * フラグON/OFF
     */
    public static String FLG_ON = "1";
    public static String FLG_OFF = "0";

    /*
     * 承認フロー
     */
    /** 承認フロー：承認者1 */
    public static final Integer APPROVE_FLOW_SEQ_1 = 1;
    /** 承認フロー：承認者2 */
    public static final Integer APPROVE_FLOW_SEQ_2 = 2;
    /** 承認フロー：承認者3 */
    public static final Integer APPROVE_FLOW_SEQ_3 = 3;

    /*
     * 月報ファイル区切り文字
     */
    public static final String REPORT_FILE_DELIMITER = "_";

    /*
     * 月報一括承認実施結果
     */
    /** 月報一括承認実施結果：成功 */
    public static final String REPORT_BULK_APPROVE_RESULT_OK = "成功";
    /** 月報一括承認実施結果：失敗 */
    public static final String REPORT_BULK_APPROVE_RESULT_NG = "エラー";

}
