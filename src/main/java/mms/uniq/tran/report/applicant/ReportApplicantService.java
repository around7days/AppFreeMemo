package mms.uniq.tran.report.applicant;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 月報申請画面サービス
 * @author
 */
@Service
public class ReportApplicantService extends mms.com.abstracts.AbstractService {
    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ReportApplicantService.class);

    /** 月報申請画面Dao */
    @Autowired
    ReportApplicantDao reportApplicantDao;

    /**
     * 新規初期処理
     * @param form
     */
    public void initInsert(ReportApplicantForm form) {
        // 年月のセット
        String targetYm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        form.setTargetYm(targetYm);
    }

    /**
     * 新規登録処理
     * @param form
     * @throws IOException
     * @throws IllegalStateException
     */
    public void insert(ReportApplicantForm form) throws IllegalStateException, IOException {
        // アップロードファイル
        MultipartFile file = form.getFile();
        // ファイルの保存先パスを生成
        Path outputPath = Paths.get("./upload_file", file.getOriginalFilename());
        logger.debug("ファイル保存：{}", outputPath.normalize().toAbsolutePath().toString());
        // ファイル保存
        file.transferTo(outputPath.toFile());
    }

    //    /**
    //     * 更新処理
    //     * @param form
    //     */
    //    public void update(ReportApplicantForm form) {
    //
    //        // 更新情報の生成
    //        MUser mUser = new MUser();
    //        BeanUtils.copyProperties(form, mUser);
    //        logger.debug("更新情報：{}", mUser.toString());
    //
    //        // 更新処理
    //        mUserDao.update(mUser);
    //    }
}
