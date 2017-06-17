package rms.web.app.mst.userregist;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import rms.common.utils.SelectOptionEntity;
import rms.common.validator.HalfWidthAlphaNumeric;
import rms.common.validator.NotSymbol;

/**
 * ユーザ登録画面フォーム
 * @author
 */
public class UserRegistForm extends rms.common.abstracts.AbstractForm {

    /* 入力チェック宣言 ----------------------------------------------------- */
    //@formatter:off
    /** 入力チェック：新規 */
    protected static interface Insert{};
    /** 入力チェック：更新 */
    protected static interface Update{};
    //@formatter:on

    /* 定数宣言 ------------------------------------------------------------- */
    /** 画面表示モード：新規 */
    public static final String VIEW_MODE_INSERT = "insert";
    /** 画面表示モード：更新 */
    public static final String VIEW_MODE_UPDATE = "update";

    /* 排他制御用 ----------------------------------------------------------- */
    /** ユーザマスタ 排他制御用バージョン */
    private Integer version;

    /* 変数宣言 ------------------------------------------------------------- */
    /** 画面表示モード */
    private String viewMode;

    /** ユーザID */
    @NotEmpty(message = "ユーザID：{NotEmpty.message}", groups = { Insert.class })
    @Size(max = 10, message = "ユーザID：{Size.message}", groups = { Insert.class })
    @HalfWidthAlphaNumeric(message = "ユーザID：{HalfWidthAlphaNumeric.message}", groups = { Insert.class })
    private String userId;
    /** パスワード */
    @NotEmpty(message = "パスワード：{NotEmpty.message}", groups = { Insert.class, Update.class })
    @Size(max = 10, message = "パスワード：{Size.message}", groups = { Insert.class, Update.class })
    @HalfWidthAlphaNumeric(message = "パスワード：{HalfWidthAlphaNumeric.message}", groups = { Insert.class, Update.class })
    private String password;
    /** ユーザ名 */
    @NotEmpty(message = "ユーザ名：{NotEmpty.message}", groups = { Insert.class, Update.class })
    @Size(max = 20, message = "ユーザ名：{Size.message}", groups = { Insert.class, Update.class })
    @NotSymbol(message = "ユーザ名：{NotSymbol.message}", groups = { Insert.class, Update.class })
    private String userNm;
    /** メールアドレス */
    @Email(message = "メールアドレス：{Email.message}", groups = { Insert.class, Update.class })
    @Size(max = 40, message = "メールアドレス：{Size.message}", groups = { Insert.class, Update.class })
    private String email;
    /** 部署ID */
    private String departmentId;

    /** 承認者ID1 */
    private String approveUserId1;
    /** 承認者ID2 */
    private String approveUserId2;
    /** 承認者ID3 */
    private String approveUserId3;
    /** 承認者ID4 */
    private String approveUserId4;

    /** 役割：申請者フラグ */
    private String roleApplyFlg;
    /** 役割：承認者フラグ */
    private String roleApproveFlg;
    /** 役割：管理者フラグ */
    private String roleAdminFlg;

    /** 承認者リスト(selectbox用) */
    private List<SelectOptionEntity> approveList;
    /** 部署リスト(selectbox用) */
    private List<SelectOptionEntity> departmentList;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getViewMode() {
        return viewMode;
    }

    public void setViewMode(String viewMode) {
        this.viewMode = viewMode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getApproveUserId1() {
        return approveUserId1;
    }

    public void setApproveUserId1(String approveUserId1) {
        this.approveUserId1 = approveUserId1;
    }

    public String getApproveUserId2() {
        return approveUserId2;
    }

    public void setApproveUserId2(String approveUserId2) {
        this.approveUserId2 = approveUserId2;
    }

    public String getApproveUserId3() {
        return approveUserId3;
    }

    public void setApproveUserId3(String approveUserId3) {
        this.approveUserId3 = approveUserId3;
    }

    public String getApproveUserId4() {
		return approveUserId4;
	}

	public void setApproveUserId4(String approveUserId4) {
		this.approveUserId4 = approveUserId4;
	}

	public String getRoleApplyFlg() {
        return roleApplyFlg;
    }

    public void setRoleApplyFlg(String roleApplyFlg) {
        this.roleApplyFlg = roleApplyFlg;
    }

    public String getRoleApproveFlg() {
        return roleApproveFlg;
    }

    public void setRoleApproveFlg(String roleApproveFlg) {
        this.roleApproveFlg = roleApproveFlg;
    }

    public String getRoleAdminFlg() {
        return roleAdminFlg;
    }

    public void setRoleAdminFlg(String roleAdminFlg) {
        this.roleAdminFlg = roleAdminFlg;
    }

    public List<SelectOptionEntity> getApproveList() {
        return approveList;
    }

    public void setApproveList(List<SelectOptionEntity> approveList) {
        this.approveList = approveList;
    }

    public List<SelectOptionEntity> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<SelectOptionEntity> departmentList) {
        this.departmentList = departmentList;
    }

}
