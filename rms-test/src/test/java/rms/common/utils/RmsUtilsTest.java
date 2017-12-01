package rms.common.utils;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RmsUtilsTest {
    /** logger */
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(RmsUtilsTest.class);

    @Test
    public void test_getThisTargetYm() {

        int switchDay = 20;
        {
            LocalDate date = LocalDate.of(2017, 06, 19);
            Integer targetYm = RmsUtils.getThisTargetYm(date, switchDay);
            assertThat(targetYm, is(201705));
        }
        {
            LocalDate date = LocalDate.of(2017, 06, 20);
            Integer targetYm = RmsUtils.getThisTargetYm(date, switchDay);
            assertThat(targetYm, is(201706));
        }
        {
            LocalDate date = LocalDate.of(2017, 06, 21);
            Integer targetYm = RmsUtils.getThisTargetYm(date, switchDay);
            assertThat(targetYm, is(201706));
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
        {
            Integer targetYm = 201706;
            boolean b = RmsUtils.isTargetYmCheck(targetYm);
            assertThat(b, is(true));
        }
        {
            Integer targetYm = 20170601;
            boolean b = RmsUtils.isTargetYmCheck(targetYm);
            assertThat(b, is(false));
        }
        {
            Integer targetYm = 999901;
            boolean b = RmsUtils.isTargetYmCheck(targetYm);
            assertThat(b, is(false));
        }
        {
            Integer targetYm = 201799;
            boolean b = RmsUtils.isTargetYmCheck(targetYm);
            assertThat(b, is(false));
        }
    }

}
