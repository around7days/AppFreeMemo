package rms.test.junit.domain;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.List;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rms.common.dto.SearchResultDto;
import rms.common.utils.PageInfo;
import rms.domain.app.tran.reportapplylist.ReportApplyListDtoCondition;
import rms.domain.app.tran.reportapplylist.ReportApplyListEntityResult;
import rms.domain.app.tran.reportapplylist.ReportApplyListService;

@RunWith(Enclosed.class)
public class ReportApplyListServiceTest {

    @RunWith(SpringRunner.class)
    @SpringBootTest
    public static class searchInterface {

        @Autowired
        ReportApplyListService service;

        @Test
        public void 申請状況検索処理() {

            String userId = "user01";
            Integer targetYm = 201609;

            // 検索
            ReportApplyListDtoCondition condition = new ReportApplyListDtoCondition();
            condition.setApplyUserId(userId);
            PageInfo pageInfo = new PageInfo();
            pageInfo.setLimit(Integer.MAX_VALUE);

            SearchResultDto<ReportApplyListEntityResult> resultDto = service.search(condition, pageInfo);

            // 検索結果件数の確認
            assertThat(resultDto.getCount(), is(Long.valueOf(3)));

            // 検索結果の確認
            List<ReportApplyListEntityResult> list = resultDto.getResultList();
            ReportApplyListEntityResult entity = list.stream()
                                                     .filter(e -> e.getTargetYm().intValue() == targetYm)
                                                     .findFirst()
                                                     .get();

            assertThat(entity.getApplyUserId(), is(userId));
            assertThat(entity.getApplyUserNm(), is("申請者０１"));
            assertThat(entity.getTargetYm(), is(targetYm));
            // assertThat(entity.getApplyDate(), is());
            // assertThat(entity.getPublishFlg(), is());
            // assertThat(entity.getPublishFlgNm(), is());
            // assertThat(entity.getStatus(), is());
            // assertThat(entity.getStatusNm(), is());
            // assertThat(entity.getApproveUserId1(), is());
            // assertThat(entity.getApproveUserId2(), is());
            // assertThat(entity.getApproveUserId3(), is());
            // assertThat(entity.getApproveUserNm1(), is());
            // assertThat(entity.getApproveUserNm2(), is());
            // assertThat(entity.getApproveUserNm3(), is());
        }
    }

}
