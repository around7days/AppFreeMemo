package rms.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     * @param applyUserId
     * @param targetYm
     * @return
     */
    public static Path createReportFilePath(String storageDir,
                                            String applyUserId,
                                            Integer targetYm) {
        String filePath = targetYm + "_" + applyUserId + ".xlsx";
        return Paths.get(storageDir, filePath);
    }

    /**
     * 月報ダウンロードファイル名の生成
     * @param applyUserId
     * @param applyUserNm
     * @param targetYm
     * @return
     */
    public static String createReportDownloadFileNm(String applyUserId,
                                                    String applyUserNm,
                                                    Integer targetYm) {
        // ユーザ名の空白除去（全角半角すべて）
        String newApplyUserNm = applyUserNm.replaceAll("\\s", "").replaceAll("　", "");

        StringBuilder sb = new StringBuilder();
        sb.append(targetYm).append("_");
        sb.append(applyUserId).append("_");
        sb.append(newApplyUserNm);
        sb.append(".xlsx");
        return sb.toString();
    }

    /**
     * 月報ダウンロード
     * @param response
     * @param filePath
     * @param fileNm
     * @throws IOException
     */
    public static void reportDownload(HttpServletResponse response,
                                      Path filePath,
                                      String fileNm) throws IOException {
        logger.debug("月報ファイルダウンロード -> {}", filePath.toAbsolutePath().normalize());

        // ファイルのエンコード
        String encodeFileNm = URLEncoder.encode(fileNm, StandardCharsets.UTF_8.name());

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
