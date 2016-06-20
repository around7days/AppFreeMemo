package mms.com.entity;

import java.time.LocalDateTime;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

/**
 * 
 */
@Entity
@Table(name = "m_user")
public class MUser {

    /**  */
    @Id
    @Column(name = "user_id")
    String userId;

    /**  */
    @Column(name = "user_nm")
    String userNm;

    /**  */
    @Column(name = "password")
    String password;

    /**  */
    @Column(name = "email")
    String email;

    /**  */
    @Column(name = "del_flg")
    Integer delFlg;

    /**  */
    @Column(name = "ins_date")
    LocalDateTime insDate;

    /**  */
    @Column(name = "ins_id")
    String insId;

    /**  */
    @Column(name = "upd_date")
    LocalDateTime updDate;

    /**  */
    @Column(name = "upd_id")
    String updId;

    /**
     * Returns the userId.
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the userId.
     * @param userId the userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Returns the userNm.
     * @return the userNm
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * Sets the userNm.
     * @param userNm the userNm
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    /**
     * Returns the password.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the email.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the delFlg.
     * @return the delFlg
     */
    public Integer getDelFlg() {
        return delFlg;
    }

    /**
     * Sets the delFlg.
     * @param delFlg the delFlg
     */
    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * Returns the insDate.
     * @return the insDate
     */
    public LocalDateTime getInsDate() {
        return insDate;
    }

    /**
     * Sets the insDate.
     * @param insDate the insDate
     */
    public void setInsDate(LocalDateTime insDate) {
        this.insDate = insDate;
    }

    /**
     * Returns the insId.
     * @return the insId
     */
    public String getInsId() {
        return insId;
    }

    /**
     * Sets the insId.
     * @param insId the insId
     */
    public void setInsId(String insId) {
        this.insId = insId;
    }

    /**
     * Returns the updDate.
     * @return the updDate
     */
    public LocalDateTime getUpdDate() {
        return updDate;
    }

    /**
     * Sets the updDate.
     * @param updDate the updDate
     */
    public void setUpdDate(LocalDateTime updDate) {
        this.updDate = updDate;
    }

    /**
     * Returns the updId.
     * @return the updId
     */
    public String getUpdId() {
        return updId;
    }

    /**
     * Sets the updId.
     * @param updId the updId
     */
    public void setUpdId(String updId) {
        this.updId = updId;
    }
}