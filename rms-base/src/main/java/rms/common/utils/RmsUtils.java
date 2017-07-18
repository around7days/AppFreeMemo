package rms.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 月報管理システム特有Utilsクラス
 * @author
 */
public class RmsUtils {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(RmsUtils.class);

    /**
     * 現在日付から対象月度を取得
     * @param sysdate 現在日付
     * @param switchDay 月度切替基準日
     * @return targetYm 今月度
     */
    public static Integer getThisTargetYm(LocalDate sysdate,
                                          Integer switchDay) {
        // 対象年月フォーマット
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMM");

        // 月度切替基準日を生成
        LocalDate switchDate = LocalDate.of(sysdate.getYear(), sysdate.getMonth(), switchDay);
        if (sysdate.isAfter(switchDate) || sysdate.isEqual(switchDate)) {
            // 現在日付 >= 月度切替基準日の場合、当月を返却
            return Integer.valueOf(sysdate.format(dtf));
        } else {
            // 現在日付 < 月度切替基準日の場合、前月を返却
            return Integer.valueOf(sysdate.minusMonths(1).format(dtf));
        }
    }

    /**
     * 指定された年月の月度切替基準日付を取得
     * @param targetYm
     * @param switchDay
     * @return switchDate
     */
    public static LocalDate getSwitchDate(Integer targetYm,
                                          Integer switchDay) {
        int year = Integer.valueOf(targetYm.toString().substring(0, 4));
        int month = Integer.valueOf(targetYm.toString().substring(4, 6));
        return LocalDate.of(year, month, switchDay);
    }

    /**
     * 指定された年月の正当性を確認
     * @param targetYm
     * @return 結果 [true:正常 false:異常]
     */
    public static boolean isTargetYmCheck(Integer targetYm) {
        if (targetYm == null) {
            return false;
        }

        // 桁チェック
        if (targetYm.toString().length() != 6) {
            return false;
        }
        // 年チェック
        int year = Integer.valueOf(targetYm.toString().substring(0, 4));
        if (year <= 2000 || 3000 <= year) {
            return false;
        }
        // 月チェック
        int month = Integer.valueOf(targetYm.toString().substring(4, 6));
        if (month < 1 || 12 < month) {
            return false;
        }

        // 正常
        return true;
    }

    // /**
    // * 年月から年を取得
    // * @param targetYm
    // * @return year
    // */
    // public static int getYear(Integer targetYm) {
    // return Integer.valueOf(targetYm.toString().substring(0, 4));
    // }
    //
    // /**
    // * 年月から月を取得
    // * @param targetYm
    // * @return month
    // */
    // public static int getMonth(Integer targetYm) {
    // return Integer.valueOf(targetYm.toString().substring(4, 6));
    // }

}
