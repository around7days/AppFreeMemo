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
     * 承認状況（承認者視点）
     */
    /** 承認状況（承認者視点）：申請待ち */
    public static final String APPROVE_REPORT_STATUS_APPLY_WAIT = "1";
    /** 承認状況（承認者視点）：入力中 */
    public static final String APPROVE_REPORT_STATUS_TURN = "2";
    /** 承認状況（承認者視点）：承認済み */
    public static final String APPROVE_REPORT_STATUS_COMPLETE = "3";

    /*
     * 承認フロー
     */
    /** 承認フロー：承認者1 */
    public static final Integer APPROVE_FLOW_SEQ_1 = 1;
    /** 承認フロー：承認者2 */
    public static final Integer APPROVE_FLOW_SEQ_2 = 2;
    /** 承認フロー：承認者3 */
    public static final Integer APPROVE_FLOW_SEQ_3 = 3;

}
