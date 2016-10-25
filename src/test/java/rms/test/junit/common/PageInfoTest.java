package rms.test.junit.common;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rms.common.utils.PageInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PageInfoTest {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(PageInfoTest.class);

    @Test
    public void test1() {
        logger.debug("◆test1");
        PageInfo page = new PageInfo();
        page.setLimit(5);
        page.setPage(1);
        page.setTotalSize(50);

        printLog(page);

        assertThat(page.getPage(), is(1));
        assertThat(page.getTotalPage(), is(10));
        assertThat(page.getStartIndex(), is(1));
        assertThat(page.getEndIndex(), is(5));
        assertThat(page.getHasPrev(), is(false));
        assertThat(page.getHasNext(), is(true));
    }

    @Test
    public void test2() {
        logger.debug("◆test2");
        PageInfo page = new PageInfo();
        page.setLimit(5);
        page.setPage(2);
        page.setTotalSize(50);

        printLog(page);

        assertThat(page.getPage(), is(2));
        assertThat(page.getTotalPage(), is(10));
        assertThat(page.getStartIndex(), is(6));
        assertThat(page.getEndIndex(), is(10));
        assertThat(page.getHasPrev(), is(true));
        assertThat(page.getHasNext(), is(true));
    }

    @Test
    public void test3() {
        logger.debug("◆test3");
        PageInfo page = new PageInfo();
        page.setLimit(5);
        page.setPage(10);
        page.setTotalSize(50);

        printLog(page);

        assertThat(page.getPage(), is(10));
        assertThat(page.getTotalPage(), is(10));
        assertThat(page.getStartIndex(), is(46));
        assertThat(page.getEndIndex(), is(50));
        assertThat(page.getHasPrev(), is(true));
        assertThat(page.getHasNext(), is(false));
    }

    @Test
    public void test4() {
        logger.debug("◆test4");
        PageInfo page = new PageInfo();
        page.setLimit(5);
        page.setPage(11);
        page.setTotalSize(50);

        printLog(page);

        assertThat(page.getPage(), is(10));
        assertThat(page.getTotalPage(), is(10));
        assertThat(page.getStartIndex(), is(46));
        assertThat(page.getEndIndex(), is(50));
        assertThat(page.getHasPrev(), is(true));
        assertThat(page.getHasNext(), is(false));
    }

    @Test
    public void test5() {
        logger.debug("◆test5");
        PageInfo page = new PageInfo();
        page.setLimit(5);
        page.setPage(11);
        page.setTotalSize(51);

        printLog(page);

        assertThat(page.getPage(), is(11));
        assertThat(page.getTotalPage(), is(11));
        assertThat(page.getStartIndex(), is(51));
        assertThat(page.getEndIndex(), is(51));
        assertThat(page.getHasPrev(), is(true));
        assertThat(page.getHasNext(), is(false));
    }

    @Test
    public void test6() {
        logger.debug("◆test6");
        PageInfo page = new PageInfo();
        page.setLimit(5);
        page.setPage(2);
        page.setTotalSize(0);

        printLog(page);

        assertThat(page.getPage(), is(1));
        assertThat(page.getTotalPage(), is(0));
        assertThat(page.getStartIndex(), is(0));
        assertThat(page.getEndIndex(), is(0));
        assertThat(page.getHasPrev(), is(false));
        assertThat(page.getHasNext(), is(false));
    }

    private void printLog(PageInfo page) {
        logger.debug("page.getPage()       -> {}", page.getPage());
        logger.debug("page.getTotalPage()  -> {}", page.getTotalPage());
        logger.debug("page.getStartIndex() -> {}", page.getStartIndex());
        logger.debug("page.getEndIndex()   -> {}", page.getEndIndex());
        logger.debug("page.getHasPrev()    -> {}", page.getHasPrev());
        logger.debug("page.getHasNext()    -> {}", page.getHasNext());
    }

}
