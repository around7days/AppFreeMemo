package rms.domain.app.shared.dto;

import java.nio.file.Path;

/**
 * ファイル情報格納クラス<br>
 * @author
 */
public class SharedFileDto {

    /** ファイルパス */
    private Path filePath;

    /** ファイル名 */
    private String fileNm;

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public String getFileNm() {
        return fileNm;
    }

    public void setFileNm(String fileNm) {
        this.fileNm = fileNm;
    }

}
