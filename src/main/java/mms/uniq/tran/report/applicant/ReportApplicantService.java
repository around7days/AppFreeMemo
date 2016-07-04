package mms.uniq.tran.report.applicant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import mms.com.consts.MCodeConst;
import mms.com.doma.dao.TReportDao;
import mms.com.doma.entity.TReport;
import mms.com.security.UserInfo;

/**
 * 月報申請画面サービス
 * @author
 */
@Service
public class ReportApplicantService extends mms.com.abstracts.AbstractService {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApplicantService.class);

    /** 月報管理テーブルDao */
    @Autowired
    TReportDao tReportDao;

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
     * ファイル保存処理
     * @param file
     * @throws IOException
     * @throws IllegalStateException
     */
    public void saveFile(MultipartFile file) throws IllegalStateException, IOException {
        // ファイルの保存先パスを生成
        Path outputPath = Paths.get("./upload_file", file.getOriginalFilename());
        logger.debug("ファイル保存先：{}", outputPath.normalize().toAbsolutePath().toString());

        // ファイル保存
        //        file.transferTo(outputPath.toFile());
        Files.copy(file.getInputStream(), outputPath, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * 新規登録処理
     * @param form
     * @param userInfo
     */
    public void insert(ReportApplicantForm form,
                       UserInfo userInfo) {
        // 登録用Entityの生成
        TReport entity = new TReport();
        entity.setApplicantId(userInfo.getUserId());
        entity.setTargetYm(Integer.valueOf(form.getTargetYm().replace("-", "")));
        entity.setApplicantDate(LocalDateTime.now());
        entity.setApprover1Id(form.getApprover1Id());
        entity.setApprover2Id(form.getApprover2Id());
        entity.setApprover3Id(form.getApprover3Id());
        entity.setFilePath(form.getFile().getOriginalFilename());

        // 承認者の有無に合わせてステータスを設定
        if (!StringUtils.isEmpty(form.getApprover1Id())) {
            entity.setStatus(MCodeConst.A001_Y01);
        } else if (!StringUtils.isEmpty(form.getApprover2Id())) {
            entity.setStatus(MCodeConst.A001_Y02);
        } else {
            entity.setStatus(MCodeConst.A001_Y03);
        }

        // TODO
        tReportDao.delete(entity);

        // 登録処理
        tReportDao.insert(entity);
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
