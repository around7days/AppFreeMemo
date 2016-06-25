package mms.uniq.mst.user.search;

import javax.validation.constraints.Size;

/**
 * ユーザ一覧画面フォーム
 * @author
 */
public class UserSearchForm {

    /** ユーザーID */
    @Size(max = 10, message = "ユーザーIDは{Size.message}")
    private String userId;

    /** ユーザーID */
    @Size(max = 10, message = "ユーザー名は{Size.message}")
    private String userNm;

    /**
     * ユーザーIDを取得します。
     * @return ユーザーID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定します。
     * @param userId ユーザーID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
