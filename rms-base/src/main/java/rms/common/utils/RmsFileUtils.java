package rms.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ファイルUtilsクラス
 * @author
 */
public class RmsFileUtils {
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(RmsFileUtils.class);

    /**
     * ファイルダウンロード
     * @param response
     * @param filePath
     * @param fileNm
     * @throws IOException
     */
    public static void fileDownload(HttpServletResponse response,
                                    Path filePath,
                                    String fileNm) throws IOException {
        logger.info("ファイルダウンロード -> {}", filePath.toAbsolutePath().normalize());

        // ファイル名のエンコード
        String encodeFileNm = URLEncoder.encode(fileNm, StandardCharsets.UTF_8.name());

        // ヘッダ設定
        response.addHeader("Content-Disposition", "attachment; filename*=UTF-8''" + encodeFileNm);

        // ファイル出力
        Files.copy(filePath, response.getOutputStream());
    }

    /**
     * ファイル保存
     * @param inputStream
     * @param filePath
     * @throws IOException
     */
    public static void fileSave(InputStream inputStream,
                                Path filePath) throws IOException {
        logger.info("ファイル保存 -> {}", filePath.toAbsolutePath().normalize());

        // ファイル保存
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * ファイル保存
     * @param fromFilePath
     * @param toFilePath
     * @throws IOException
     */
    public static void fileSave(Path fromFilePath,
                                Path toFilePath) throws IOException {
        logger.info("ファイル保存 -> {}", fromFilePath.toAbsolutePath().normalize());

        // ファイル保存
        Files.copy(fromFilePath, toFilePath, StandardCopyOption.REPLACE_EXISTING);
    }

}
