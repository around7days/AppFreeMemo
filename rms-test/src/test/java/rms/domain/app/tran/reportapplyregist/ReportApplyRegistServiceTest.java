package rms.domain.app.tran.reportapplyregist;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import rms.SpringWebApplication;
import rms.common.consts.MCodeConst;
import rms.common.consts.MessageEnum;
import rms.common.dao.TReportDao;
import rms.common.dao.VTReportDao;
import rms.common.entity.TReport;
import rms.common.entity.VTReport;
import rms.common.exception.BusinessException;
import rms.testutil.mock.RmsMockFileType;
import rms.testutil.mock.RmsMockUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringWebApplication.class)
public class ReportApplyRegistServiceTest {

    @Autowired
    ReportApplyRegistService service;
    @Autowired
    ReportApplyRegistServiceImpl serviceImpl;
    @Autowired
    VTReportDao vTReportDao;

    @SpyBean
    TReportDao tReportDao;

    @Test
    public void test_initDisplayApply_月報申請_初期表示処理() {

        ReportApplyRegistDto dto = service.initDisplayApply("user01");

        assertThat(dto.getApplyUserId(), is("user01"));
        assertThat(dto.getApplyUserNm(), is("申請者０１"));
        // assertThat(dto.getTargetYm(), is(nullValue())); TODO システム日付を指定してテストする必要あり
        assertThat(dto.getFile(), is(nullValue()));
        assertThat(dto.getApproveUserId1(), is("user06"));
        assertThat(dto.getApproveUserId2(), is("user07"));
        assertThat(dto.getApproveUserId3(), is("user08"));
        assertThat(dto.getApproveUserNm1(), is("承認者０１"));
        assertThat(dto.getApproveUserNm2(), is("承認者０２"));
        assertThat(dto.getApproveUserNm3(), is("承認者０３"));
    }

    @Test
    public void test_initDisplayReApply_月報再申請_初期表示処理() {

        ReportApplyRegistDto dto = service.initDisplayReApply("user01", 201609);

        assertThat(dto.getApplyUserId(), is("user01"));
        assertThat(dto.getApplyUserNm(), is("申請者０１"));
        assertThat(dto.getTargetYm(), is(201609));
        assertThat(dto.getFile(), is(nullValue()));
        assertThat(dto.getApproveUserId1(), is("user06"));
        assertThat(dto.getApproveUserId2(), is("user07"));
        assertThat(dto.getApproveUserId3(), is("user08"));
        assertThat(dto.getApproveUserNm1(), is("承認者０１"));
        assertThat(dto.getApproveUserNm2(), is("承認者０２"));
        assertThat(dto.getApproveUserNm3(), is("承認者０３"));
    }

    @Test
    public void test_apply_月報申請_重複チェックエラー処理() throws Exception {

        String applyUserId = "user01";
        Integer targetYm = 201608;

        ReportApplyRegistDto dto = new ReportApplyRegistDto();
        dto.setApplyUserId(applyUserId);
        dto.setTargetYm(targetYm);
        try {
            service.apply(dto);
        } catch (BusinessException e) {
            assertThat(e.getErrorMessage(), is("対象年月の月報は既に申請されています"));
            return;
        }

        fail("エラーチェック失敗");
    }

    @Test
    public void test_apply_月報申請_未来日付チェックエラー_来月の月報を提出() throws Exception {

        LocalDate targetDate = LocalDate.now().plusMonths(1);

        String applyUserId = "user01";
        Integer targetYm = Integer.valueOf(targetDate.format(DateTimeFormatter.ofPattern("yyyyMM")));

        ReportApplyRegistDto dto = new ReportApplyRegistDto();
        dto.setApplyUserId(applyUserId);
        dto.setTargetYm(targetYm);
        try {
            service.apply(dto);
        } catch (BusinessException e) {
            assertThat(e.getErrorMessage(),
                       is(allOf(org.hamcrest.CoreMatchers.startsWith("対象年月の月報は"),
                                org.hamcrest.CoreMatchers.endsWith("以降から申請可能です"))));
            return;
        }

        fail("エラーチェック失敗");
    }

