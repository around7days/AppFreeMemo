package rms.domain.app.shared.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import rms.common.consts.Const.ReportNmPattern;
import rms.domain.app.shared.dto.SharedFileDto;
import rms.domain.app.shared.dto.SharedSubmitReportFileDto;

/**
 * 月報ファイル関連共通サービスインタフェース
 * @author
 */
public interface SharedReportFileService {

    /**
     * 月報ファイルダウンロード情報生成
     * 引数を基に生成した月報ファイル情報を返却する
     * @param applyUserId
     * @param applyUserNm
     * @param targetYm
     * @return
     */
    public SharedFileDto getReportFileInfo(String applyUserId,
                                           String applyUserNm,
                                           Integer targetYm);

    /**
     * 月報ファイル一覧ZIPファイル生成<br>
     * サーバ内でzipファイルを作成して、作成したzipファイル情報を返却する
     * @param reportFileDtoList
     * @param reportNmPattern
     * @return 生成したzipファイル情報
     * @throws FileNotFoundException
     * @throws IOException
     */
    public SharedFileDto createReportFileBulk(List<SharedSubmitReportFileDto> reportFileDtoList,
                                              ReportNmPattern reportNmPattern) throws FileNotFoundException,
                                                                               IOException;

    /**
     * 月報ファイル保存処理
     * @param file
     * @param applyUserId
     * @param targetYm
     * @throws IOException
     */
    public void saveReportFile(MultipartFile file,
                               String applyUserId,
                               Integer targetYm) throws IOException;

    /**
     * 月報ファイル保存処理
     * @param filePath
     * @param applyUserId
     * @param targetYm
     * @throws IOException
     */
    public void saveReportFile(Path filePath,
                               String applyUserId,
                               Integer targetYm) throws IOException;

    /**
     * 月報zipファイル解凍処理
     * サーバ内でzipファイルを解凍して、zipファイル中に含まれる月報情報一覧を返却する
     * @param file
     * @return
     * @throws IOException
     */
    public List<SharedFileDto> unZipReportFileInfo(MultipartFile file) throws IOException;

}