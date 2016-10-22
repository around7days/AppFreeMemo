package rms.web.app.tran.reportapproveregistbulk;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import rms.common.validator.NotNullUploadFile;
import rms.domain.app.tran.reportapproveregistbulk.ReportApproveRegistBulkDto;

/**
 * 月報一括承認画面フォーム
 * @author
 */
public class ReportApproveRegistBulkForm extends rms.common.abstracts.AbstractForm {

    /** 承認者月報ファイル */
    @NotNullUploadFile(message = "月報：{NotNullUploadFile.message}")
    private MultipartFile file;
    /** 実行結果リスト */
    private List<ReportApproveRegistBulkDto> resultList;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public List<ReportApproveRegistBulkDto> getResultList() {
        return resultList;
    }

    public void setResultList(List<ReportApproveRegistBulkDto> resultList) {
        this.resultList = resultList;
    }

}
