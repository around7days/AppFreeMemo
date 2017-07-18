package rms.common.consts;

/**
 * 定数定義
 * @author
 */
public class Const {

    /*
     * フラグON/OFF
     */
    /** フラグON */
    public static String FLG_ON = "1";
    /** フラグOFF */
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
    /** 承認フロー：承認者4 */
    public static final Integer APPROVE_FLOW_SEQ_4 = 4;

    /** 月報ファイル区切り文字 */
    public static final String REPORT_FILE_DELIMITER = "_";

    /** システムユーザーID */
    public static final String SYSTEM_USER_ID = "system";

    /**
     * 承認状況処理区分
     */
    public enum StatusExecKbn {
        /** 申請 */
        APPLY,
        /** 承認 */
        APPROVE,
        /** 否認 */
        DENY
    }

    /**
     * 月報ファイル名パターン
     */
    public enum ReportNmPattern {
        /** 通常 */
        NOMAL,
        /** 提出用 */
        SUBMIT
    }

}
