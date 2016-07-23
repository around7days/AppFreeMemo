package rms.web.base;

import java.util.List;

/**
 * 検索結果格納Entity
 * @author
 * @param <T> Entity
 */
public class SearchResultEntity<T> {

    /** 検索結果一覧 */
    private List<T> resultList;
    /** 検索結果件数（総件数） */
    private long count;

    /**
     * 検索結果一覧を取得します。
     * @return 検索結果一覧
     */
    public List<T> getResultList() {
        return resultList;
    }

    /**
     * 検索結果一覧を設定します。
     * @param resultList 検索結果一覧
     */
    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    /**
     * 検索結果件数（総件数）を取得します。
     * @return 検索結果件数（総件数）
     */
    public long getCount() {
        return count;
    }

    /**
     * 検索結果件数（総件数）を設定します。
     * @param count 検索結果件数（総件数）
     */
    public void setCount(long count) {
        this.count = count;
    }

}
