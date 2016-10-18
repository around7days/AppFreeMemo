package rms.junit.common;

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

        assertEquals(page.getPage(), 1);
        assertEquals(page.getTotalPage(), 10);
        assertEquals(page.getStartIndex(), 1);
        assertEquals(page.getEndIndex(), 5);
        assertEquals(page.getHasPrev(), false);
        assertEquals(page.getHasNext(), true);
    }

    @Test
    public void test2() {
        logger.debug("◆test2");
        PageInfo page = new PageInfo();
        page.setLimit(5);
        page.setPage(2);
        page.setTotalSize(50);

        printLog(page);

        assertEquals(page.getPage(), 2);
        assertEquals(page.getTotalPage(), 10);
        assertEquals(page.getStartIndex(), 6);
        assertEquals(page.getEndIndex(), 10);
        assertEquals(page.getHasPrev(), true);
        assertEquals(page.getHasNext(), true);
    }

    @Test
    public void test3() {
        logger.debug("◆test3");
        PageInfo page = new PageInfo();
        page.setLimit(5);
        page.setPage(10);
        page.setTotalSize(50);

        printLog(page);

        assertEquals(page.getPage(), 10);
        assertEquals(page.getTotalPage(), 10);
        assertEquals(page.getStartIndex(), 46);
        assertEquals(page.getEndIndex(), 50);
        assertEquals(page.getHasPrev(), true);
        assertEquals(page.getHasNext(), false);
    }

    @Test
    public void test4() {
        logger.debug("◆test4");
        PageInfo page = new PageInfo();
        page.setLimit(5);
        page.setPage(11);
        page.setTotalSize(50);

        printLog(page);

        assertEquals(page.getPage(), 10);
        assertEquals(page.getTotalPage(), 10);
        assertEquals(page.getStartIndex(), 46);
        assertEquals(page.getEndIndex(), 50);
        assertEquals(page.getHasPrev(), true);
        assertEquals(page.getHasNext(), false);
    }

    @Test
    public void test5() {
        logger.debug("◆test5");
        PageInfo page = new PageInfo();
        page.setLimit(5);
        page.setPage(11);
        page.setTotalSize(51);

        printLog(page);

        assertEquals(page.getPage(), 11);
        assertEquals(page.getTotalPage(), 11);
        assertEquals(page.getStartIndex(), 51);
        assertEquals(page.getEndIndex(), 51);
        assertEquals(page.getHasPrev(), true);
        assertEquals(page.getHasNext(), false);
    }

    @Test
    public void test6() {
        logger.debug("◆test6");
        PageInfo page = new PageInfo();
        page.setLimit(5);
        page.setPage(2);
        page.setTotalSize(0);

        printLog(page);

        assertEquals(page.getPage(), 1);
        assertEquals(page.getTotalPage(), 0);
        assertEquals(page.getStartIndex(), 0);
        assertEquals(page.getEndIndex(), 0);
        assertEquals(page.getHasPrev(), false);
        assertEquals(page.getHasNext(), false);
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
