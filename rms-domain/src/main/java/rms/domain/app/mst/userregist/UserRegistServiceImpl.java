package rms.domain.app.mst.userregist;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rms.common.base.BusinessException;
import rms.common.consts.Const;
import rms.common.consts.MRoleConst;
import rms.common.consts.MessageEnum;
import rms.common.dao.MCodeDao;
import rms.common.dao.MUserApproveFlowDao;
import rms.common.dao.MUserDao;
import rms.common.dao.MUserRoleDao;
import rms.common.dao.VMUserDao;
import rms.common.entity.MUser;
import rms.common.entity.MUserApproveFlow;
import rms.common.entity.MUserRole;
import rms.common.entity.VMUser;
import rms.common.utils.RmsBeanUtils;
import rms.common.utils.RmsStringUtils;
import rms.common.utils.SelectOptionEntity;

/**
 * ユーザ登録画面サービス
 * @author
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserRegistServiceImpl implements UserRegistService {
    /** logger */
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(UserRegistServiceImpl.class);

    /** ユーザ情報登録Dao */
    @Autowired
    private UserRegistDao dao;

    /** MCodeDao */
    @Autowired
    private MCodeDao mCodeDao;

    /** MUserDao */
    @Autowired
    private MUserDao mUserDao;

    /** MUserApproveFlowDao */
    @Autowired
    private MUserApproveFlowDao mUserApproveFlowDao;

    /** MUserRoleDao */
    @Autowired
    private MUserRoleDao mUserRoleDao;

    /** VMUserDao */
    @Autowired
    private VMUserDao vMUserDao;

    @Override
    public UserRegistDto initDisplayUpdate(String userId) {

        // 返却用ユーザ情報の生成
        UserRegistDto dto = new UserRegistDto();

        // ユーザマスタ情報の取得
        VMUser vMUser = vMUserDao.selectById(userId);
        RmsBeanUtils.copyProperties(vMUser, dto);

        // ユーザ役割マスタ情報の取得
        List<MUserRole> mUserRoleList = mUserRoleDao.selectListByUserId(userId);
        for (MUserRole mUserRole : mUserRoleList) {
            switch (mUserRole.getRole()) {
            case MRoleConst.APPLY: // 申請者
                dto.setRoleApplyFlg(Const.FLG_ON);
                break;
            case MRoleConst.APPROVE: // 承認者
                dto.setRoleApproveFlg(Const.FLG_ON);
                break;
            case MRoleConst.REFERENCE: // 閲覧者
                dto.setRoleReferenceFlg(Const.FLG_ON);
                break;
            case MRoleConst.ADMIN: // 管理者
                dto.setRoleAdminFlg(Const.FLG_ON);
                break;
            }
        }

        return dto;
    }

    @Override
    public void regist(UserRegistDto dto) throws BusinessException {

        // ユーザIDの重複チェック
        validateUniquUserId(dto.getUserId());

        // 承認ルート設定チェック
        validateApprovalRoute(dto.getRoleApplyFlg(),
                              dto.getApproveUserId1(),
                              dto.getApproveUserId2(),
                              dto.getApproveUserId3(),
                              dto.getApproveUserId4());

        // ユーザマスタ登録
        inserUser(dto);

        // ユーザ承認フローマスタ登録
        deleteInsertUserApproveFlow(dto);

        // ユーザ役割マスタ登録
        deleteInsertUserRole(dto);
    }

    @Override
    public void update(UserRegistDto dto) throws BusinessException {

        // 承認ルート設定チェック
        validateApprovalRoute(dto.getRoleApplyFlg(),
                              dto.getApproveUserId1(),
                              dto.getApproveUserId2(),
                              dto.getApproveUserId3(),
                              dto.getApproveUserId4());

        // ユーザマスタ更新
        updateUser(dto);

        // ユーザ承認フローマスタ登録
        deleteInsertUserApproveFlow(dto);

        // ユーザ役割マスタ登録
        deleteInsertUserRole(dto);
    }

    @Override
    public List<SelectOptionEntity> getSelectboxApprove() {
        // セレクトボックス用 承認者一覧の取得
        List<SelectOptionEntity> approveList = dao.selectboxApproveUser();

        return approveList;
    }

    @Override
    public List<SelectOptionEntity> getSelectboxDepartment() {
        // セレクトボックス用 部署一覧の取得
        List<SelectOptionEntity> departmentList = mCodeDao.selectboxDepartmentRnm();

        return departmentList;
    }

    /**
     * ユーザマスタ登録処理
     * @param dto
     */
    private void inserUser(UserRegistDto dto) {
        // 登録情報の生成
        MUser entity = RmsBeanUtils.createCopyProperties(dto, MUser.class);
        // 登録処理
        mUserDao.insert(entity);
    }

    /**
     * ユーザ承認フローマスタ登録処理<br>
     * 説明：ユーザIDに紐付く承認フローを全て削除してから新規登録を行います。
     * @param dto
     */
    private void deleteInsertUserApproveFlow(UserRegistDto dto) {

        // ユーザIDに紐付くレコードを全て削除
        mUserApproveFlowDao.deleteListByUserId(dto.getUserId());

        // ユーザ役割マスタ情報の生成
        MUserApproveFlow entity = new MUserApproveFlow();
        entity.setUserId(dto.getUserId());

        // 承認者１の登録
        if (!RmsStringUtils.isEmpty(dto.getApproveUserId1())) {
            entity.setApproveSeq(Const.APPROVE_FLOW_SEQ_1);
            entity.setApproveUserId(dto.getApproveUserId1());
            mUserApproveFlowDao.insert(entity);
        }
        // 承認者２の登録
        if (!RmsStringUtils.isEmpty(dto.getApproveUserId2())) {
            entity.setApproveSeq(Const.APPROVE_FLOW_SEQ_2);
            entity.setApproveUserId(dto.getApproveUserId2());
            mUserApproveFlowDao.insert(entity);
        }
        // 承認者３の登録
        if (!RmsStringUtils.isEmpty(dto.getApproveUserId3())) {
            entity.setApproveSeq(Const.APPROVE_FLOW_SEQ_3);
            entity.setApproveUserId(dto.getApproveUserId3());
            mUserApproveFlowDao.insert(entity);
        }
        // 承認者４の登録
        if (!RmsStringUtils.isEmpty(dto.getApproveUserId4())) {
            entity.setApproveSeq(Const.APPROVE_FLOW_SEQ_4);
            entity.setApproveUserId(dto.getApproveUserId4());
            mUserApproveFlowDao.insert(entity);
        }
    }

    /**
     * ユーザ役割マスタ登録処理<br>
     * 説明：ユーザに紐付くユーザ役割マスタを全て削除してから新規登録を行います。
     * @param dto
     */
    private void deleteInsertUserRole(UserRegistDto dto) {

        // ユーザIDに紐付くレコードを全て削除
        mUserRoleDao.deleteListByUserId(dto.getUserId());

        // ユーザ役割マスタ情報の生成
        MUserRole entity = new MUserRole();
        entity.setUserId(dto.getUserId());

        // 申請者の登録
        if (Const.FLG_ON.equals(dto.getRoleApplyFlg())) {
            entity.setRole(MRoleConst.APPLY);
            mUserRoleDao.insert(entity);
        }
        // 承認者の登録
        if (Const.FLG_ON.equals(dto.getRoleApproveFlg())) {
            entity.setRole(MRoleConst.APPROVE);
            mUserRoleDao.insert(entity);
        }
        // 閲覧者の登録
        if (Const.FLG_ON.equals(dto.getRoleReferenceFlg())) {
            entity.setRole(MRoleConst.REFERENCE);
            mUserRoleDao.insert(entity);
        }
        // 管理者の登録
        if (Const.FLG_ON.equals(dto.getRoleAdminFlg())) {
            entity.setRole(MRoleConst.ADMIN);
            mUserRoleDao.insert(entity);
        }
    }

    /**
     * ユーザマスタ更新処理
     * @param dto
     */
    private void updateUser(UserRegistDto dto) {
        // 更新情報の生成
        MUser entity = RmsBeanUtils.createCopyProperties(dto, MUser.class);

        // 更新処理（楽観的排他制御）
        mUserDao.update(entity);
    }

    /**
     * ユーザIDの重複チェック<br>
     * 重複エラーでBusinessExceptionを発生させます<br>
     * @param userId
     * @throws BusinessException
     */
    private void validateUniquUserId(String userId) throws BusinessException {
        boolean hasExists = mUserDao.existsById(userId);
        if (hasExists) {
            // 「{0}が重複しています」
            throw new BusinessException(MessageEnum.error001, "ユーザID");
        }
    }

    /**
     * 承認ルート設定チェック<br>
     * 承認ルート設定エラーでBusinessExceptionを発生させます。<br>
     * チェック内容は以下の二つ<br>
     * ・承認者１～４に同じ承認者は設定できません<br>
     * ・役割に申請者を保持している場合、承認者４は必須入力になります
     * @param roleApplyFlg
     * @param approveUserId1
     * @param approveUserId2
     * @param approveUserId3
     * @param approveUserId4
     * @throws BusinessException
     */
    private void validateApprovalRoute(String roleApplyFlg,
                                       String approveUserId1,
                                       String approveUserId2,
                                       String approveUserId3,
                                       String approveUserId4) throws BusinessException {
        //@formatter:off
        if (isValueEquals(approveUserId1, approveUserId2) ||
            isValueEquals(approveUserId1, approveUserId3) ||
            isValueEquals(approveUserId1, approveUserId4) ||
            isValueEquals(approveUserId2, approveUserId3) ||
            isValueEquals(approveUserId2, approveUserId4) ||
            isValueEquals(approveUserId3, approveUserId4)) {
            // 承認者１～４に同じ承認者は設定できません
            throw new BusinessException(MessageEnum.error004);
        }

        if (Const.FLG_ON.equals(roleApplyFlg) &&
            RmsStringUtils.isEmpty(approveUserId1) &&
            RmsStringUtils.isEmpty(approveUserId2) &&
            RmsStringUtils.isEmpty(approveUserId3) &&
            RmsStringUtils.isEmpty(approveUserId4)){
            // 役割が申請者の場合、承認者は必須です
            throw new BusinessException(MessageEnum.error005);
        }
        //@formatter:on
    }

    /**
     * 値の同一チェック<br>
     * （どちらかの値が空白の場合はfalseとする）
     * @param value1
     * @param value2
     * @return true:同じ false:異なる
     */
    private boolean isValueEquals(String value1,
                                  String value2) {
        if (RmsStringUtils.isEmpty(value1) || RmsStringUtils.isEmpty(value2)) {
            return false;
        }
        return value1.equals(value2);
    }

}
