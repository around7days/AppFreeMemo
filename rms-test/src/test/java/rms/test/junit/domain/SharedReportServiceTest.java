package rms.test.junit.domain;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static rms.common.consts.Const.StatusExecKbn.*;
import static rms.common.consts.MCodeConst.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rms.common.consts.Const.StatusExecKbn;
import rms.common.entity.VTReport;
import rms.domain.app.shared.service.SharedReportServiceImpl;

@RunWith(Enclosed.class)
public class SharedReportServiceTest {

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(SharedReportServiceTest.class);

    @RunWith(SpringRunner.class)
    @SpringBootTest
    public static class searchInterface {

        @Autowired
        SharedReportServiceImpl service;

        @DataPoints
        Fixture[] fixture = { new Fixture(1, APPLY, A001_AAA, "xx", null, null, null, A001_Y01),
                              new Fixture(2, APPLY, A001_AAA, null, "xx", null, null, A001_Y02),
                              new Fixture(3, APPLY, A001_AAA, null, null, "xx", null, A001_Y03),
                              new Fixture(4, APPLY, A001_AAA, null, null, null, "xx", A001_Y04),
                              new Fixture(5, APPLY, A001_AAA, null, null, null, null, A001_ZZZ),
                              new Fixture(6, APPLY, A001_AAA, "xx", null, null, "xx", A001_Y01),
                              new Fixture(7, APPLY, A001_AAA, null, "xx", null, "xx", A001_Y02),
                              new Fixture(11, APPROVE, A001_Y01, null, "xx", null, null, A001_Y02),
                              new Fixture(12, APPROVE, A001_Y01, null, null, "xx", null, A001_Y03),
                              new Fixture(13, APPROVE, A001_Y01, null, null, null, "xx", A001_Y04),
                              new Fixture(14, APPROVE, A001_Y01, null, null, null, null, A001_ZZZ),
                              new Fixture(15, APPROVE, A001_Y01, null, null, "xx", "xx", A001_Y03),
                              new Fixture(21, APPROVE, A001_Y02, null, null, "xx", null, A001_Y03),
                              new Fixture(22, APPROVE, A001_Y02, null, null, null, "xx", A001_Y04),
                              new Fixture(23, APPROVE, A001_Y02, null, null, null, null, A001_ZZZ),
                              new Fixture(24, APPROVE, A001_Y02, null, null, "xx", "xx", A001_Y03),
                              new Fixture(31, APPROVE, A001_Y03, null, null, null, "xx", A001_Y04),
                              new Fixture(32, APPROVE, A001_Y03, null, null, null, null, A001_ZZZ),
                              new Fixture(41, APPROVE, A001_Y04, null, null, null, null, A001_ZZZ),
                              new Fixture(51, DENY, A001_Y01, null, null, null, null, A001_N01),
                              new Fixture(52, DENY, A001_Y02, null, null, null, null, A001_N02),
                              new Fixture(53, DENY, A001_Y03, null, null, null, null, A001_N03),
                              new Fixture(54, DENY, A001_Y04, null, null, null, null, A001_N04), };

        // TODO Theoriesを使いたいけどSpringBootでの実行方法が分からず
        @Test
        public void 承認状況計算() {
            for (Fixture f : fixture) {
                logger.debug("No:{}", f.no);

                VTReport entity = new VTReport();
                entity.setStatus(f.status);
                entity.setApproveUserId1(f.user1);
                entity.setApproveUserId2(f.user2);
                entity.setApproveUserId3(f.user3);
                entity.setApproveUserId4(f.user4);
                String newStatus = service.getNewStatus(entity, f.statusExecKbn);
                assertThat(newStatus, is(f.newStatus));
            }
        }

        class Fixture {

            public int no;
            public StatusExecKbn statusExecKbn;
            public String status;
            public String user1;
            public String user2;
            public String user3;
            public String user4;
            public String newStatus;

            Fixture(int no, StatusExecKbn statusExecKbn, //
                    String status, //
                    String user1, //
                    String user2, //
                    String user3, //
                    String user4, //
                    String newStatus) {
                this.no = no;
                this.statusExecKbn = statusExecKbn;
                this.status = status;
                this.user1 = user1;
                this.user2 = user2;
                this.user3 = user3;
                this.user4 = user4;
                this.newStatus = newStatus;
            }
        }
    }

}
