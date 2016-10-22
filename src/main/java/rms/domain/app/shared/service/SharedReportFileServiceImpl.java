package rms.domain.app.shared.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import rms.common.base.ProjectProperties;
import rms.common.consts.Const;
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
    private static final Logger logger = LoggerFactory.getLogger(SharedReportFileServiceImpl.class);

    /** application.properties */
    private static final ProjectProperties properties = ProjectProperties.INSTANCE;

    /** 月報ファイル格納ディレクトリ */
    private static final String reportFileDir = properties.getString("report.storage");

    /** 一時ファイル格納ディレクトリ */
    private static final String temporaryDir = properties.getString("temporary.storage");

    /** 初期処理 */
    static {
        // 月報保存ディレクトリの生成
        File dir1 = new File(reportFileDir);
        if (!dir1.exists()) {
            dir1.mkdirs();
        }
        // 一時ファイル格納ディレクトリの生成
        File dir2 = new File(temporaryDir);
        if (!dir2.exists()) {
            dir2.mkdirs();
        }
    }

    /**
     * 月報ファイルダウンロード情報生成
     * 引数を基に生成した月報ファイル情報を返却する
     * @param applyUserId
     * @param applyUserNm
     * @param targetYm
     * @return
     */
    @Override
    public ReportFileDto getReportFileDownloadInfo(String applyUserId,
                                                   String applyUserNm,
                                                   Integer targetYm) {

        // ダウンロードファイルパスの生成
        Path filePath = createReportFilePath(reportFileDir, applyUserId, targetYm);

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
     * サーバ内でzipファイルを作成して、作成したzipファイル情報を返却する
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
        Path zipPath = Paths.get(temporaryDir, zipFileNm);

        // zipファイル生成
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath.toFile()))) {
            for (int i = 0; i < cnt; i++) {
                String applyUserId = applyUserIdList.get(i);
                String applyUserNm = applyUserNmList.get(i);
                Integer targetYm = targetYmList.get(i);

                // ダウンロードファイルパスの生成
                Path filePath = createReportFilePath(reportFileDir, applyUserId, targetYm);

                // ダウンロードファイル名の生成
                String fileNm = createReportDownloadFileNm(applyUserId, applyUserNm, targetYm);

                // zipファイルに月報ファイルを追加
                ZipEntry entry = new ZipEntry(fileNm);
                zos.putNextEntry(entry);
                Files.copy(filePath, zos);
            }
        } catch (Exception e) {
            logger.warn("zipファイルの解凍に失敗 -> {}", zipPath);
            throw e;
        }

        // 返却用情報の生成
        ReportFileDto dto = new ReportFileDto();
        dto.setFilePath(zipPath);
        dto.setFileNm(zipFileNm);

        return dto;
    }

    /**
     * 月報ファイル保存処理
     * @throws IOException
     */
    @Override
    public void saveReportFile(MultipartFile file,
                               String applyUserId,
                               Integer targetYm) throws IOException {
        // 月報保存ファイルパスの生成
        Path filePath = createReportFilePath(reportFileDir, applyUserId, targetYm);

        // 月報保存処理
        FileUtils.fileSave(file.getInputStream(), filePath);
    }

    /**
     * 月報ファイル保存処理
     * @throws IOException
     */
    @Override
    public void saveReportFile(Path fromFilePath,
                               String applyUserId,
                               Integer targetYm) throws IOException {
        // 月報保存ファイルパスの生成
        Path toFilePath = createReportFilePath(reportFileDir, applyUserId, targetYm);

        // 月報保存処理
        FileUtils.fileSave(fromFilePath, toFilePath);
    }

    /**
     * 月報zipファイル解凍処理
     * サーバ内でzipファイルを解凍して、zipファイル中に含まれる月報情報一覧を返却する
     * @throws IOException
     */
    @Override
    public List<ReportFileDto> unZipReportFileInfo(MultipartFile file) throws IOException {

        // 解凍後の月報情報リスト
        List<ReportFileDto> reportList = new ArrayList<>();

        // zipアップロード一時格納先ディレクトリの生成
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        Path unzipDir = Paths.get(temporaryDir, LocalDateTime.now().format(dateFormat));
        unzipDir.toFile().mkdirs();

        // zipファイルパスの生成
        Path zipPath = unzipDir.resolve("report.zip");

        // zipファイルの保存
        FileUtils.fileSave(file.getInputStream(), zipPath);

        // zipファイルの解凍処理
        // TODO 常にCharset.forName("MS932")で本当にOK？
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath.toFile()), Charset.forName("MS932"))) {
            ZipEntry entry = null;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    // ディレクトリの場合は何もしない
                    continue;
                }

                // 解凍先の月報ファイルパスを生成
                File reportFile = unzipDir.resolve(entry.getName()).toFile();

                // 解凍先ディレクトリの生成
                reportFile.getParentFile().mkdirs();

                // 解凍ファイルを出力する
                try (FileOutputStream fos = new FileOutputStream(reportFile)) {
                    byte[] buf = new byte[256];
                    int size = 0;
                    while ((size = zis.read(buf)) > 0) {
                        fos.write(buf, 0, size);
                    }
                }

                // 返却情報の設定
                ReportFileDto dto = new ReportFileDto();
                dto.setFilePath(reportFile.toPath());
                dto.setFileNm(reportFile.getName());
                reportList.add(dto);
            }
        }

        return reportList;
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
        String filePath = targetYm + Const.REPORT_FILE_DELIMITER + applyUserId + ".xlsx";
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
        sb.append(targetYm).append(Const.REPORT_FILE_DELIMITER);
        sb.append(applyUserId).append(Const.REPORT_FILE_DELIMITER);
        sb.append(newApplyUserNm);
        sb.append(".xlsx");
        return sb.toString();
    }

}
