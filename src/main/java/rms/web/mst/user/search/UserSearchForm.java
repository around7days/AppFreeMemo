package rms.web.mst.user.search;

import java.util.List;

import javax.validation.constraints.Size;

import rms.com.doma.entity.MUser;
import rms.com.page.PageInfo;

/**
 * ユーザ一覧画面フォーム
 * @author
 */
public class UserSearchForm extends rms.com.abstracts.AbstractForm {

    /*
     * 検索条件
     */
    /** ユーザID */
    @Size(max = 10, message = "ユーザIDは{Size.message}")
    private String userId;

    /** ユーザ名 */
    @Size(max = 10, message = "ユーザ名は{Size.message}")
    private String userNm;

    /*
     * ページ情報
     */
    private PageInfo pageInfo = new PageInfo();

    /*
     * 検索結果
     */
    /** 検索結果リスト */
    private List<MUser> resultList;

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

    /**
     * pageInfoを取得します。
     * @return pageInfo
     */
    public PageInfo getPageInfo() {
        return pageInfo;
    }

    /**
     * pageInfoを設定します。
     * @param pageInfo pageInfo
     */
    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    /**
     * 検索結果リストを取得します。
     * @return 検索結果リスト
     */
    public List<MUser> getResultList() {
        return resultList;
    }

    /**
     * 検索結果リストを設定します。
     * @param resultList 検索結果リスト
     */
    public void setResultList(List<MUser> resultList) {
        this.resultList = resultList;
    }
}
