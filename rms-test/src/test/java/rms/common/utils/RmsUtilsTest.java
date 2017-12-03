package rms.common.utils;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RmsUtilsTest {
    /** logger */
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(RmsUtilsTest.class);

    @Test
    public void test_getThisTargetYm() {

        /* テスト対象データ */
        // テストNo、月度切換日、年、月、日、望む結果
        //@formatter:off
        List<Integer[]> dataPoint = Arrays.asList(
             new Integer[] {1, 20, 2017, 06, 19, 201705}
            ,new Integer[] {2, 20, 2017, 06, 20, 201706}
            ,new Integer[] {3, 20, 2017, 06, 21, 201706}
            ,new Integer[] {4, 20, 2018, 01, 01, 201712}
        );
        //@formatter:on

        /* テスト実施 */
        for (Integer[] data : dataPoint) {
            logger.debug("TestNo:{}", data[0]);
            Integer switchDay = data[1];
            Integer yyyy = data[2];
            Integer mm = data[3];
            Integer dd = data[4];
            Integer result = data[5];

            LocalDate date = LocalDate.of(yyyy, mm, dd);
            Integer targetYm = RmsUtils.getThisTargetYm(date, switchDay);
            assertThat(targetYm, is(result));
        }
    }

    @Test
    public void test_getSwitchDate() {
        Integer targetYm = 201706;
        Integer switchDay = 20;
        LocalDate date = RmsUtils.getSwitchDate(targetYm, switchDay);
        assertThat(date.format(DateTimeFormatter.BASIC_ISO_DATE), is("20170620"));
    }

    @Test
    public void test_isTargetYmCheck() {

        /* テスト対象データ */
        // テストNo、対象年度、望む結果
        //@formatter:off
        List<Object[]> dataPoint = Arrays.asList(
             new Object[] {1, 201706, true}
            ,new Object[] {2, 20170601, false}
            ,new Object[] {3, 999901, false}
            ,new Object[] {4, 201799, false}
        );
        //@formatter:on

        /* テスト実施 */
        for (Object[] data : dataPoint) {
            logger.debug("TestNo:{}", data[0]);
            Integer targetYm = (Integer) data[1];
            boolean result = (boolean) data[2];

            boolean b = RmsUtils.isTargetYmCheck(targetYm);
            assertThat(b, is(result));
        }
    }

    @Test
    public final void test_formatTargetYm() {
        Integer targetYm = 201706;
        String val = RmsUtils.formatTargetYm(targetYm);
        assertThat(val, is("2017/06"));
    }

    @Test
    public final void test_getTargetYear() {
        Integer targetYm = 201706;
        String val = RmsUtils.getTargetYear(targetYm);
        assertThat(val, is("2017"));
    }

    @Test
    public final void test_getTargetMonth() {
        Integer targetYm = 201706;
        String val = RmsUtils.getTargetMonth(targetYm);
        assertThat(val, is("06"));
    }

}
