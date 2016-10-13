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

    /**
     * 承認者月報ファイルを取得します。
     * @return 承認者月報ファイル
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * 承認者月報ファイルを設定します。
     * @param file 承認者月報ファイル
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    /**
     * 実行結果リストを取得します。
     * @return 実行結果リスト
     */
    public List<ReportApproveRegistBulkDto> getResultList() {
        return resultList;
    }

    /**
     * 実行結果リストを設定します。
     * @param resultList 実行結果リスト
     */
    public void setResultList(List<ReportApproveRegistBulkDto> resultList) {
        this.resultList = resultList;
    }

}
