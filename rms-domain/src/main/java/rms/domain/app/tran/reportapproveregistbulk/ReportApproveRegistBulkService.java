package rms.domain.app.tran.reportapproveregistbulk;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import rms.common.auth.UserInfo;
import rms.common.base.BusinessException;

/**
 * 月報一括承認画面サービス
 * @author
 */
public interface ReportApproveRegistBulkService {

    /**
     * 月報情報の一括承認処理<br>
     * @param file
     * @param userInfo
     * @return
     * @throws BusinessException
     * @throws IOException
     */
    public List<ReportApproveRegistBulkDto> approveBulk(MultipartFile file,
                                                        UserInfo userInfo) throws BusinessException, IOException;
}
