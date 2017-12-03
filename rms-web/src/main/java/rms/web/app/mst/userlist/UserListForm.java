package rms.web.app.mst.userlist;

import java.util.List;

import javax.validation.Valid;

import rms.common.utils.PageInfo;
import rms.domain.app.mst.userlist.UserListResultEntity;

/**
 * ユーザ一覧画面フォーム
 * @author
 */
public class UserListForm extends rms.common.abstracts.AbstractForm {

    /* 変数宣言 ------------------------------------------------------------- */
    /** 検索条件 */
    @Valid
    private UserListConditionForm condition = new UserListConditionForm();
    /** ページ情報 */
    private PageInfo pageInfo = new PageInfo();
    /** 検索結果リスト */
    private List<UserListResultEntity> resultList;

    public UserListConditionForm getCondition() {
        return condition;
    }

    public void setCondition(UserListConditionForm condition) {
        this.condition = condition;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<UserListResultEntity> getResultList() {
        return resultList;
    }

    public void setResultList(List<UserListResultEntity> resultList) {
        this.resultList = resultList;
    }

}
