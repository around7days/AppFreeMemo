package rms.test.junit.domain;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import rms.common.dao.VTReportDao;
import rms.common.entity.VTReport;
import rms.domain.app.tran.reportapplyregist.ReportApplyRegistDto;
import rms.domain.app.tran.reportapplyregist.ReportApplyRegistService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportApplyRegistServiceTest {

    @Autowired
    ReportApplyRegistService service;

    @Autowired
    VTReportDao vTReportDao;

    @Test
    public void 月報初期表示処理_新規() {

        ReportApplyRegistDto dto = service.getInitInsertReportInfo("user01");

        assertThat(dto.getApplyUserId(), is("user01"));
        assertThat(dto.getApplyUserNm(), is("申請者０１"));
        assertThat(dto.getTargetYm(), is(nullValue()));
        assertThat(dto.getFile(), is(nullValue()));
        assertThat(dto.getPublishFlg(), is("1"));
        assertThat(dto.getApproveUserId1(), is("user06"));
        assertThat(dto.getApproveUserId2(), is("user07"));
        assertThat(dto.getApproveUserId3(), is("user08"));
        assertThat(dto.getApproveUserNm1(), is("承認者０１"));
        assertThat(dto.getApproveUserNm2(), is("承認者０２"));
        assertThat(dto.getApproveUserNm3(), is("承認者０３"));
    }

    @Test
    public void 月報初期表示処理_更新() {

        ReportApplyRegistDto dto = service.getInitUpdateReportInfo("user01", 201609);

        assertThat(dto.getApplyUserId(), is("user01"));
        assertThat(dto.getApplyUserNm(), is("申請者０１"));
        assertThat(dto.getTargetYm(), is(201609));
        assertThat(dto.getFile(), is(nullValue()));
        assertThat(dto.getPublishFlg(), is("1"));
        assertThat(dto.getApproveUserId1(), is("user06"));
        assertThat(dto.getApproveUserId2(), is("user07"));
        assertThat(dto.getApproveUserId3(), is("user08"));
        assertThat(dto.getApproveUserNm1(), is("承認者０１"));
        assertThat(dto.getApproveUserNm2(), is("承認者０２"));
        assertThat(dto.getApproveUserNm3(), is("承認者０３"));
    }

    @WithUserDetails(value = "user01", userDetailsServiceBeanName = "userDetailsServiceImpl")
    @Test
    public void 月報登録処理_新規() throws Exception {

        String applyUserId = "user01";
        Integer targetYm = 201606;

        // 月報登録処理
        {
            ReportApplyRegistDto dto = new ReportApplyRegistDto();
            dto.setApplyUserId(applyUserId);
            dto.setTargetYm(targetYm);
            dto.setApproveUserId1("user06");
            dto.setApproveUserId2("user07");
            dto.setApproveUserId3("user08");
            dto.setPublishFlg("0");
            MultipartFile file = createMockMultipartFile();
            dto.setFile(file);
            service.apply(dto);
        }

        // DB登録内容の確認
        {
            VTReport vTReport = vTReportDao.selectById(applyUserId, targetYm);
            assertThat(vTReport.getApplyUserId(), is(applyUserId));
            assertThat(vTReport.getTargetYm(), is(targetYm));
            assertThat(vTReport.getApplyDate().toLocalDate(), is(LocalDate.now()));
            assertThat(vTReport.getPublishFlg(), is("0"));
            assertThat(vTReport.getApproveUserId1(), is("user06"));
            assertThat(vTReport.getApproveUserId2(), is("user07"));
            assertThat(vTReport.getApproveUserId3(), is("user08"));
            assertThat(vTReport.getApproveDate1(), is(nullValue()));
            assertThat(vTReport.getApproveDate2(), is(nullValue()));
            assertThat(vTReport.getApproveDate3(), is(nullValue()));
            assertThat(vTReport.getStatus(), is("Y01"));
        }

        // ファイル登録内容の確認
        // TODO ファイル確認は保留で・・・
    }

    /**
     * MultipartFileのモック作成
     * @return
     * @throws IOException
     */
    private MultipartFile createMockMultipartFile() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResource("dummy/dummy_report_apply.xlsx").openStream();
        MultipartFile file = new MockMultipartFile("dummmy_report", stream);
        return file;
    }

}
