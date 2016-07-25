/**
 *
 */
package rms.junit;

import static org.junit.Assert.*;

import rms.web.com.utils.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Test;

/**
 * @author 9EAFP
 */
public class PageInfoTest {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(PageInfoTest.class);

    @Test
    public void indexTest1() {
        logger.debug("◆indexTest1");
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
    public void indexTest2() {
        logger.debug("◆indexTest2");
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
    public void indexTest3() {
        logger.debug("◆indexTest3");
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
    public void indexTest4() {
        logger.debug("◆indexTest4");
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
    public void indexTest5() {
        logger.debug("◆indexTest5");
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
    public void indexTest6() {
        logger.debug("◆indexTest6");
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
        logger.debug("page.getPage()       : {}", page.getPage());
        logger.debug("page.getTotalPage()  : {}", page.getTotalPage());
        logger.debug("page.getStartIndex() : {}", page.getStartIndex());
        logger.debug("page.getEndIndex()   : {}", page.getEndIndex());
        logger.debug("page.getHasPrev()    : {}", page.getHasPrev());
        logger.debug("page.getHasNext()    : {}", page.getHasNext());
    }

}
