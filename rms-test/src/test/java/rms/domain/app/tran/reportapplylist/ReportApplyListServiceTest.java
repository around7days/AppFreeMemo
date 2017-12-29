package rms.domain.app.tran.reportapplylist;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import rms.SpringBatchApplication;
import rms.SpringWebApplication;
import rms.common.utils.PageInfo;
import rms.common.utils.SearchResultDto;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWebApplication.class, properties = "spring.profiles.active=test")
public class ReportApplyListServiceTest {

    /* テスト対象 */
    @Autowired
    ReportApplyListService service;

    /* Mock対象Dao */
    @MockBean
    ReportApplyListDao reportApplyListDao;

    @BeforeClass
    public static void beforeAll() {
        SpringApplication application = new SpringApplication(SpringBatchApplication.class);
        application.setWebEnvironment(false); // 内臓tomcatの起動を抑制
    }

    @Test
    public void test_search_申請状況検索処理() {

        // テスト実行
        // Mock定義 ---------------------------------------------------
        List<ReportApplyListResultEntity> mockResultList = new ArrayList<ReportApplyListResultEntity>();
        {
            ReportApplyListResultEntity mockEntity = new ReportApplyListResultEntity();
            mockEntity.setApplyUserId("ユーザID1");
            mockResultList.add(mockEntity);
        }
        {
            ReportApplyListResultEntity mockEntity = new ReportApplyListResultEntity();
            mockEntity.setApplyUserId("ユーザID2");
            mockResultList.add(mockEntity);
        }
        doReturn(mockResultList).when(reportApplyListDao).reportApplyListByCondition(anyObject(), anyObject());
        // ----------------------------------------------------------

        // テスト実行
        SearchResultDto<ReportApplyListResultEntity> resultDto = service.search(new ReportApplyListDto(),
                                                                                new PageInfo());

        // テスト結果確認
        assertThat(resultDto.getResultList().get(0).getApplyUserId(), is("ユーザID1"));
        assertThat(resultDto.getResultList().get(1).getApplyUserId(), is("ユーザID2"));
    }

}
