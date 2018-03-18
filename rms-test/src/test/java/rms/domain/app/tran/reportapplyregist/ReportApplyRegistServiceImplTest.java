package rms.domain.app.tran.reportapplyregist;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

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
import rms.common.consts.MCodeConst;
import rms.common.consts.MessageEnum;
import rms.common.dao.TReportDao;
import rms.common.dao.VTReportDao;
import rms.common.entity.TReport;
import rms.common.exception.BusinessException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWebApplication.class, properties = "spring.profiles.active=test")
public class ReportApplyRegistServiceImplTest {

    /* テスト対象 */
    @Autowired
    ReportApplyRegistServiceImpl serviceImpl;

    /* Mock対象Dao */
    @MockBean
    TReportDao tReportDao;
    @MockBean
    VTReportDao vTReportDao;

    @BeforeClass
    public static void beforeAll() {
        SpringApplication application = new SpringApplication(SpringBatchApplication.class);
        application.setWebEnvironment(false); // 内臓tomcatの起動を抑制
    }

    @Test
    public void test_validateUniquReport_月報の重複チェック_正常() throws Exception {

        // Mock定義 ---------------------------------------------------
        doReturn(null).when(tReportDao).selectById(any(), any());
        // -----------------------------------------------------------

        // テスト実行
        serviceImpl.validateUniquReport("dummy", 999912);

        // テスト結果確認（到達確認）
        assertTrue("正常終了", true);
    }

    @Test
    public void test_validateUniquReport_月報の重複チェック_重複エラー() throws Exception {

        // Mock定義 ---------------------------------------------------
        TReport entity = new TReport();
        entity.setStatus(MCodeConst.A001_Y01);
        doReturn(entity).when(tReportDao).selectById(any(), any());
        // -----------------------------------------------------------

        try {
            // テスト実行
            serviceImpl.validateUniquReport("dummy", 999912);
        } catch (BusinessException e) {
            // テスト結果確認
            assertThat(e.getErrorCode(), is(MessageEnum.error003.name()));
            return;
        }

        fail("失敗");
    }

    @Test
    public void test_validateFutureYm_月報の未来日付チェック_正常() throws Exception {

        // テスト実行
        serviceImpl.validateFutureYm(200001);

        // テスト結果確認（到達確認）
        assertTrue("正常終了", true);
    }

    @Test
    public void test_insertReport_月報申請処理() throws Exception {

        // Mock定義 ---------------------------------------------------
        doReturn(1).when(tReportDao).insert(any());
        // -----------------------------------------------------------

        // テストデータ
        ReportApplyRegistDto dto = new ReportApplyRegistDto();
        dto.setApplyUserId("ユーザID");
        dto.setTargetYm(999912);

        // テスト実行
        serviceImpl.insertReport(dto);

        // テスト結果確認（到達確認）
        assertTrue("正常終了", true);

        // TODO メソッドを呼び出した引数のチェックを行う方法が分からない・・・
        // // テスト結果確認（呼び出し確認）
        // TReport entity = new TReport();
        // entity.setApplyUserId("ユーザID");
        // entity.setTargetYm(999912);
        // entity.setFilePath("");
        // entity.setStatus(MCodeConst.A001_AAA);
        // verify(tReportDao).insert(entity);
    }
}
