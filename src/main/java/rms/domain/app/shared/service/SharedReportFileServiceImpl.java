package rms.domain.app.shared.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;

import rms.common.base.ApplicationProperties;
import rms.common.utils.FileUtils;
import rms.domain.app.shared.dto.ReportFileDto;

/**
 * 月報ファイル関連共通サービスインタフェース
 * @author
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SharedReportFileServiceImpl implements SharedReportFileService {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(SharedReportFileServiceImpl.class);

    /** application.properties */
    private static final ApplicationProperties properties = ApplicationProperties.INSTANCE;

    /** 月報ファイル格納ディレクトリ */
    private static final String reportFileStorageDir = properties.getString("myapp.report.storage");

    /** 一時ファイル格納ディレクトリ */
    private static final String temporaryStorageDir = properties.getString("myapp.temporary.storage");

    /**
     * 月報ファイル保存処理
     * @throws IOException
     */
    @Override
    public void saveReportFile(MultipartFile file,
                               String applyUserId,
                               Integer targetYm) throws IOException {
        // 月報保存ファイルパスの生成
        Path filePath = createReportFilePath(reportFileStorageDir, applyUserId, targetYm);

        // 月報保存処理
        FileUtils.fileSave(file.getInputStream(), filePath);
    }

    /**
     * 月報ファイルダウンロード情報生成
     * @param applyUserId
     * @param applyUserNm
     * @param targetYm
     * @return
     */
    @Override
    public ReportFileDto createReportFileDownloadInfo(String applyUserId,
                                                      String applyUserNm,
                                                      Integer targetYm) {

        // ダウンロードファイルパスの生成
        Path filePath = createReportFilePath(reportFileStorageDir, applyUserId, targetYm);

        // ダウンロードファイル名の生成
        String fileNm = createReportDownloadFileNm(applyUserId, applyUserNm, targetYm);

        // 返却用情報の生成
        ReportFileDto dto = new ReportFileDto();
        dto.setFilePath(filePath);
        dto.setFileNm(fileNm);

        return dto;
    }

    /**
     * 月報ファイル一括ダウンロード情報生成<br>
     * サーバ内でzipファイルを生成して、生成したzipファイル情報を返却する
     * @param applyUserIdList
     * @param applyUserNmList
     * @param targetYmList
     * @param cnt
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    @Override
    public ReportFileDto createReportFileBulkDownloadInfo(List<String> applyUserIdList,
                                                          List<String> applyUserNmList,
                                                          List<Integer> targetYmList,
                                                          int cnt) throws FileNotFoundException, IOException {

        // zipファイル名・ファイルパスの生成
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String zipFileNm = "report" + LocalDateTime.now().format(dateFormat) + ".zip";
        Path zipPath = Paths.get(temporaryStorageDir, zipFileNm);

        // zipファイル生成
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath.toFile()))) {
            for (int i = 0; i < cnt; i++) {
                String applyUserId = applyUserIdList.get(i);
                String applyUserNm = applyUserNmList.get(i);
                Integer targetYm = targetYmList.get(i);

                // ダウンロードファイルパスの生成
                Path filePath = createReportFilePath(reportFileStorageDir, applyUserId, targetYm);

                // ダウンロードファイル名の生成
                String fileNm = createReportDownloadFileNm(applyUserId, applyUserNm, targetYm);

                // zipファイルに月報ファイルを追加
                ZipEntry entry = new ZipEntry(fileNm);
                zos.putNextEntry(entry);
                Files.copy(filePath.toFile(), zos);
            }
        }

        // 返却用情報の生成
        ReportFileDto dto = new ReportFileDto();
        dto.setFilePath(zipPath);
        dto.setFileNm(zipFileNm);

        return dto;
    }

    /**
     * 月報ファイルパスの生成
     * @param storageDir
     * @param applyUserId
     * @param targetYm
     * @return
     */
    private Path createReportFilePath(String storageDir,
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
    private String createReportDownloadFileNm(String applyUserId,
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

}
