package rms.web.app.mst.userlist;

import javax.validation.constraints.Size;

import rms.common.validator.HalfWidthAlphaNumeric;
import rms.common.validator.NotSymbol;

/**
 * ユーザ一覧画面（検索条件）フォーム
 * @author
 */
public class UserListConditionForm extends rms.common.abstracts.AbstractForm {

    /** ユーザID */
    @Size(max = 10, message = "ユーザID：{Size.message}")
    @HalfWidthAlphaNumeric(message = "ユーザID：{HalfWidthAlphaNumeric.message}")
    private String userId;

    /** ユーザ名 */
    @Size(max = 20, message = "ユーザ名：{Size.message}")
    @NotSymbol(message = "ユーザ名：{NotSymbol.message}")
    private String userNm;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }
}
