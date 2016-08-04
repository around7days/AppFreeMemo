package rms.web.com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;

/**
 * ファイルUtilsクラス
 * @author
 */
public class FileUtils {
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 月報ファイルパスの生成
     * @param storageDir
     * @param applicantId
     * @param targetYm
     * @return
     */
    public static Path createReportFilePath(String storageDir,
                                            String applicantId,
                                            Integer targetYm) {
        return createReportFilePath(storageDir, applicantId, String.valueOf(targetYm));
    }

    /**
     * 月報ファイルパスの生成
     * @param storageDir
     * @param applicantId
     * @param targetYm
     * @return
     */
    public static Path createReportFilePath(String storageDir,
                                            String applicantId,
                                            String targetYm) {
        String filePath = targetYm + "_" + applicantId + ".xlsx";
        return Paths.get(storageDir, filePath);

    }

    /**
     * 月報ダウンロード
     * @param response
     * @param filePath
     * @throws IOException
     */
    public static void reportDownload(HttpServletResponse response,
                                      Path filePath) throws IOException {
        // 月報ファイルパスの生成
        String encodeFileNm = URLEncoder.encode(filePath.toFile().getName(), StandardCharsets.UTF_8.name());
        logger.debug("月報ファイルダウンロード -> {}", filePath.toAbsolutePath().normalize());

        // ヘッダ設定
        response.addHeader("Content-Disposition", "attachment; filename*=UTF-8''" + encodeFileNm);

        // ファイル出力
        Files.copy(filePath, response.getOutputStream());
    }

    /**
     * 月報ファイル保存
     * @param inputStream
     * @param filePath
     * @throws IOException
     */
    public static void reportSave(InputStream inputStream,
                                  Path filePath) throws IOException {
        // ファイルの保存先パスを生成
        logger.debug("月報ファイル保存 -> {}", filePath.toAbsolutePath().normalize());

        // ファイル保存
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    }

}
