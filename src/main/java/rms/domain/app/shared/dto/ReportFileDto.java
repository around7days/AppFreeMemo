package rms.domain.app.shared.dto;

import java.nio.file.Path;

/**
 * 月報ファイル情報格納クラス<br>
 * @author
 */
public class ReportFileDto {

    /** 検索結果一覧 */
    private Path filePath;

    /** 検索結果件数（総件数） */
    private String fileNm;

    /**
     * 検索結果一覧を取得します。
     * @return 検索結果一覧
     */
    public Path getFilePath() {
        return filePath;
    }

    /**
     * 検索結果一覧を設定します。
     * @param filePath 検索結果一覧
     */
    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * 検索結果件数（総件数）を取得します。
     * @return 検索結果件数（総件数）
     */
    public String getFileNm() {
        return fileNm;
    }

    /**
     * 検索結果件数（総件数）を設定します。
     * @param fileNm 検索結果件数（総件数）
     */
    public void setFileNm(String fileNm) {
        this.fileNm = fileNm;
    }
}