    @WithUserDetails(value = "user01", userDetailsServiceBeanName = "userDetailsServiceImpl")
    @Test
    public void test_apply_月報申請処理() throws Exception {

        String applyUserId = "user01";
        Integer targetYm = 201606;

        // 月報申請処理
        {
            ReportApplyRegistDto dto = new ReportApplyRegistDto();
            dto.setApplyUserId(applyUserId);
            dto.setTargetYm(targetYm);
            dto.setApproveUserId1("user06");
            dto.setApproveUserId2("user07");
            dto.setApproveUserId3("user08");
            MultipartFile file = RmsMockUtils.getDummyMultipartFile(RmsMockFileType.XLSX);
            dto.setFile(file);

            service.apply(dto);
        }

        // DB登録内容の確認
        {
            VTReport vTReport = vTReportDao.selectById(applyUserId, targetYm);
            assertThat(vTReport.getApplyUserId(), is(applyUserId));
            assertThat(vTReport.getTargetYm(), is(targetYm));
            assertThat(vTReport.getApplyDate().toLocalDate(), is(LocalDate.now()));
            assertThat(vTReport.getApproveUserId1(), is("user06"));
            assertThat(vTReport.getApproveUserId2(), is("user07"));
            assertThat(vTReport.getApproveUserId3(), is("user08"));
            assertThat(vTReport.getApproveDate1(), is(nullValue()));
            assertThat(vTReport.getApproveDate2(), is(nullValue()));
            assertThat(vTReport.getApproveDate3(), is(nullValue()));
            assertThat(vTReport.getStatus(), is("Y01"));
        }

        // TODO ファイル登録内容の確認は未実装
        // fail("ファイル確認は未実装");
    }

    @WithUserDetails(value = "user01", userDetailsServiceBeanName = "userDetailsServiceImpl")
    @Test
    public void test_reApply_月報再申請処理() throws Exception {
        String applyUserId = "user01";
        Integer targetYm = 201609;

        // 月報再申請処理
        {
            ReportApplyRegistDto dto = new ReportApplyRegistDto();
            dto.setApplyUserId(applyUserId);
            dto.setTargetYm(targetYm);
            dto.setApproveUserId1("user06");
            dto.setApproveUserId2("user07");
            dto.setApproveUserId3("user08");
            MultipartFile file = RmsMockUtils.getDummyMultipartFile(RmsMockFileType.XLSX);
            dto.setFile(file);
            dto.setVersion(0);
            service.reApply(dto);
        }

        // DB登録内容の確認
        {
            VTReport vTReport = vTReportDao.selectById(applyUserId, targetYm);
            assertThat(vTReport.getApplyUserId(), is(applyUserId));
            assertThat(vTReport.getTargetYm(), is(targetYm));
            assertThat(vTReport.getApplyDate().toLocalDate(), is(LocalDate.now()));
            assertThat(vTReport.getApproveUserId1(), is("user06"));
            assertThat(vTReport.getApproveUserId2(), is("user07"));
            assertThat(vTReport.getApproveUserId3(), is("user08"));
            assertThat(vTReport.getApproveDate1(), is(nullValue()));
            assertThat(vTReport.getApproveDate2(), is(nullValue()));
            assertThat(vTReport.getApproveDate3(), is(nullValue()));
            assertThat(vTReport.getStatus(), is("Y01"));
        }

        // TODO ファイル登録内容の確認は未実装
        // fail("ファイル確認は未実装");
    }

    @WithUserDetails(value = "user01", userDetailsServiceBeanName = "userDetailsServiceImpl")
    @Test
    public void test_validateUniquReport_月報の重複チェック() throws Exception {

        // Mock定義 ---------------------------------------------------
        TReport entity = new TReport();
        entity.setStatus(MCodeConst.A001_Y01);
        doReturn(entity).when(tReportDao).selectById(anyString(), anyInt());
        // -----------------------------------------------------------

        try {
            serviceImpl.validateUniquReport("dummy", 999999);
        } catch (BusinessException e) {
            assertThat(e.getErrorCode(), is(MessageEnum.error003.name()));
            return;
        }

        fail("失敗");
    }
}
