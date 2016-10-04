package rms.domain.app.shared.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import rms.domain.app.shared.dto.ReportFileDto;

/**
 * 月報ファイル関連共通サービスインタフェース
 * @author
 */
public interface SharedReportFileService {

    /**
     * 月報ファイルダウンロード情報生成
     * @param applyUserId
     * @param applyUserNm
     * @param targetYm
     * @return
     */
    public ReportFileDto createReportFileDownloadInfo(String applyUserId,
                                                      String applyUserNm,
                                                      Integer targetYm);

    /**
     * 月報ファイル一括ダウンロード情報生成
     * サーバ内でzipファイルを生成して、生成したzipファイル情報を返却する
     * @param applyUserIdList
     * @param applyUserNmList
     * @param targetYmList
     * @param cnt
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public ReportFileDto createReportFileBulkDownloadInfo(List<String> applyUserIdList,
                                                          List<String> applyUserNmList,
                                                          List<Integer> targetYmList,
                                                          int cnt) throws FileNotFoundException, IOException;

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

}