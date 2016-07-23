package rms.domain.mst.user.entity;

/**
 * ユーザ一覧（検索条件）Entity
 * @author
 */
public class UserSearchConditionEntity extends rms.domain.com.abstracts.AbstractEntity {

    /** ユーザID */
    private String userId;

    /** ユーザ名 */
    private String userNm;

    /**
     * ユーザIDを取得します。
     * @return ユーザID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザIDを設定します。
     * @param userId ユーザID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザ名を取得します。
     * @return ユーザ名
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * ユーザ名を設定します。
     * @param userNm ユーザ名
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

}
