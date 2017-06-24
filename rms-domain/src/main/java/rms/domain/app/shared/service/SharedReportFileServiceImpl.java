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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import rms.common.base.ProjectProperties;
import rms.common.consts.Const;
import rms.common.consts.Const.ReportNmPattern;
import rms.common.dao.VMUserDao;
import rms.common.entity.VMUser;
import rms.common.utils.RmsFileUtils;
import rms.domain.app.shared.dto.SharedFileDto;
import rms.domain.app.shared.dto.SharedSubmitReportFileDto;

/**
 * 月報ファイル関連共通サービス実装
 * @author
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SharedReportFileServiceImpl implements SharedReportFileService {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(SharedReportFileServiceImpl.class);

    /** application.properties */
    @Autowired
    private ProjectProperties properties;

    @Autowired
    protected VMUserDao vMUserDao;

    /** ファイル名の文字コード */
    // TODO MS932固定で大丈夫？
    private static final Charset FILE_NM_CHARSET = Charset.forName("MS932");

    @Override
    public SharedFileDto getReportFileInfo(String applyUserId,
                                           String applyUserNm,
                                           Integer targetYm) {

        // ダウンロードファイルパスの生成
        Path filePath = createReportFilePath(properties.getReportStorage(), applyUserId, targetYm);

        // ダウンロードファイル名の生成
        String fileNm = createReportDownloadFileNm1(applyUserId, applyUserNm, targetYm);

        // 返却用情報の生成
        SharedFileDto dto = new SharedFileDto();
        dto.setFilePath(filePath);
        dto.setFileNm(fileNm);

        return dto;
    }

    @Override
    public SharedFileDto createReportFileBulk(List<SharedSubmitReportFileDto> reportFileDtoList,
                                              ReportNmPattern reportNmPattern) throws FileNotFoundException,
                                                                               IOException {

        // zipファイル名・ファイルパスの生成
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String zipFileNm = "report" + LocalDateTime.now().format(dateFormat) + ".zip";
        Path zipPath = Paths.get(properties.getTemporaryStorage(), zipFileNm);

        // zipファイル生成
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath.toFile()), FILE_NM_CHARSET)) {
            for (SharedSubmitReportFileDto dto : reportFileDtoList) {
                String applyUserId = dto.getApplyUserId();
                Integer targetYm = dto.getTargetYm();

                // ダウンロードファイルパスの生成
                Path filePath = createReportFilePath(properties.getReportStorage(), applyUserId, targetYm);

                // ダウンロードファイル名の生成
                VMUser mUser = vMUserDao.selectById(dto.getApplyUserId());
                String fileNm;
                if (reportNmPattern == ReportNmPattern.NOMAL) {
                    // 通常のファイル名
                    fileNm = createReportDownloadFileNm1(applyUserId, mUser.getUserNm(), targetYm);
                } else {
                    // 提出用のファイル名
                    fileNm = createReportDownlaodFileNm2(applyUserId,
                                                         mUser.getUserNm(),
                                                         targetYm,
                                                         mUser.getDepartmentRnm());
                }

                // zipファイルに月報ファイルを追加
                ZipEntry entry = new ZipEntry(fileNm);
                zos.putNextEntry(entry);
                Files.copy(filePath, zos);
            }
        } catch (Exception e) {
            logger.warn("zipファイルの生成に失敗 -> {}", zipPath);
            throw e;
        }

        // 返却用情報の生成
        SharedFileDto dto = new SharedFileDto();
        dto.setFilePath(zipPath);
        dto.setFileNm(zipFileNm);

        return dto;
    }

    @Override
    public void saveReportFile(MultipartFile file,
                               String applyUserId,
                               Integer targetYm) throws IOException {
        // 月報保存ファイルパスの生成
        Path filePath = createReportFilePath(properties.getReportStorage(), applyUserId, targetYm);

        // 月報保存処理
        RmsFileUtils.fileSave(file.getInputStream(), filePath);
    }

    @Override
    public void saveReportFile(Path fromFilePath,
                               String applyUserId,
                               Integer targetYm) throws IOException {
        // 月報保存ファイルパスの生成
        Path toFilePath = createReportFilePath(properties.getReportStorage(), applyUserId, targetYm);

        // 月報保存処理
        RmsFileUtils.fileSave(fromFilePath, toFilePath);
    }

    @Override
    public List<SharedFileDto> unZipReportFileInfo(MultipartFile file) throws IOException {

        // 解凍後の月報情報リスト
        List<SharedFileDto> reportList = new ArrayList<>();

        // zipアップロード一時格納先ディレクトリの生成
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        Path unzipDir = Paths.get(properties.getTemporaryStorage(), LocalDateTime.now().format(dateFormat));
        unzipDir.toFile().mkdirs();

        // zipファイルパスの生成
        Path zipPath = unzipDir.resolve("report.zip");

        // zipファイルの保存
        RmsFileUtils.fileSave(file.getInputStream(), zipPath);

        // zipファイルの解凍処理
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipPath.toFile()), FILE_NM_CHARSET)) {
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
                SharedFileDto dto = new SharedFileDto();
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
     * @return ファイル名[yyyymm_userId_userNm.xlsx]
     */
    private String createReportDownloadFileNm1(String applyUserId,
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

    /**
     * 月報ダウンロードファイル名の生成（提出用）
     * @param applyUserId
     * @param applyUserNm
     * @param targetYm
     * @param departmentRnm
     * @return ファイル名[yyyy mm 作業月報【departmentRnm)userNm_userId】.xlsx]
     */
    private String createReportDownlaodFileNm2(String applyUserId,
                                               String applyUserNm,
                                               Integer targetYm,
                                               String departmentRnm) {
        // ユーザ名の空白除去（全角半角すべて）
        String newApplyUserNm = applyUserNm.replaceAll("\\s", "").replaceAll("　", "");
        // 年月で分割
        String yyyy = targetYm.toString().substring(0, 4);
        String mm = targetYm.toString().substring(4, 6);

        StringBuilder sb = new StringBuilder();
        sb.append(yyyy).append(" ").append(mm).append(" ");
        sb.append("作業月報");
        sb.append("【");
        sb.append(departmentRnm).append(")").append(applyUserId).append("_").append(newApplyUserNm);
        sb.append("】");
        sb.append(".xlsx");

        return sb.toString();
    }
}
