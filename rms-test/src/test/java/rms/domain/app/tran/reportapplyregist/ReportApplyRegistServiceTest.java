package rms.domain.app.tran.reportapplyregist;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import rms.SpringBatchApplication;
import rms.SpringWebApplication;
import rms.common.dao.VMUserDao;
import rms.common.dao.VTReportDao;
import rms.common.entity.VMUser;
import rms.common.entity.VTReport;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWebApplication.class, properties = "spring.profiles.active=test")
public class ReportApplyRegistServiceTest {

    /* テスト対象 */
    @Autowired
    ReportApplyRegistService service;

    /* Mock対象Service */
    @SpyBean
    ReportApplyRegistServiceImpl serviceImpl;

    /* Mock対象Dao */
    @MockBean
    VMUserDao vMUserDao;
    @MockBean
    VTReportDao vTReportDao;

    @BeforeClass
    public static void beforeAll() {
        SpringApplication application = new SpringApplication(SpringBatchApplication.class);
        application.setWebEnvironment(false); // 内臓tomcatの起動を抑制
    }

    @Test
    public void test_initDisplayApply_月報申請_初期表示処理() {

        // Mock定義 ---------------------------------------------------
        VMUser mockEntity = new VMUser();
        mockEntity.setUserId("ユーザID");
        mockEntity.setUserNm("ユーザ名");
        mockEntity.setApproveUserId1("承認者ID1");
        mockEntity.setApproveUserId2("承認者ID2");
        mockEntity.setApproveUserId3("承認者ID3");
        mockEntity.setApproveUserId4("承認者ID4");
        mockEntity.setApproveUserNm1("承認者名1");
        mockEntity.setApproveUserNm2("承認者名2");
        mockEntity.setApproveUserNm3("承認者名3");
        mockEntity.setApproveUserNm4("承認者名4");
        doReturn(mockEntity).when(vMUserDao).selectById(anyObject());
        // -----------------------------------------------------------

        // テスト実行
        ReportApplyRegistDto dto = service.initDisplayApply("user01");

        // テスト結果確認
        assertThat(dto.getApplyUserId(), is("ユーザID"));
        assertThat(dto.getApplyUserNm(), is("ユーザ名"));
        assertThat(dto.getApproveUserId1(), is("承認者ID1"));
        assertThat(dto.getApproveUserId2(), is("承認者ID2"));
        assertThat(dto.getApproveUserId3(), is("承認者ID3"));
        assertThat(dto.getApproveUserId4(), is("承認者ID4"));
        assertThat(dto.getApproveUserNm1(), is("承認者名1"));
        assertThat(dto.getApproveUserNm2(), is("承認者名2"));
        assertThat(dto.getApproveUserNm3(), is("承認者名3"));
        assertThat(dto.getApproveUserNm4(), is("承認者名4"));
        assertThat(dto.getTargetYm(), is(201706));
        assertThat(dto.getFile(), is(nullValue()));
    }

    @Test
    public void test_initDisplayReApply_月報再申請_初期表示処理() {

        // Mock定義 ---------------------------------------------------
        VTReport mockEntity = new VTReport();
        mockEntity.setApplyUserId("ユーザID");
        mockEntity.setApplyUserNm("ユーザ名");
        mockEntity.setApproveUserId1("承認者ID1");
        mockEntity.setApproveUserId2("承認者ID2");
        mockEntity.setApproveUserId3("承認者ID3");
        mockEntity.setApproveUserId4("承認者ID4");
        mockEntity.setApproveUserNm1("承認者名1");
        mockEntity.setApproveUserNm2("承認者名2");
        mockEntity.setApproveUserNm3("承認者名3");
        mockEntity.setApproveUserNm4("承認者名4");
        doReturn(mockEntity).when(vTReportDao).selectById(anyObject(), anyObject());
        // -----------------------------------------------------------

        // テスト実行
        ReportApplyRegistDto dto = service.initDisplayReApply("dummy", 999912);

        // テスト結果確認
        assertThat(dto.getApplyUserId(), is("ユーザID"));
        assertThat(dto.getApplyUserNm(), is("ユーザ名"));
        assertThat(dto.getApproveUserId1(), is("承認者ID1"));
        assertThat(dto.getApproveUserId2(), is("承認者ID2"));
        assertThat(dto.getApproveUserId3(), is("承認者ID3"));
        assertThat(dto.getApproveUserId4(), is("承認者ID4"));
        assertThat(dto.getApproveUserNm1(), is("承認者名1"));
        assertThat(dto.getApproveUserNm2(), is("承認者名2"));
        assertThat(dto.getApproveUserNm3(), is("承認者名3"));
        assertThat(dto.getApproveUserNm4(), is("承認者名4"));
    }

    @Test
    public void test_apply_月報申請処理() throws Exception {

        // Mock定義 ---------------------------------------------------
        doNothing().when(serviceImpl).validateUniquReport(anyObject(), anyObject());
        doNothing().when(serviceImpl).validateFutureYm(anyObject());
        doNothing().when(serviceImpl).deleteReport(anyObject());
        doNothing().when(serviceImpl).deleteReportApproveFlow(anyObject());
        doNothing().when(serviceImpl).insertReport(anyObject());
        doNothing().when(serviceImpl).insertReportApproveFlow(anyObject());
        doNothing().when(serviceImpl).updateReportStatus(anyObject());
        doNothing().when(serviceImpl).saveReportFile(anyObject());
        // -----------------------------------------------------------

        // テスト実行
        service.apply(new ReportApplyRegistDto());

        // テスト結果確認（到達確認）
        assertTrue("正常終了", true);
    }

    @Test
    public void test_reApply_月報再申請処理() throws Exception {

        // Mock定義 ---------------------------------------------------
        doNothing().when(serviceImpl).deleteReport(anyObject());
        doNothing().when(serviceImpl).deleteReportApproveFlow(anyObject());
        doNothing().when(serviceImpl).insertReport(anyObject());
        doNothing().when(serviceImpl).insertReportApproveFlow(anyObject());
        doNothing().when(serviceImpl).updateReportStatus(anyObject());
        doNothing().when(serviceImpl).saveReportFile(anyObject());
        // -----------------------------------------------------------

        // テスト実行
        service.reApply(new ReportApplyRegistDto());

        // テスト結果確認（到達確認）
        assertTrue("正常終了", true);
    }

}
