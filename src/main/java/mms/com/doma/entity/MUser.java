package mms.com.doma.entity;

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

    /** ユーザーID */
    @Id
    @Column(name = "user_id")
    String userId;

    /** ユーザー名 */
    @Column(name = "user_nm")
    String userNm;

    /** パスワード */
    @Column(name = "password")
    String password;

    /** メールアドレス */
    @Column(name = "email")
    String email;

    /** 削除フラグ */
    @Column(name = "del_flg")
    Integer delFlg;

    /** 登録日時 */
    @Column(name = "ins_date")
    LocalDateTime insDate;

    /** 登録ID */
    @Column(name = "ins_id")
    String insId;

    /** 更新日時 */
    @Column(name = "upd_date")
    LocalDateTime updDate;

    /** 更新ID */
    @Column(name = "upd_id")
    String updId;

    /**
     * ユーザーIDを取得します.
     *
     * @return ユーザーID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定します.
     *
     * @param userId ユーザーID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザー名を取得します.
     *
     * @return ユーザー名
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * ユーザー名を設定します.
     *
     * @param userNm ユーザー名
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    /**
     * パスワードを取得します.
     *
     * @return パスワード
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定します.
     *
     * @param password パスワード
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * メールアドレスを取得します.
     *
     * @return メールアドレス
     */
    public String getEmail() {
        return email;
    }

    /**
     * メールアドレスを設定します.
     *
     * @param email メールアドレス
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 削除フラグを取得します.
     *
     * @return 削除フラグ
     */
    public Integer getDelFlg() {
        return delFlg;
    }

    /**
     * 削除フラグを設定します.
     *
     * @param delFlg 削除フラグ
     */
    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * 登録日時を取得します.
     *
     * @return 登録日時
     */
    public LocalDateTime getInsDate() {
        return insDate;
    }

    /**
     * 登録日時を設定します.
     *
     * @param insDate 登録日時
     */
    public void setInsDate(LocalDateTime insDate) {
        this.insDate = insDate;
    }

    /**
     * 登録IDを取得します.
     *
     * @return 登録ID
     */
    public String getInsId() {
        return insId;
    }

    /**
     * 登録IDを設定します.
     *
     * @param insId 登録ID
     */
    public void setInsId(String insId) {
        this.insId = insId;
    }

    /**
     * 更新日時を取得します.
     *
     * @return 更新日時
     */
    public LocalDateTime getUpdDate() {
        return updDate;
    }

    /**
     * 更新日時を設定します.
     *
     * @param updDate 更新日時
     */
    public void setUpdDate(LocalDateTime updDate) {
        this.updDate = updDate;
    }

    /**
     * 更新IDを取得します.
     *
     * @return 更新ID
     */
    public String getUpdId() {
        return updId;
    }

    /**
     * 更新IDを設定します.
     *
     * @param updId 更新ID
     */
    public void setUpdId(String updId) {
        this.updId = updId;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}