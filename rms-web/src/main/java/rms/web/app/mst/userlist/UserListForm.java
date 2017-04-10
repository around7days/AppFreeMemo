package rms.web.app.mst.userlist;

import java.util.List;

import javax.validation.Valid;

import rms.common.utils.PageInfo;
import rms.domain.app.mst.userlist.UserListEntityResult;

/**
 * ユーザ一覧画面フォーム
 * @author
 */
public class UserListForm extends rms.common.abstracts.AbstractForm {

    /* 変数宣言 ------------------------------------------------------------- */
    /** 検索条件 */
    @Valid
    private UserListFormCondition condition = new UserListFormCondition();
    /** ページ情報 */
    private PageInfo pageInfo = new PageInfo();
    /** 検索結果リスト */
    private List<UserListEntityResult> resultList;

    public UserListFormCondition getCondition() {
        return condition;
    }

    public void setCondition(UserListFormCondition condition) {
        this.condition = condition;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<UserListEntityResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<UserListEntityResult> resultList) {
        this.resultList = resultList;
    }

}
