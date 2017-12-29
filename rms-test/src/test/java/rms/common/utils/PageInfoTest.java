package rms.common.utils;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rms.SpringWebApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWebApplication.class, properties = "spring.profiles.active=test")
public class PageInfoTest {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(PageInfoTest.class);

    @DataPoints
    Fixture[] fixture = {
          // @formatter:off
          // ---------No | limit | page | totalSize /// page | totalPage | sIdx | eIdx | hasPrev | hasNext
          new Fixture(1,   5,     1,      50,           1,     10,          1,     5,    false,    true),  // 先頭ページ
          new Fixture(2,   5,     2,      50,           2,     10,          6,     10,   true,     true),  // 中間ページ
          new Fixture(3,   5,     10,     50,           10,    10,          46,    50,   true,     false), // 最終ページ
          new Fixture(4,   5,     11,     50,           10,    10,          46,    50,   true,     false), // 最終ページ以上(割り切れる件数)
          new Fixture(5,   5,     11,     51,           11,    11,          51,    51,   true,     false), // 最終ページ以上(割り切れない件数)
          new Fixture(6,   5,     2,      0,            1,     0,           0,     0,    false,    false), // 0件
          // @formatter:on
    };

    // TODO Theoriesを使いたいけどSpringBootでの実行方法が分からず
    @Test
    public void test_ページ制御処理() {
        for (Fixture f : fixture) {
            logger.debug("◆test" + f.no);
            PageInfo page = new PageInfo(f.limit);
            page.setPage(f.page);
            page.setTotalSize(f.totalSize);

            printLog(page);

            assertThat(page.getPage(), is(f.expectedPage));
            assertThat(page.getTotalPage(), is(f.expectedTotalPage));
            assertThat(page.getStartIndex(), is(f.expectedStartIndex));
            assertThat(page.getEndIndex(), is(f.expectedEndIndex));
            assertThat(page.getHasPrev(), is(f.expectedHasPrev));
            assertThat(page.getHasNext(), is(f.expectedHasNext));
        }
    }

    private void printLog(PageInfo page) {
        logger.debug("page.getPage() -> {}", page.getPage());
        logger.debug("page.getTotalPage() -> {}", page.getTotalPage());
        logger.debug("page.getStartIndex() -> {}", page.getStartIndex());
        logger.debug("page.getEndIndex() -> {}", page.getEndIndex());
        logger.debug("page.getHasPrev() -> {}", page.getHasPrev());
        logger.debug("page.getHasNext() -> {}", page.getHasNext());
    }

    class Fixture {
        public int no;

        public int limit;
        public int page;
        public int totalSize;

        public int expectedPage;
        public int expectedTotalPage;
        public int expectedStartIndex;
        public int expectedEndIndex;
        public boolean expectedHasPrev;
        public boolean expectedHasNext;

        Fixture(int no, int limit, int page, int totalSize, int expectedPage, int expectedTotalPage,
                int expectedStartIndex, int expectedEndIndex, boolean expectedHasPrev, boolean expectedHasNext) {
            this.no = no;
            this.limit = limit;
            this.page = page;
            this.totalSize = totalSize;
            this.expectedPage = expectedPage;
            this.expectedTotalPage = expectedTotalPage;
            this.expectedStartIndex = expectedStartIndex;
            this.expectedEndIndex = expectedEndIndex;
            this.expectedHasPrev = expectedHasPrev;
            this.expectedHasNext = expectedHasNext;
        }
    }

}
