package rms.testutil.mock;

/**
 * モックファイルタイプ定義
 * @author
 */
public enum RmsMockFileType {
    TXT("dummy/dummy.txt", "dummy.txt"), //
    XLSX("dummy/dummy.xlsx", "dummy.xlsx"), //
    ZIP("dummy/dummy.zip", "dummy.zip"), //
    ;

    private final String fileNm;
    private final String resourcePath;

    RmsMockFileType(String resourcePath, String fileNm) {
        this.resourcePath = resourcePath;
        this.fileNm = fileNm;
    }

    public String getResourcePath() {
        return this.resourcePath;
    }

    public String getFileNm() {
        return this.fileNm;
    }
}
