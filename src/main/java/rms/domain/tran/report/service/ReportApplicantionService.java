package rms.domain.tran.report.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import rms.com.base.UserInfo;
import rms.com.consts.MCodeConst;
import rms.domain.com.entity.TReport;
import rms.domain.com.repository.TReportDao;
import rms.domain.mst.user.repository.UserDao;
import rms.domain.tran.report.repository.ReportDao;
import rms.web.com.utils.SelectOptionEntity;
import rms.web.mst.user.regist.UserRegistForm;
import rms.web.tran.report.applicantion.ReportApplicantionForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.thymeleaf.util.StringUtils;

/**
 * 月報申請画面サービス
 * @author
 */
@Service
public class ReportApplicantionService extends rms.com.abstracts.AbstractService {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(ReportApplicantionService.class);

    /** 月報管理テーブルDao */
    @Autowired
    TReportDao tReportDao;

    /** ユーザ情報Dao */
    @Autowired
    UserDao userDao;

    /** 月報情報Dao */
    @Autowired
    ReportDao reportDao;

    /**
     * 初期表示処理（新規時）
     * @param form
     */
    public void initInsert(ReportApplicantionForm form) {
        // 表示モードの設定
        form.setViewMode(UserRegistForm.VIEW_MODE_INSERT);

        // 年月のセット
        LocalDateTime date = LocalDateTime.now();
        form.setTargetYear(String.valueOf(date.getYear()));
        form.setTargetMonth(date.format(DateTimeFormatter.ofPattern("MM")));

        // セレクトボックスの設定
        setSelectBox(form);
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
        logger.debug("ファイル保存先 -> {}", outputPath.normalize().toAbsolutePath().toString());

        // ファイル保存
        //        file.transferTo(outputPath.toFile());
        Files.copy(file.getInputStream(), outputPath, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * 新規登録処理
     * @param form
     * @param userInfo
     */
    public void insert(ReportApplicantionForm form,
                       UserInfo userInfo) {
        // 登録用Entityの生成
        TReport entity = new TReport();
        entity.setApplicantId(userInfo.getUserId());
        entity.setTargetYm(Integer.valueOf(form.getTargetYear() + form.getTargetMonth()));
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

        // 登録処理
        tReportDao.insert(entity);
    }

    /**
     * セレクトボックスの設定
     * @param form
     */
    private void setSelectBox(ReportApplicantionForm form) {
        // セレクトボックス用 承認者一覧の取得
        List<SelectOptionEntity> approverList = userDao.selectboxApprover();
        approverList.forEach(entity -> logger.debug(entity.toString()));
        // 格納
        form.setApproverList(approverList);
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
    //        logger.debug("更新情報 -> {}", mUser.toString());
    //
    //        // 更新処理
    //        mUserDao.update(mUser);
    //    }
}
