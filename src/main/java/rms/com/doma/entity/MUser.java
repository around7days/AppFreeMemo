package rms.com.doma.entity;

import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * MUserクラス
 * 
 */
@Entity(listener = MUserListener.class)
@Table(name = "m_user")
public class MUser {

    /** ユーザID */
    @Id
    @Column(name = "user_id")
    private String userId;

    /** ユーザ名 */
    @Column(name = "user_nm")
    private String userNm;

    /** パスワード */
    @Column(name = "password")
    private String password;

    /** メールアドレス */
    @Column(name = "email")
    private String email;

    /** 申請者区分 */
    @Column(name = "applicant_kbn")
    private String applicantKbn;

    /** 承認者区分 */
    @Column(name = "approval_kbn")
    private String approvalKbn;

    /** 管理者区分 */
    @Column(name = "admin_kbn")
    private String adminKbn;

    /** 削除フラグ */
    @Column(name = "del_flg")
    private Integer delFlg;

    /** 登録日時 */
    @Column(name = "ins_date")
    private LocalDateTime insDate;

    /** 登録ID */
    @Column(name = "ins_id")
    private String insId;

    /** 更新日時 */
    @Column(name = "upd_date")
    private LocalDateTime updDate;

    /** 更新ID */
    @Column(name = "upd_id")
    private String updId;

    /**
     * ユーザIDを取得します.
     * @return ユーザID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザIDを設定します.
     * @param userId ユーザID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザ名を取得します.
     * @return ユーザ名
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * ユーザ名を設定します.
     * @param userNm ユーザ名
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    /**
     * パスワードを取得します.
     * @return パスワード
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定します.
     * @param password パスワード
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * メールアドレスを取得します.
     * @return メールアドレス
     */
    public String getEmail() {
        return email;
    }

    /**
     * メールアドレスを設定します.
     * @param email メールアドレス
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 申請者区分を取得します.
     * @return 申請者区分
     */
    public String getApplicantKbn() {
        return applicantKbn;
    }

    /**
     * 申請者区分を設定します.
     * @param applicantKbn 申請者区分
     */
    public void setApplicantKbn(String applicantKbn) {
        this.applicantKbn = applicantKbn;
    }

    /**
     * 承認者区分を取得します.
     * @return 承認者区分
     */
    public String getApprovalKbn() {
        return approvalKbn;
    }

    /**
     * 承認者区分を設定します.
     * @param approvalKbn 承認者区分
     */
    public void setApprovalKbn(String approvalKbn) {
        this.approvalKbn = approvalKbn;
    }

    /**
     * 管理者区分を取得します.
     * @return 管理者区分
     */
    public String getAdminKbn() {
        return adminKbn;
    }

    /**
     * 管理者区分を設定します.
     * @param adminKbn 管理者区分
     */
    public void setAdminKbn(String adminKbn) {
        this.adminKbn = adminKbn;
    }

    /**
     * 削除フラグを取得します.
     * @return 削除フラグ
     */
    public Integer getDelFlg() {
        return delFlg;
    }

    /**
     * 削除フラグを設定します.
     * @param delFlg 削除フラグ
     */
    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * 登録日時を取得します.
     * @return 登録日時
     */
    public LocalDateTime getInsDate() {
        return insDate;
    }

    /**
     * 登録日時を設定します.
     * @param insDate 登録日時
     */
    public void setInsDate(LocalDateTime insDate) {
        this.insDate = insDate;
    }

    /**
     * 登録IDを取得します.
     * @return 登録ID
     */
    public String getInsId() {
        return insId;
    }

    /**
     * 登録IDを設定します.
     * @param insId 登録ID
     */
    public void setInsId(String insId) {
        this.insId = insId;
    }

    /**
     * 更新日時を取得します.
     * @return 更新日時
     */
    public LocalDateTime getUpdDate() {
        return updDate;
    }

    /**
     * 更新日時を設定します.
     * @param updDate 更新日時
     */
    public void setUpdDate(LocalDateTime updDate) {
        this.updDate = updDate;
    }

    /**
     * 更新IDを取得します.
     * @return 更新ID
     */
    public String getUpdId() {
        return updId;
    }

    /**
     * 更新IDを設定します.
     * @param updId 更新ID
     */
    public void setUpdId(String updId) {
        this.updId = updId;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}