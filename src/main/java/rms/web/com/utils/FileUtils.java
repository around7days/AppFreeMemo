package rms.web.com.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

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
     * 月報ダウンロード
     * @param response
     * @param filePath
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public static void reportDownload(HttpServletResponse response,
                                      Path filePath) throws UnsupportedEncodingException, IOException {
        // 月報ファイルパスの生成
        String encodeFileNm = URLEncoder.encode(filePath.toFile().getName(), StandardCharsets.UTF_8.name());
        logger.debug("月報ファイル -> {}", filePath.toAbsolutePath());

        // ヘッダ設定
        response.addHeader("Content-Disposition", "attachment; filename*=UTF-8''" + encodeFileNm);

        // ファイル出力
        Files.copy(filePath, response.getOutputStream());
    }
}
