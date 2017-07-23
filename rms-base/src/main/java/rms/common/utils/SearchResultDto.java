package rms.common.utils;

import java.util.List;

/**
 * 検索結果格納クラス<br>
 * 検索結果一覧と検索結果件数（総件数）を格納
 * @author
 * @param <T>
 */
public class SearchResultDto<T> {

    /** 検索結果一覧 */
    private List<T> resultList;
    /** 検索結果件数（総件数） */
    private long count;

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

}
