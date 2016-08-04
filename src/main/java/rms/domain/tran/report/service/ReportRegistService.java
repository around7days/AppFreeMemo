package rms.domain.tran.report.service;

import rms.com.consts.MCodeConst;
import rms.domain.com.entity.TReport;
import rms.domain.com.repository.TReportDao;
import rms.domain.tran.report.repository.ReportSelectDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.thymeleaf.util.StringUtils;

/**
 * 月報申請画面サービス
 * @author
 */
@Service
public class ReportRegistService extends rms.domain.com.abstracts.AbstractService {

    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ReportRegistService.class);

    /** TReportDao */
    @Autowired
    TReportDao tReportDao;

    /** 月報情報取得Dao */
    @Autowired
    ReportSelectDao reportSelectDao;

    /**
     * 月報情報の新規登録処理<br>
     * 補足：承認状況はメソッド内で自動設定
     * @param entity
     */
    public void insertReportMst(TReport entity) {

        // 登録用Entityの生成
        // 承認者の有無に合わせてステータスを設定
        if (!StringUtils.isEmpty(entity.getApproveUserId1())) {
            entity.setStatus(MCodeConst.A001_Y01);
        } else if (!StringUtils.isEmpty(entity.getApproveUserId2())) {
            entity.setStatus(MCodeConst.A001_Y02);
        } else {
            entity.setStatus(MCodeConst.A001_Y03);
        }

        // 登録処理
        tReportDao.insert(entity);
    }

    //    /**
    //     * 更新処理
    //     * @param form
    //     */
    //    public void update(ReportApplyForm form) {
    //
    //        // 更新情報の生成
    //        MUser mUser = new MUser();
    //        BeanUtils.copyProperties(form, mUser);
    //        logger.debug("更新情報 -> {}", mUser);
    //
    //        // 更新処理
    //        mUserDao.update(mUser);
    //    }
}
