package mms.uniq.mst.user.search;

import java.util.List;

import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import mms.com.doma.entity.MUser;
import mms.com.page.PageInfo;

/**
 * ユーザ一覧画面フォーム
 * @author
 */
public class UserSearchForm {

    /*
     * 検索条件
     */
    /** ユーザーID */
    @Size(max = 10, message = "ユーザーIDは{Size.message}")
    private String userId;

    /** ユーザー名 */
    @Size(max = 10, message = "ユーザー名は{Size.message}")
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

    /**
     * ユーザー名を取得します。
     * @return ユーザー名
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * ユーザー名を設定します。
     * @param userNm ユーザー名
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

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
