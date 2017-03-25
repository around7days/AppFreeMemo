package rms.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 月報管理システム特有Utilsクラス
 * @author
 */
public class RmsUtils extends org.springframework.beans.BeanUtils {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(RmsUtils.class);

    /**
     * 現在日付から対象年月を取得
     * @param targetDate
     * @param switchDay
     * @return targetYm
     */
    public static Integer getThisTargetYm(LocalDate targetDate,
                                          Integer switchDay) {
        // 対象年月フォーマット
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMM");

        // 月度切替基準日を生成
        LocalDate switchDate = LocalDate.of(targetDate.getYear(), targetDate.getMonth(), switchDay);
        if (targetDate.isAfter(switchDate)) {
            // 本日日付が月度切替基準日より後の場合、当月を返却
            return Integer.valueOf(targetDate.format(dtf));
        } else {
            // 本日日付が月度切替基準日より前の場合、前月を返却
            return Integer.valueOf(targetDate.minusMonths(1).format(dtf));
        }
    }

}
