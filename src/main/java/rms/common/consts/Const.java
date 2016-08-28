package rms.common.consts;

/**
 * コードマスタ定義
 * @author
 */
public class Const {

    /*
     * フラグON/OFF
     */
    public static String FLG_ON = "1";
    public static String FLG_OFF = "0";

    /*
     * 承認状況（承認者視点）：C001
     */
    /** 承認状況（承認者視点）：申請待ち */
    public static final String APPROVE_REPORT_STATUS_APPLY_WAIT = "1";
    /** 承認状況（承認者視点）：入力中 */
    public static final String APPROVE_REPORT_STATUS_TURN = "2";
    /** 承認状況（承認者視点）：承認済み */
    public static final String APPROVE_REPORT_STATUS_COMPLETE = "3";

}
