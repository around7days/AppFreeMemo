package rms.test.junit.domain;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.time.format.DateTimeFormatter;
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
        public void 申請状況検索処理_検索結果件数の確認() {

            // // 確認するデータ
            // final String userId = "user01";
            //
            // // 検索
            // ReportApplyListDtoCondition condition = new ReportApplyListDtoCondition();
            // condition.setApplyUserId(userId);
            // PageInfo pageInfo = new PageInfo();
            // pageInfo.setLimit(Integer.MAX_VALUE); // テスト用に件数を無制限に設定
            // SearchResultDto<ReportApplyListEntityResult> resultDto = service.search(condition, pageInfo);
            //
            // 検索結果件数の確認
            // assertThat(resultDto.getCount(), is(Long.valueOf(3)));
        }

        @Test
        public void 申請状況検索処理_結果明細の確認() {

            // 確認するデータ
            final String userId = "user01";
            final Integer targetYm = 201607;

            // 検索
            ReportApplyListDtoCondition condition = new ReportApplyListDtoCondition();
            condition.setApplyUserId(userId);
            PageInfo pageInfo = new PageInfo();
            pageInfo.setLimit(Integer.MAX_VALUE); // テスト用に件数を無制限に設定
            SearchResultDto<ReportApplyListEntityResult> resultDto = service.search(condition, pageInfo);

            // 検索結果件数の確認
            // assertThat(resultDto.getCount(), is(Long.valueOf(3)));

            // 検索結果の確認（targetYmの年月データを確認）
            List<ReportApplyListEntityResult> list = resultDto.getResultList();
            ReportApplyListEntityResult entity = list.stream()
                                                     .filter(e -> e.getTargetYm().intValue() == targetYm)
                                                     .findFirst()
                                                     .get();

            assertThat(entity.getApplyUserId(), is(userId));
            assertThat(entity.getApplyUserNm(), is("申請者０１"));
            assertThat(entity.getTargetYm(), is(targetYm));
            assertThat(entity.getApplyDate().format(DateTimeFormatter.BASIC_ISO_DATE), is("20160725"));
            assertThat(entity.getStatusNm(), is("承認済み"));
            assertThat(entity.getApproveUserNm1(), is("承認者０１"));
            assertThat(entity.getApproveUserNm2(), is("承認者０２"));
            assertThat(entity.getApproveUserNm3(), is("承認者０３"));
            assertThat(entity.getApproveUserNm4(), is("承認者０４"));
        }
    }

}
